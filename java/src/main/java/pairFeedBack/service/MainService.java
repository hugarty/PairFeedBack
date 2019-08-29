package pairFeedBack.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import pairFeedBack.dataTransferer.dto.DetailsPairDto;
import pairFeedBack.dataTransferer.dto.UserDto;
import pairFeedBack.dataTransferer.form.PairAddForm;
import pairFeedBack.dataTransferer.form.PairRatingForm;
import pairFeedBack.dataTransferer.form.PairUpdateForm;
import pairFeedBack.entity.FeedBack;
import pairFeedBack.entity.Pair;
import pairFeedBack.entity.User;
import pairFeedBack.exception.DeniedDataAccessException;
import pairFeedBack.repository.FeedBackRepository;
import pairFeedBack.repository.PairRepository;
import pairFeedBack.repository.UserRepository;

@Service
public class MainService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    PairRepository pairRepository;

    @Autowired
    FeedBackRepository feedbackRepository;

    public UserDto getUserDtoById(HttpServletRequest request){
        Optional<User> optUser = userRepository.findById(getUserId(request));
        if (optUser.isPresent())
            return UserDto.convertToDto(optUser.get());
        return null;
    }

    public DetailsPairDto getDetailsPairDtoById (Long pairId, HttpServletRequest request){
        Pair pair = secureFindPairById(pairId, request);
        DetailsPairDto pairDto = DetailsPairDto.convertToDto(pair);
        return pairDto;
    }

	public DetailsPairDto addPair(PairAddForm form, HttpServletRequest request) {
        User user = userRepository.getOne(getUserId(request));
        Pair pair = new Pair(form.getName(), user, (float)form.getRating());
        LocalDate today = LocalDate.now();
        FeedBack feedback = new FeedBack(form.getRating(), form.getMessage(), today);

        feedback.getPairList().add(pair);
        pair.getFeedbackList().add(feedback);
        
        feedbackRepository.save(feedback);
        pairRepository.save(pair);
        return DetailsPairDto.convertToDto(pair);
	}
    
	public DetailsPairDto addFeedbackToPair(PairRatingForm form, HttpServletRequest request) {
        Pair pair = secureFindPairById(form.getPairId(), request);
        LocalDate today = LocalDate.now();
        Optional<FeedBack> optFeedback = pair.getFeedbackList().stream()
            .filter(feedback -> feedback.getDate().isEqual(today))
            .findFirst();
        
        if(optFeedback.isPresent()){
            updateTodaysFeedBack(form, pair, optFeedback.get());
        }
        else{
            addNewFeedBackToPair(form, pair, today);
        }
        
        DetailsPairDto detailsPairDto = DetailsPairDto.convertToDto(pair);
		return detailsPairDto;
    }

    private void updateTodaysFeedBack(PairRatingForm form, Pair pair, FeedBack feedback) {
        if(!feedback.getMessage().equals(form.getMessage())){
            feedback.setMessage(form.getMessage());
        }
        if(feedback.getRating() != form.getRating()){
            feedback.setRating(form.getRating());
            pair.setAverage(getNewPairAverage(pair));
        }
        feedbackRepository.save(feedback);
    }

    private void addNewFeedBackToPair(PairRatingForm form, Pair pair, LocalDate today) {
        FeedBack feedBack = new FeedBack(form.getRating(), form.getMessage(), today);
        feedBack.getPairList().add(pair);
        pair.getFeedbackList().add(feedBack);
        pair.setAverage(getNewPairAverage(pair));
        feedbackRepository.save(feedBack);
    }
    
    private float getNewPairAverage(Pair pair) {
        int sumOfFeedbacks = pair.getFeedbackList().stream().mapToInt(item -> item.getRating()).sum();
        float newAverage = (float) sumOfFeedbacks / pair.getFeedbackList().size();
        return newAverage;
    }

    public DetailsPairDto updatePair(@Valid PairUpdateForm form, HttpServletRequest request) {
        Pair pair = secureFindPairById(form.getPairId(), request);
        pair.setName(form.getName());
        pairRepository.save(pair);
        return DetailsPairDto.convertToDto(pair);
    }
    
    public void deletePair(Long pairId, HttpServletRequest request) {
        Pair pair = secureFindPairById(pairId, request);
        pairRepository.delete(pair);
    }
    
    private Long getUserId(HttpServletRequest request){
        return (Long) request.getAttribute("userId");
    }

    private Pair secureFindPairById(Long pairId, HttpServletRequest request){
        Optional<Pair> optPair = pairRepository.findById(pairId);
        if(optPair.isPresent() ){
            if(optPair.get().getUser().getId().equals(getUserId(request))){
                return optPair.get();
            }
            throw new DeniedDataAccessException("Acesso negado!");
        }
        throw new EmptyResultDataAccessException("Pair não encontrado", 1);
    }
}
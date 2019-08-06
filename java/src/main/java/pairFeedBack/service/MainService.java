package pairFeedBack.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pairFeedBack.dataTransferer.dto.DetailsPairDto;
import pairFeedBack.dataTransferer.dto.UserDto;
import pairFeedBack.dataTransferer.form.PairRatingForm;
import pairFeedBack.entity.FeedBack;
import pairFeedBack.entity.Pair;
import pairFeedBack.entity.User;
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

    public UserDto getUserDtoById(Long userId){
        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isPresent())
            return UserDto.convertToDto(optUser.get());
        return null;
    }

    public DetailsPairDto getDetailsPairDtoById (Long idPar, Long userId){
        Optional<Pair> optPair = pairRepository.findById(idPar);
        if(optPair.isPresent()){
            DetailsPairDto pairDto = DetailsPairDto.convertToDto(optPair.get());
            if(pairDto.getUserId() == userId)
                return pairDto;
        }
        return null;
    }

	public DetailsPairDto addFeedBackToPair(PairRatingForm form, Long userId) {
        Optional<Pair> optPair = pairRepository.findById(form.getPairId());
        if(optPair.isPresent() && optPair.get().getUser().getId().equals(userId))
        {
            LocalDate today = LocalDate.now();
            Optional<FeedBack> optFeedback = optPair.get().getFeedbackList()
                .stream()
                .filter(feedback -> feedback.getDate().isEqual(today))
                .findFirst();

            if(optFeedback.isPresent()){
                optFeedback.get().setRating(form.getRating());
                feedbackRepository.save(optFeedback.get());
            }
            else{
                FeedBack feedBack = new FeedBack(form.getRating(), today);
                feedBack.addPairToPairList(optPair.get());
                optPair.get().getFeedbackList().add(feedBack);
                feedbackRepository.save(feedBack);
            }
        }
		return null;
	}
}
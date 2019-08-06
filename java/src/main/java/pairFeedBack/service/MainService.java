package pairFeedBack.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pairFeedBack.dto.DetailsPairDto;
import pairFeedBack.dto.UserDto;
import pairFeedBack.entity.Pair;
import pairFeedBack.entity.User;
import pairFeedBack.repository.PairRepository;
import pairFeedBack.repository.UserRepository;

@Service
public class MainService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    PairRepository pairRepository;

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
}
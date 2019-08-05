package pairFeedBack.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pairFeedBack.dto.DetailsPairDto;
import pairFeedBack.dto.UserDto;
import pairFeedBack.entity.Pair;
import pairFeedBack.entity.User;
import pairFeedBack.repository.PairRepository;
import pairFeedBack.repository.UserRepository;

@RestController
@RequestMapping("/me")
public class MainController {

    @Autowired
    UserRepository UserRepository;
    
    @Autowired
    PairRepository pairRepository;

    @GetMapping("/api/test")
    public ResponseEntity<String> nothing() {
        return ResponseEntity.ok("Api rest");
    }

    @GetMapping
    public UserDto mainPage(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Optional<User> optUser = UserRepository.findById(userId);
        if (optUser.isPresent())
            return UserDto.convertToDto(optUser.get());
        return null;
    }

    @GetMapping("/pair/{id}")
    public DetailsPairDto getPair(@PathVariable Long id){
        Optional<Pair> optPair = pairRepository.findById(id);
        if(optPair.isPresent()){
            DetailsPairDto pairDto = DetailsPairDto.convertToDto(optPair.get());
            return pairDto;
        }
        return null;
    }
}
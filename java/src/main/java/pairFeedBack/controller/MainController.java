package pairFeedBack.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pairFeedBack.dto.UserDto;
import pairFeedBack.entity.User;
import pairFeedBack.repository.UserRepository;

@RestController
public class MainController {

    @Autowired
    UserRepository UserRepository;


    @GetMapping("/")
    @ResponseBody
    public String nothing() {
        return "Api rest";
    }

    @GetMapping("/me")
    @ResponseBody
    public UserDto mainPage(HttpServletRequest request) {
        Long userId = (Long)request.getAttribute("userId");
        Optional<User> optUser = UserRepository.findById(userId);
        if(optUser.isPresent())
            return UserDto.convertToDto(optUser.get());
        return null;
    }
}
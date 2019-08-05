package pairFeedBack.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pairFeedBack.dto.UserDto;
import pairFeedBack.entity.User;
import pairFeedBack.repository.UserRepository;

@RestController
@RequestMapping("/me")
public class MainController {

    @Autowired
    UserRepository UserRepository;

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
}
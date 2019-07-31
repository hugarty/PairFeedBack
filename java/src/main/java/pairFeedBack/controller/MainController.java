package pairFeedBack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pairFeedBack.dto.UserDto;
import pairFeedBack.service.UserService;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    @ResponseBody
    public List<UserDto> getAllUsers (){
        return userService.findAll();
    }
}
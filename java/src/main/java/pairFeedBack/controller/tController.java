package pairFeedBack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pairFeedBack.dto.UserDto;
import pairFeedBack.service.UserService;

@RestController
public class tController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    @ResponseBody
    public List<UserDto> getAllUsers (){
        return userService.findAll();
    }

}
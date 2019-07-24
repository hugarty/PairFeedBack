package pairFeedBack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pairFeedBack.repository.UserRepository;
import pairFeedBack.entity.User;

@RestController
public class tController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/get")
    @ResponseBody
    public Iterable<User> getAllUsers (){
        return userRepository.findAll();
    }
    
    @RequestMapping("/eae")
    @ResponseBody
    public String root(){
        return "S";
    }
}
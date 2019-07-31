package pairFeedBack.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pairFeedBack.dto.SignUpForm;
import pairFeedBack.dto.UserDto;
import pairFeedBack.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    @ResponseBody
    public List<UserDto> getAllUsers (){
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    @ResponseBody
    public UserDto getOne (@PathVariable Long id){
        return userService.findUserDto(id);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp (@RequestBody @Valid SignUpForm form) {
        UserDto userDto = userService.saveNewUser(form);
        URI uri = UriComponentsBuilder.newInstance()
                .path("/user/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }
}
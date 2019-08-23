package pairFeedBack.controller;

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

import pairFeedBack.dataTransferer.dto.TokenDto;
import pairFeedBack.dataTransferer.dto.UserDto;
import pairFeedBack.dataTransferer.form.SignUpForm;
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
    public ResponseEntity<TokenDto> signUp (@RequestBody @Valid SignUpForm form) {
        TokenDto tokenDto = userService.saveNewUser(form);
        return ResponseEntity.ok(tokenDto);
    }
}
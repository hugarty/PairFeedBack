package pairFeedBack.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import pairFeedBack.dto.LoginForm;
import pairFeedBack.dto.SignUpForm;
import pairFeedBack.dto.TokenDto;
import pairFeedBack.dto.UserDto;
import pairFeedBack.entity.User;
import pairFeedBack.service.TokenService;
import pairFeedBack.service.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    TokenService tokenService;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken userAuthenticationToken = form.convert();
        try {
            Authentication auth = authManager.authenticate(userAuthenticationToken);
            String token = tokenService.geraToken(auth);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp (@RequestBody @Valid SignUpForm form) {
        UserDto userDto = userService.saveNewUser(form);
        URI uri = UriComponentsBuilder.newInstance().path("/auth").build().toUri();
        return ResponseEntity.created(uri).body(userDto);
    }
}


package pairFeedBack.controller;

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

import pairFeedBack.dto.LoginDto;
import pairFeedBack.dto.TokenDto;
import pairFeedBack.service.TokenService;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody @Valid LoginDto dto) {
        UsernamePasswordAuthenticationToken userAuthenticationToken = dto.convert();
        try {
            Authentication auth = authManager.authenticate(userAuthenticationToken);
            String token = tokenService.geraToken(auth);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

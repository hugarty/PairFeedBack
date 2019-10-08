package pairFeedBack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import pairFeedBack.dataTransferer.dto.TokenDto;
import pairFeedBack.entity.User;

@Service
public class UserAuthService {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    TokenService tokenService;
    
    public TokenDto authenticateUserAndReturnToken(User user, String passwd){                
        UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(user.getEmail(), passwd);
        Authentication auth = authManager.authenticate(userAuth);
        String token = tokenService.geraToken(auth);
        return new TokenDto(token, "Bearer");
    }
}
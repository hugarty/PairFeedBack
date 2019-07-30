package pairFeedBack.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pairFeedBack.entity.User;

@Service
public class TokenService {

    @Value("${api.jwt.token.expiration}")
    String expirationValue;

    @Value("${api.jwt.token.senha.aleatoria}")
    String randomPasswd;

    @Value("${api.jwt.token.nome.projeto}")
    String projectName;

    public String geraToken(Authentication auth){
        User user = (User) auth.getPrincipal();
        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + Long.parseLong(expirationValue));
        return Jwts.builder()
            .setIssuer(projectName) 
            .setSubject(user.getId().toString())
            .setIssuedAt(today) 
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS256, randomPasswd)
            .compact();
      }
      
}
package pairFeedBack.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pairFeedBack.dto.ExceptionDto;

@RestControllerAdvice
public class AuthHandler {
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ExceptionDto authenticationExceptionHandler(AuthenticationException e) {
        ExceptionDto dto = new ExceptionDto("Login ou senha incorretos", e.getLocalizedMessage());
        return dto;
    }
}
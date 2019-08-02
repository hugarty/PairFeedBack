package pairFeedBack.exceptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pairFeedBack.dto.ExceptionDto;

@RestControllerAdvice
public class UserHandler {

    @Autowired
    MessageSource messageSource;

    // @ExceptionHandler(AuthenticationException.class)
    // @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    // public ExceptionDto authenticationExceptionHandler(AuthenticationException e) {
    //     ExceptionDto dto = new ExceptionDto();
    //     dto.setTitle("Login ou senha incorretos");
    //     dto.addDetails(e.getLocalizedMessage());
    //     return dto; 
    // }
}
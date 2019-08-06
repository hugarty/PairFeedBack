package pairFeedBack.exceptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pairFeedBack.dataTransferer.dto.ExceptionDto;

@RestControllerAdvice
public class GenericHandler {

    @Autowired
    MessageSource messageSource;

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ExceptionDto authenticationExceptionHandler(HttpMessageNotReadableException e) {
        ExceptionDto dto = new ExceptionDto("Requisição precisa ter um corpo");
        return dto;
    }
}
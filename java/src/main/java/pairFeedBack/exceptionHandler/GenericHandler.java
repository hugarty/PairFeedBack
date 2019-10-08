package pairFeedBack.exceptionHandler;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pairFeedBack.dataTransferer.dto.ExceptionDto;
import pairFeedBack.exception.DeniedDataAccessException;

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

    @ExceptionHandler(DeniedDataAccessException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public ExceptionDto deniedDataAccessExceptionHandler (DeniedDataAccessException e){
        ExceptionDto dto =  new ExceptionDto(e.getMessage());
        return dto;
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ExceptionDto emptyResultDataAccessExceptionHandler (EmptyResultDataAccessException e){
        ExceptionDto dto =  new ExceptionDto(e.getMessage());
        return dto;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ExceptionDto emptyResultDataAccessExceptionHandler (ConstraintViolationException e){
        ExceptionDto dto =  new ExceptionDto("Email já está sendo usado");
        return dto;
    }
}
package pairFeedBack.exceptionHandler;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pairFeedBack.dto.ExceptionDto;

@RestControllerAdvice
public class UserHandler {

    @Autowired
    MessageSource messageSource;
    // HttpMessageNotReadableException

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ExceptionDto authenticationExceptionHandler(MethodArgumentNotValidException e) {
        ExceptionDto dto = new ExceptionDto("Argumentos inválidos");
        Stream<FieldError> stream = e.getBindingResult().getFieldErrors().stream();

        stream.forEach(fieldErro -> {
            String message = messageSource.getMessage(fieldErro, LocaleContextHolder.getLocale());
            dto.addDetails(fieldErro.getField() + ":" + message);
        });

        return dto;
    }
}
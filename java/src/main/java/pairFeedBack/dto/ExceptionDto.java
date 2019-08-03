package pairFeedBack.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExceptionDto {

    String message;
    List<String> details;

    public ExceptionDto() {
        details = new ArrayList<>();
    }

    public ExceptionDto(String message) {
        this.message = message;
        details = new ArrayList<>();
    }

    public ExceptionDto(String message, String... details) {
        this.message = message;
        this.details = Arrays.asList(details);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String title) {
        this.message = title;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public void addDetails(String detail) {
        this.details.add(detail);
    }
}
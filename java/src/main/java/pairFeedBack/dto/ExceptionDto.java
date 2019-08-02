package pairFeedBack.dto;

import java.util.ArrayList;
import java.util.List;

public class ExceptionDto {

    String title;
    List<String> details = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
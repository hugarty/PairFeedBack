package pairFeedBack.dataTransferer.form;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PairAddForm {

    @NotNull @NotEmpty
    String name;

    @DecimalMax(value = "10", inclusive = true)
    @DecimalMin(value = "0", inclusive = true)
    Integer rating;


    @NotNull @Size(max = 200)
    String message;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
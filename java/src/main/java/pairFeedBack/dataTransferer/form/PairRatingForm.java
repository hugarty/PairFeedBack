package pairFeedBack.dataTransferer.form;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class PairRatingForm {
    @NotNull
    Long pairId;
    @DecimalMax(value = "10", inclusive = true) @DecimalMin(value = "0", inclusive = true)
    Integer rating;

    public Long getPairId() {
        return pairId;
    }

    public void setPairId(Long pairId) {
        this.pairId = pairId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

}
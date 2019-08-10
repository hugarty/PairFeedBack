package pairFeedBack.dataTransferer.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PairUpdateForm {

    @NotNull
    Long pairId;
    @NotEmpty
    String name;

    public Long getPairId() {
        return pairId;
    }

    public void setPairId(Long pairId) {
        this.pairId = pairId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
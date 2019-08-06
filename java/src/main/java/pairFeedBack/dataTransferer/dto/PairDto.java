package pairFeedBack.dataTransferer.dto;

import pairFeedBack.entity.Pair;

public class PairDto {
    Long id;
    String name;
    Float average;

    public PairDto(Long id, String name, Float average) {
        this.id = id;
        this.name = name;
        this.average = average;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAverage() {
        return average;
    }

    public void setAverage(Float average) {
        this.average = average;
    }
    
    public static PairDto convertToDto (Pair pair){
        return new PairDto(pair.getId(), pair.getName(), pair.getAverage());
    }
}
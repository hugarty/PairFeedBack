package pairFeedBack.dataTransferer.dto;

import java.time.LocalDate;

import pairFeedBack.entity.FeedBack;

public class FeedBackDto {

    Long id;
    Integer average;
    LocalDate date;

    public FeedBackDto(Long id, Integer average, LocalDate date) {
        this.id = id;
        this.average = average;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAverage() {
        return average;
    }

    public void setAverage(Integer average) {
        this.average = average;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static FeedBackDto convertToDto(FeedBack f){
        FeedBackDto feedDto = new FeedBackDto(f.getId(), f.getRating(), f.getDate());
        return feedDto;
    }
}
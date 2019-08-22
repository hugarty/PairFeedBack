package pairFeedBack.dataTransferer.dto;

import java.time.LocalDate;

import pairFeedBack.entity.FeedBack;

public class FeedBackDto {

    Long id;
    Integer rating;
    LocalDate date;

    public FeedBackDto(Long id, Integer rating, LocalDate date) {
        this.id = id;
        this.rating = rating;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
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
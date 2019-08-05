package pairFeedBack.dto;

import java.time.LocalDate;

import pairFeedBack.entity.FeedBack;

public class FeedBackDto {

    Long id;
    Integer nota;
    LocalDate date;

    public FeedBackDto(Long id, Integer nota, LocalDate date) {
        this.id = id;
        this.nota = nota;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static FeedBackDto convertToDto(FeedBack f){
        FeedBackDto feedDto = new FeedBackDto(f.getId(), f.getNota(), f.getDate());
        return feedDto;
    }
}
package pairFeedBack.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class FeedBack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer rating;

    @Column(name = "date", columnDefinition = "DATE")
    LocalDate date;

    @ManyToMany
    List<Pair> pairList = new ArrayList<>();

    public FeedBack (){
    }

    public FeedBack (Integer rating, LocalDate today){
        this.rating = rating;
        this.date = today;
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

    public List<Pair> getPairList() {
        return pairList;
    }

    public void setPairList(List<Pair> pairList) {
        this.pairList = pairList;
    }

    public void  addPairToPairList (Pair pair){
        this.pairList.add(pair);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
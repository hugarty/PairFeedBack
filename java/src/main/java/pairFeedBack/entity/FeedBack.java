package pairFeedBack.entity;

import java.time.LocalDate;
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
    Integer nota;

    @Column(name = "date", columnDefinition = "DATE")
    LocalDate date;

    @ManyToMany
    List<Pair> pairList;

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

    public List<Pair> getPairList() {
        return pairList;
    }

    public void setPairList(List<Pair> pairList) {
        this.pairList = pairList;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
package pairFeedBack.dto;

import java.util.List;
import java.util.stream.Collectors;

import pairFeedBack.entity.FeedBack;
import pairFeedBack.entity.Pair;

public class DetailsPairDto {

    Long id;
    Long userId;
    String name;
    Float average;
    List<FeedBackDto> feedBackDtoList;

    public DetailsPairDto(Long id, Long userId, String name, Float average, List<FeedBack> feedbackList) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.average = average;
        this.feedBackDtoList = feedbackList.stream()
        .map(FeedBackDto::convertToDto)
        .collect(Collectors.toList());
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

    public List<FeedBackDto> getFeedBackDtoList() {
        return feedBackDtoList;
    }

    public void setFeedBackDtoList(List<FeedBackDto> feedBackDtoList) {
        this.feedBackDtoList = feedBackDtoList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public static DetailsPairDto convertToDto(Pair pair) {
        DetailsPairDto dto = new DetailsPairDto(pair.getId(), pair.getUser().getId(), 
            pair.getName(), pair.getAverage(), pair.getFeedbackList());
        return dto;
    }
}
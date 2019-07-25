package pairFeedBack.dto;

import pairFeedBack.entity.User;

import java.util.List;

import pairFeedBack.entity.Pair;

public class UserDto {
    Long id;
    String name;
    String email;
    List<Pair> pairs;

    public UserDto(Long id, String name, String email, List<Pair> pairs) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pairs = pairs;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Pair> getPairs() {
        return pairs;
    }

    public void setPairs(List<Pair> pairs) {
        this.pairs = pairs;
    }

    public static UserDto convertToDto(User user){
        UserDto dto = new UserDto(user.getId(), user.getName(),
         user.getEmail(), user.getPairs());
        return dto;
    }
}
package pairFeedBack.dataTransferer.dto;

import java.util.List;
import java.util.stream.Collectors;

import pairFeedBack.entity.User;
import pairFeedBack.dataTransferer.dto.PairDto;

public class UserDto {
    Long id;
    String name;
    String email;
    List<PairDto> pairsDto;

    public static UserDto convertToDto(User user){
        List<PairDto> pairsDto = user.getPairs().stream()
            .map(PairDto::convertToDto)
            .collect(Collectors.toList());
        UserDto dto = new UserDto(user.getId(), user.getName(),
            user.getEmail(), pairsDto);
        return dto;
    }

    public UserDto(Long id, String name, String email, List<PairDto> pairsDto) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pairsDto = pairsDto;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PairDto> getPairsDto() {
        return pairsDto;
    }

    public void setPairsDto(List<PairDto> pairsDto) {
        this.pairsDto = pairsDto;
    }
}
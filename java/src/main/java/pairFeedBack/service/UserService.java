package pairFeedBack.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pairFeedBack.dto.SignUpForm;
import pairFeedBack.dto.UserDto;
import pairFeedBack.entity.User;
import pairFeedBack.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDto findUserDto (Long id){
        return null;
    }

    public List<UserDto> findAll(){
        List<User> users = userRepository.findAll();
        List<UserDto> usersDto = users.stream()
            .map(UserDto::convertToDto)
            .collect(Collectors.toList());
    
        return usersDto;
    }

    @Transactional
	public UserDto saveNewUser(SignUpForm form) {
        String senha = new BCryptPasswordEncoder().encode(form.getSenha());
        User user = userRepository.save(new User(form.getName(), form.getEmail(), senha));
        UserDto userDto = UserDto.convertToDto(user);
        return userDto;
	}
}
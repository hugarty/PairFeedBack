package pairFeedBack.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pairFeedBack.dataTransferer.dto.TokenDto;
import pairFeedBack.dataTransferer.dto.UserDto;
import pairFeedBack.dataTransferer.form.SignUpForm;
import pairFeedBack.entity.User;
import pairFeedBack.repository.PerfilRepository;
import pairFeedBack.repository.UserRepository;
import pairFeedBack.utils.PerfilEnum;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PerfilRepository perfilRepository;
    @Autowired
    UserAuthService userAuthService;
    @Autowired
    MainService mainService;

    public UserDto findUserDto (Long id){
        Optional<User> optUser = userRepository.findById(id);
        if(optUser.isPresent())
            return UserDto.convertToDto(optUser.get());
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
	public TokenDto saveNewUser(SignUpForm form) {
        String passwd = new BCryptPasswordEncoder().encode(form.getPasswd());
        User user = new User(form.getName(), form.getEmail(), passwd);
        user.addPerfil(perfilRepository.findByPerfilEnum(PerfilEnum.USER));
        userRepository.save(user);
        mainService.addExamplesPairs(user);
        TokenDto tokenDto = userAuthService.authenticateUserAndReturnToken(user, form.getPasswd());
        return tokenDto;
	}
}
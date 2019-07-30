package pairFeedBack.configuration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pairFeedBack.entity.User;
import pairFeedBack.repository.UserRepository;

@Service
public class UserAuthenticationService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optUser = userRepository.findByEmail(email);
        if(optUser.isPresent())
            return optUser.get();

        throw new UsernameNotFoundException("Usuário não encontrado!");
	}
}
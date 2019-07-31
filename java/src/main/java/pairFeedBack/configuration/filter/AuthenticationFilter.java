package pairFeedBack.configuration.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import pairFeedBack.entity.User;
import pairFeedBack.repository.UserRepository;
import pairFeedBack.service.TokenService;

public class AuthenticationFilter extends OncePerRequestFilter {

    TokenService tokenService;
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        tryAuthenticateUserAndSetUserIdInRequest(request);
        filterChain.doFilter(request, response);
    }

    private void tryAuthenticateUserAndSetUserIdInRequest(HttpServletRequest request) {
        String token = getToken(request);
        boolean isTokenValido = tokenService.isTokenValido(token);
        if (isTokenValido) {
            Long userId = tokenService.getIdUsuario(token);
            request.setAttribute("userId", userId);
            Optional<User> optUser = userRepository.findById(userId);
            authenticateUser(optUser);
        }
    }

    private void authenticateUser(Optional<User> optUser) {
        if (optUser.isPresent()) {
            UsernamePasswordAuthenticationToken userAuthentication = new UsernamePasswordAuthenticationToken(
                    optUser.get().getEmail(), null, optUser.get().getPerfis());
            SecurityContextHolder.getContext().setAuthentication(userAuthentication);
        }
    }

    private String getToken(HttpServletRequest request) {
        String value = request.getHeader("Authorization");
        String token = "";
        if(value != null)
            token = value.substring(7);
        
        return token;
    }

    public AuthenticationFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }
}
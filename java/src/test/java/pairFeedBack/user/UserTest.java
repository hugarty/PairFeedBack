package pairFeedBack.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import pairFeedBack.dataTransferer.dto.TokenDto;
import pairFeedBack.dataTransferer.form.LoginForm;
import pairFeedBack.dataTransferer.form.SignUpForm;
import pairFeedBack.utils.Utils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserTest {

    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate restTemplate;

    private String email = "jose@jose.com";
    private String passwd = "passwdteste";
    
    @Test 
    public void shouldSignUp () throws URISyntaxException{
        URI uri = Utils.getUriForPath("/signup", port);
        
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setEmail(email);
        signUpForm.setName("ArrobaRotemaio");
        signUpForm.setPasswd(passwd);

        HttpEntity<SignUpForm> requestEntity = new HttpEntity<>(signUpForm);
        ResponseEntity<TokenDto> result = this.restTemplate.postForEntity(uri, requestEntity, TokenDto.class);
        
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getToken().length()).isGreaterThan(5);
    }

    @Test
    public void trySignIn() throws URISyntaxException {
        URI uri = Utils.getUriForPath("/auth", port);
        LoginForm loginForm = new LoginForm();
        loginForm.setEmail(email);
        loginForm.setPasswd(passwd);

        HttpEntity<LoginForm> request = new HttpEntity<>(loginForm);
        ResponseEntity<TokenDto> result = this.restTemplate.postForEntity(uri, request, TokenDto.class);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getTipo()).containsIgnoringCase("Bearer");
        assertThat(result.getBody()).getClass().equals(TokenDto.class);
    }
}
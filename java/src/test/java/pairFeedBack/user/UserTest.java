package pairFeedBack.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import pairFeedBack.dataTransferer.dto.UserDto;
import pairFeedBack.dataTransferer.form.SignUpForm;
import pairFeedBack.utils.Utils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserTest {

    @Value("${api.test.parameters.email.user}")
    String userEmail;

    @Value("${api.test.parameters.passwd.user}")
    String userPasswd;

    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate restTemplate;


    @Test 
    public void shouldSignUp () throws URISyntaxException{
        URI uri = Utils.getUriForPath("/signup", port);
        
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setEmail("jose@jose.com");
        signUpForm.setName("ArrobaRotemaio");
        signUpForm.setSenha("senhateste");

        HttpEntity<SignUpForm> requestEntity = new HttpEntity<>(signUpForm);
        ResponseEntity<UserDto> result = this.restTemplate.postForEntity(uri, requestEntity, UserDto.class);
        
        assertThat(result.getStatusCodeValue()).isEqualTo(201);
        assertThat(result.getBody().getName().equals(signUpForm.getName()));
    }
}
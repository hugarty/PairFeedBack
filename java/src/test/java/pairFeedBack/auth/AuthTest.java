package pairFeedBack.auth;

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

import pairFeedBack.dto.LoginForm;
import pairFeedBack.dto.TokenDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AuthTest {

    @Value("${api.test.parameters.email.user}")
    String userEmail;

    @Value("${api.test.parameters.passwd.user}")
    String userPasswd;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate
                .getForObject("http://localhost:" + port + "/me/api/test",String.class)
            ).contains("rest");
    }

    @Test
    public void trySignInShouldNotWork() throws URISyntaxException {
        String baseUrl = "http://localhost:"+ port +"/auth";
        URI uri = new URI(baseUrl);
        LoginForm loginForm = new LoginForm();
        loginForm.setEmail(userEmail);
        loginForm.setSenha(userPasswd + "abc");

        HttpEntity<LoginForm> request = new HttpEntity<>(loginForm);
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
        assertThat(result.getStatusCodeValue()).isEqualTo(400);
    }

    @Test
    public void trySignInShouldWork() throws URISyntaxException {
        String baseUrl = "http://localhost:"+ port +"/auth";
        URI uri = new URI(baseUrl);
        LoginForm loginForm = new LoginForm();
        loginForm.setEmail(userEmail);
        loginForm.setSenha(userPasswd);

        HttpEntity<LoginForm> request = new HttpEntity<>(loginForm);
        ResponseEntity<TokenDto> result = this.restTemplate.postForEntity(uri, request, TokenDto.class);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getTipo()).containsIgnoringCase("Bearer");
        assertThat(result.getBody()).getClass().equals(TokenDto.class);
    }
}
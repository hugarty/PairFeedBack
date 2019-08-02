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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import pairFeedBack.dto.LoginForm;
import pairFeedBack.dto.TokenDto;

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
    public void shouldAccessContentMe() throws URISyntaxException {
        TokenDto tokenDto = doLoginReturnTokenDto();
        String baseUrl = "http://localhost:" + port + "/me";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();
        String tipoAndToken = tokenDto.getTipo() + " " + tokenDto.getToken();

        headers.add("Authorization", tipoAndToken);
        HttpEntity<?> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> result = this.restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).contains("Cleitor");
    }

    public TokenDto doLoginReturnTokenDto() throws URISyntaxException{
        String baseUrl = "http://localhost:" + port + "/auth";
        URI uri = new URI(baseUrl);
        LoginForm loginForm = new LoginForm();
        loginForm.setEmail(userEmail);
        loginForm.setSenha(userPasswd);

        HttpEntity<LoginForm> request = new HttpEntity<>(loginForm);
        ResponseEntity<TokenDto> result = this.restTemplate.postForEntity(uri, request, TokenDto.class);
        return result.getBody();
    }
}
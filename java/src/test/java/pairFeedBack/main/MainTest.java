package pairFeedBack.main;

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

import pairFeedBack.dataTransferer.dto.TokenDto;
import pairFeedBack.dataTransferer.form.LoginForm;
import pairFeedBack.dataTransferer.form.PairRatingForm;
import pairFeedBack.utils.Utils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MainTest {

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
        URI uri = Utils.getUriForPath("/me", port);
        HttpHeaders header = doLoginAndReturnAuthorizationHeader();

        HttpEntity<?> requestEntity = new HttpEntity<>(null, header);
        ResponseEntity<String> result = this.restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).contains("Cleitor");
        assertThat(result.getBody()).contains("Par");
        assertThat(result.getBody()).contains("Par3");
    }

    @Test
    public void shouldNotAccessContentMe() throws URISyntaxException {
        URI uri = Utils.getUriForPath("/me", port);
        HttpHeaders headers = Utils.getHeaderWithInvalidAuthorization("INVALID_TOKEN");

        HttpEntity<?> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> result = this.restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

        assertThat(result.getStatusCodeValue()).isEqualTo(403);
    }

    @Test
    public void shouldPostNewFeedBackInPair() throws URISyntaxException {
        URI uri = Utils.getUriForPath("/me/pair", port);
        HttpHeaders header = doLoginAndReturnAuthorizationHeader();

        PairRatingForm form = new PairRatingForm();
        form.setPairId(1L);
        form.setRating(6);

        HttpEntity<PairRatingForm> requestEntity = new HttpEntity<>(form, header);
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, requestEntity, String.class);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }

    private HttpHeaders doLoginAndReturnAuthorizationHeader() throws URISyntaxException {
        TokenDto tokenDto = doLoginReturnTokenDto();
        HttpHeaders headers = Utils.getHeaderWithAuthorization(tokenDto);
        return headers;
    }


    private TokenDto doLoginReturnTokenDto() throws URISyntaxException {
        URI uri = Utils.getUriForPath("/auth", port);
        LoginForm loginForm = new LoginForm();
        loginForm.setEmail(userEmail);
        loginForm.setSenha(userPasswd);

        HttpEntity<LoginForm> request = new HttpEntity<>(loginForm);
        ResponseEntity<TokenDto> result = this.restTemplate.postForEntity(uri, request, TokenDto.class);
        return result.getBody();
    }

}
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import pairFeedBack.dataTransferer.dto.DetailsPairDto;
import pairFeedBack.dataTransferer.dto.ExceptionDto;
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
    public void doPostFeedbackInPairTests () throws URISyntaxException {
        URI uri = Utils.getUriForPath("/me/pair", port);
        HttpHeaders header = doLoginAndReturnAuthorizationHeader();
        shouldPostNewFeedBackInPair(uri, header);
        shouldNotPostNewFeedBackInPair(uri, header);
        shouldEditPostFeedBackInPair(uri, header);
    }

    public void shouldPostNewFeedBackInPair(URI uri, HttpHeaders header) throws URISyntaxException {
        PairRatingForm form = new PairRatingForm();
        form.setPairId(1L);
        form.setRating(6);
        HttpEntity<PairRatingForm> requestEntity = new HttpEntity<>(form, header);
        ResponseEntity<DetailsPairDto> result = this.restTemplate.postForEntity(uri, requestEntity, DetailsPairDto.class);
        assertThat(result.getStatusCodeValue()).isEqualTo(201);
    }

    public void shouldNotPostNewFeedBackInPair(URI uri, HttpHeaders header)  throws URISyntaxException {
        PairRatingForm form = new PairRatingForm();
        form.setPairId(5L);
        form.setRating(2);
        HttpEntity<PairRatingForm> requestEntity = new HttpEntity<>(form, header);
        ResponseEntity<ExceptionDto> result = this.restTemplate.postForEntity(uri, requestEntity, ExceptionDto.class);
        assertThat(result.getStatusCodeValue()).isEqualTo(403);
        assertThat(result.getBody().getMessage()).containsIgnoringCase("acesso negado");
    }

    public void shouldEditPostFeedBackInPair(URI uri, HttpHeaders header)  throws URISyntaxException {
        PairRatingForm form = new PairRatingForm();
        form.setPairId(1L);
        form.setRating(4);
        HttpEntity<PairRatingForm> requestEntity = new HttpEntity<>(form, header);
        ResponseEntity<DetailsPairDto> result = this.restTemplate.postForEntity(uri, requestEntity, DetailsPairDto.class);
        assertThat(result.getStatusCodeValue()).isEqualTo(201);
        assertThat(result.getBody().getFeedBackDtoList().get(1).getNota()).isEqualTo(4);
        assertThat(result.getBody().getFeedBackDtoList().size()).isEqualTo(2);
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
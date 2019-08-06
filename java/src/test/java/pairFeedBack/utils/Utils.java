package pairFeedBack.utils;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;

import pairFeedBack.dataTransferer.dto.TokenDto;


public class Utils {
    public static URI getUriForPath(String path, int port) throws URISyntaxException {
        String baseUri = "http://localhost:" + port + path;
        URI uri = new URI(baseUri);
        return uri;
    }


    public static HttpHeaders getHeaderWithAuthorization(TokenDto tokenDto) {
        String tipoAndTokenDirty = tokenDto.getTipo() + " " + tokenDto.getToken();
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", tipoAndTokenDirty);
        return header;
    }

    public static HttpHeaders getHeaderWithInvalidAuthorization(String invalidToken) {
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", invalidToken);
        return header;
    }
}
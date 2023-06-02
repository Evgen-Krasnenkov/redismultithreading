package org.kras.redismultithreading.util;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

@Service
public class ExternalServiceCall<T> {
    public static final int SECONDS = 7;
    private final WebClient webClient = WebClient.create();

    public T getPart(String url, Class<T> tClass, String number) throws URISyntaxException {
        T t = webClient.get()
                .uri(new URI(url))
                .retrieve()
                .onStatus(resp -> resp.is4xxClientError() || resp.is5xxServerError(),
                        ClientResponse::createException)
                .bodyToMono(tClass)
                .blockOptional(Duration.ofSeconds(SECONDS))
                .orElseThrow(() -> new RuntimeException("3rd Party API fails"));
        return t;
    }
}

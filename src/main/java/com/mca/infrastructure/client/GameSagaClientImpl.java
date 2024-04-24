package com.mca.infrastructure.client;

import com.mca.domain.ports.in.client.GameSagaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class GameSagaClientImpl implements GameSagaClient {

    private final WebClient webClient;

    @Autowired
    public GameSagaClientImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:3000").build();
    }




    @Override
    public Mono<String[]> getRelatedSagas(String gameId) {
        return webClient.get()
                .uri("/game-saga/{gameId}/related-sagas", gameId)
                .retrieve()
                .bodyToMono(String[].class);
    }

}

package com.mca.domain.ports.in.client;

import reactor.core.publisher.Mono;


public interface GameSagaClient {

      Mono<String[]> getRelatedSagas(String gameId);
}

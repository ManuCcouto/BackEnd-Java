package com.mca.infrastructure.rest.controller;


import com.mca.domain.ports.in.gamesagas.GetGameSagasUseCase;
import com.mca.infrastructure.rest.VideoGameDto;
import com.mca.infrastructure.rest.VideoGameMapperDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/game/")
public class SagaController {

    private GetGameSagasUseCase getGameSagasUseCase;

    private VideoGameMapperDto videoGameMapperDto;
    @Autowired
    public SagaController(GetGameSagasUseCase videoGamesUseCase, VideoGameMapperDto videoGameMapperDto) {
        this.getGameSagasUseCase = videoGamesUseCase;
        this.videoGameMapperDto = videoGameMapperDto;
    }

    @GetMapping("{gameId}/saga")
    public ResponseEntity<List<VideoGameDto>> getRelatedSagas(@PathVariable Long gameId) {
        List<VideoGameDto> gameDto = videoGameMapperDto.videoGameListToDtoList(getGameSagasUseCase.getRelatedVideoGames(gameId));
        return ResponseEntity.ok(gameDto);

    }


}

package com.mca.domain.ports.in.gamesagas;

import com.mca.domain.model.VideoGame;

import java.util.List;

public interface GetGameSagasUseCase {
    List<VideoGame> getRelatedVideoGames(Long id);
}

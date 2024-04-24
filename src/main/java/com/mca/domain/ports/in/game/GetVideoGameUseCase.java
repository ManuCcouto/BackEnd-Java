package com.mca.domain.ports.in.game;


import com.mca.domain.model.VideoGame;


public interface GetVideoGameUseCase {

    VideoGame getVideoGameById(long id);

    VideoGame getVideoGameAvailabilityById(long gameId);



}

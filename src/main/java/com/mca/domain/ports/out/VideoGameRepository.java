package com.mca.domain.ports.out;

import com.mca.domain.model.VideoGame;
import java.time.LocalDateTime;
import java.util.Optional;
public interface VideoGameRepository {


    Optional<VideoGame> findVideoGameById(Long id);


    Optional<VideoGame> findVideoGameWithActivePromotionsAndLatestStock(Long videoGameId, LocalDateTime date);


}

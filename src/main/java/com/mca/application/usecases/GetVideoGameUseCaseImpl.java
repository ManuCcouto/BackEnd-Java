package com.mca.application.usecases;

import com.mca.application.utils.DateUtils;

import com.mca.domain.model.VideoGame;
import com.mca.domain.ports.in.game.GetVideoGameUseCase;
import com.mca.domain.ports.out.VideoGameRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class GetVideoGameUseCaseImpl implements GetVideoGameUseCase {

    private VideoGameRepository videoGameRepository;
    @Value("${date}")
    private String date;
    @Autowired
    public GetVideoGameUseCaseImpl(VideoGameRepository videoGameRepository) {
        this.videoGameRepository = videoGameRepository;
    }
    @Override
    public VideoGame getVideoGameById(long id) {
        Optional<VideoGame> optionalVideoGame = videoGameRepository.findVideoGameById(id);
        return optionalVideoGame.orElseThrow(() -> new ResourceNotFoundException("VideoGame not found with id: " + id));
    }
    @Override
    public VideoGame getVideoGameAvailabilityById(long gameId) {
        LocalDateTime dateTime = DateUtils.getDateTime(date);
        Optional<VideoGame> optionalVideoGame = videoGameRepository.findVideoGameWithActivePromotionsAndLatestStock(gameId, dateTime);
        return optionalVideoGame.orElseThrow(() -> new ResourceNotFoundException("VideoGame not found with id: " + gameId));
    }


}

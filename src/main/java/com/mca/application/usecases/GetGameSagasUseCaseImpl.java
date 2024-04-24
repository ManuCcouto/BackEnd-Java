package com.mca.application.usecases;

import com.mca.application.utils.DateUtils;
import com.mca.domain.exception.NoSuchElementException;
import com.mca.domain.model.VideoGame;
import com.mca.domain.ports.in.gamesagas.GetGameSagasUseCase;
import com.mca.domain.ports.out.VideoGameRepository;
import com.mca.infrastructure.client.GameSagaClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Service
public class GetGameSagasUseCaseImpl  implements GetGameSagasUseCase {

    private VideoGameRepository videoGameRepository;


    @Value("${date}")
    private String date;

    private final GameSagaClientImpl gameSagaClient;

    @Autowired
    public GetGameSagasUseCaseImpl(VideoGameRepository videoGameRepository,  GameSagaClientImpl externalServiceClient) {
        this.videoGameRepository = videoGameRepository;
        this.gameSagaClient = externalServiceClient;
    }






    @Override
    public List<VideoGame> getRelatedVideoGames(Long id) {
        Mono<String[]> relatedSagasMono = gameSagaClient.getRelatedSagas(String.valueOf(id));

        LocalDateTime dateTime = DateUtils.getDateTime(date);
        List<VideoGame> relatedVideoGames = new ArrayList<>();
        relatedSagasMono.flatMapMany(Flux::fromArray)
                .map(Long::parseLong)
                .flatMap(gameId -> {
                    Optional<VideoGame> videoGameOptional = videoGameRepository.findVideoGameWithActivePromotionsAndLatestStock(gameId, dateTime);
                    videoGameOptional.ifPresent(relatedVideoGames::add);
                    return Mono.justOrEmpty(videoGameOptional);
                })
                .blockLast();
        if (relatedVideoGames.isEmpty()) {
            throw new NoSuchElementException("No se encontraron juegos relacionados para los IDs en el array: " + Arrays.toString(relatedSagasMono.block()));
        }

        return relatedVideoGames;

    }
}

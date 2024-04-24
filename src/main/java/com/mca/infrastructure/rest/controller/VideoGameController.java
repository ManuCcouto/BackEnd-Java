package com.mca.infrastructure.rest.controller;



import com.mca.domain.ports.in.game.GetVideoGameUseCase;
import com.mca.infrastructure.rest.VideoGameDto;
import com.mca.infrastructure.rest.VideoGameMapperDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/game")
public class VideoGameController {



    private GetVideoGameUseCase videoGamesUseCase;

    private VideoGameMapperDto videoGameMapperDto;
    @Autowired
    public VideoGameController(GetVideoGameUseCase videoGamesUseCase, VideoGameMapperDto videoGameMapperDto) {
        this.videoGamesUseCase = videoGamesUseCase;
        this.videoGameMapperDto = videoGameMapperDto;
    }


    @GetMapping("/{id}")
    public ResponseEntity<VideoGameDto> getVideoGameId(@PathVariable Long id)  {
        VideoGameDto gameDto = videoGameMapperDto.videoGameToDto(videoGamesUseCase.getVideoGameAvailabilityById(id));
        return (ResponseEntity<VideoGameDto>) ResponseEntity.ok( gameDto);

    }


}


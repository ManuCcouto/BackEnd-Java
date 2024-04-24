package com.mca.infrastructure.persistence.repository;

import com.mca.domain.model.VideoGame;
import com.mca.domain.ports.out.VideoGameRepository;
import com.mca.infrastructure.persistence.entities.VideoGameEntity;
import com.mca.infrastructure.persistence.mapper.VideoGameMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Repository
public class VideoGameRepositoryAdapter implements VideoGameRepository {
    private final JpaVideoGameRepository jpaVideoGameRepository;
    private static final Logger LOG = LoggerFactory.getLogger(VideoGameRepositoryAdapter.class);
    private VideoGameMapper videoGameMapper;
    @Autowired
    public VideoGameRepositoryAdapter(JpaVideoGameRepository repository, VideoGameMapper videoGameMapper) {
        this.jpaVideoGameRepository = repository;
        this.videoGameMapper = videoGameMapper;
    }





    @Override
    public Optional<VideoGame> findVideoGameById(Long id) {
        return jpaVideoGameRepository.findById(id)
                .map(videoGameEntity -> videoGameMapper.toVideoGameDomain(videoGameEntity));
    }

    @Override
    public Optional<VideoGame> findVideoGameWithActivePromotionsAndLatestStock(Long videoGameId, LocalDateTime date){
        Date dateAsDate = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
        Optional<VideoGameEntity> videoGameEntityOptional  = jpaVideoGameRepository.findVideoGameWithActivePromotionAndLatestStock(videoGameId, dateAsDate);
        if (videoGameEntityOptional.isPresent()) {
            VideoGameEntity videoGameEntity = videoGameEntityOptional.get();
            LOG.info("findVideoGameWithActivePromotionAndLatestStock {} {}", videoGameId, videoGameEntity.toString());
            return Optional.of(videoGameMapper.toVideoGameDomain(videoGameEntity));
        } else {
            LOG.info("VideoGameEntity not found for ID: {}", videoGameId);
            return Optional.empty();
        }
    }


}

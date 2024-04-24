package com.mca.infrastructure.persistence.mapper;

import com.mca.domain.model.VideoGame;
import com.mca.infrastructure.persistence.entities.VideoGameEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper(componentModel = "spring", uses = StockMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VideoGameMapper {


    VideoGameEntity toEntity(VideoGame videoGame);

    @Mapping(target = "stock", source = "stocks")
    VideoGame toVideoGameDomain(VideoGameEntity videoGame);

    List<VideoGameEntity> toEntityList(List<VideoGame> videoGame);


    List<VideoGame> toVideoGameList(List<VideoGameEntity> entities);
}



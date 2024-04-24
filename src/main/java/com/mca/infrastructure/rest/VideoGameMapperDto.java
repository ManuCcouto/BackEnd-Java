package com.mca.infrastructure.rest;

import com.mca.domain.model.Stock;
import com.mca.domain.model.VideoGame;
import com.mca.domain.model.Promotion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
@Mapper(componentModel = "spring", uses = Stock.class,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VideoGameMapperDto {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(expression = "java(getFirstPromotionPrice(videoGame))", target = "price")
    @Mapping(expression = "java(getFirstStockAvailability(videoGame))", target = "availability")
    VideoGameDto videoGameToDto(VideoGame videoGame);

    List<VideoGameDto> videoGameListToDtoList(List<VideoGame> videoGameList);
    default BigDecimal getFirstPromotionPrice(VideoGame videoGame) {
        return Optional.ofNullable(videoGame.getPromotions())
                .flatMap(promotions -> promotions.stream()
                        .findFirst()
                        .map(Promotion::getPrice))
                .orElse(null);
    }
    default Boolean getFirstStockAvailability(VideoGame videoGame) {
        return Optional.ofNullable(videoGame.getStock())
                .flatMap(stocks -> stocks.stream()
                        .findFirst()
                        .map(Stock::isAvailability))
                .orElse(null);
    }
}


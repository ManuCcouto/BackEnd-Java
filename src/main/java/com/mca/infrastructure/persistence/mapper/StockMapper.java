package com.mca.infrastructure.persistence.mapper;

import com.mca.domain.model.Stock;
import com.mca.infrastructure.persistence.entities.StockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StockMapper {

    @Mapping(target = "lastUpdated", source = "lastUpdated", dateFormat = "yyyy-MM-dd HH:mm:ss")
    StockEntity toEntity(Stock stock);

    @Mapping(target = "lastUpdated", source = "lastUpdated", dateFormat = "yyyy-MM-dd HH:mm:ss")
    Stock toStockDomain(StockEntity entity);

    List<StockEntity> toEntityList(List<Stock> stocks);

    List<Stock> toStockList(List<StockEntity> entities);
}



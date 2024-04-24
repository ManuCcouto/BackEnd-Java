package com.mca.infrastructure.persistence.mapper;

import com.mca.domain.model.Promotion;
import com.mca.infrastructure.persistence.entities.PromotionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PromotionMapper {

    @Mappings({@Mapping(target = "validFrom", source = "validFrom", dateFormat = "yyyy-MM-dd'T'HH:mm:ss"),

    })
    PromotionEntity toEntity(Promotion promotion);

    @Mappings({@Mapping(target = "validFrom", source = "validFrom", dateFormat = "yyyy-MM-dd'T'HH:mm:ss"),

    })
    Promotion toPromotionDomain(PromotionEntity entity);

    List<PromotionEntity> toEntityList(List<Promotion> promotions);

    List<Promotion> toPromotionList(List<PromotionEntity> entities);
}

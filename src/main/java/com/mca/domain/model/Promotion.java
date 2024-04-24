package com.mca.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
public class Promotion {
    private Long id;
    private LocalDateTime validFrom;
    private BigDecimal price;


}

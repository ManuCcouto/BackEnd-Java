package com.mca.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VideoGame {
    private Long id;
    private String title;
    private Set<Promotion> promotions;
    private Set<Stock> stock;
}

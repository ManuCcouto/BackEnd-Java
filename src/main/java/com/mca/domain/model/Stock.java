package com.mca.domain.model;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    private Long id;
    private boolean availability;
    private LocalDateTime lastUpdated;
    private VideoGame videoGame;

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", availability=" + availability +
                ", lastUpdated=" + lastUpdated +
                ", videoGame=" + videoGame.getId() +
                '}';
    }
}
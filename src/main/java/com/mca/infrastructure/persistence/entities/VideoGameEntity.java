package com.mca.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.util.Set;


@Getter
@Setter
@Entity
@ToString
@Table(name = "VIDEOGAME")
public class VideoGameEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @OneToMany(mappedBy = "videoGame", fetch = FetchType.EAGER)
    private Set<PromotionEntity> promotions;

    @OneToMany(mappedBy = "videoGame", fetch = FetchType.EAGER)
    private Set<StockEntity> stocks;

}



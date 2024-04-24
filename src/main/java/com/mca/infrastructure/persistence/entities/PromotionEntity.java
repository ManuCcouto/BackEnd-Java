package com.mca.infrastructure.persistence.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity

@Table(name = "PROMOTION")
public class PromotionEntity  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "VALID_FROM", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date validFrom;

    @Column(name = "PRICE", nullable = false, precision = 5, scale = 2)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VIDEOGAME_ID", nullable = false)
    private VideoGameEntity videoGame;

    @Override
    public String toString() {
        return "PromotionEntity{" +
                "id=" + id +
                ", validFrom=" + validFrom +
                ", price=" + price +
                '}';
    }
}

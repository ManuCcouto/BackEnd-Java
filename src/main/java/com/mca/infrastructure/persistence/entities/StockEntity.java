package com.mca.infrastructure.persistence.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "STOCK")
public class StockEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "AVAILABILITY", nullable = false)
    private Boolean availability;

    @Column(name = "LAST_UPDATED", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date lastUpdated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VIDEOGAME_ID", nullable = false)
    private VideoGameEntity videoGame;

    @Override
    public String toString() {
        return "StockEntity{" +
                "id=" + id +
                ", availability=" + availability +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}

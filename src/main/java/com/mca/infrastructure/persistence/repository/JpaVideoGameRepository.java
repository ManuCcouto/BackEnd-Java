package com.mca.infrastructure.persistence.repository;
import com.mca.infrastructure.persistence.entities.VideoGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface JpaVideoGameRepository extends JpaRepository<VideoGameEntity, Long> {

    @Query("SELECT DISTINCT v FROM VideoGameEntity v " +
            "JOIN FETCH v.promotions p " +
            "LEFT JOIN FETCH v.stocks s " +
            "WHERE v.id IN :videoGameIds " +
            "AND p.id IN " +
            "(SELECT MIN(p2.price) FROM PromotionEntity p2 WHERE p2.videoGame.id = v.id AND p2.validFrom <= :date ORDER BY p2.price ASC) " +
            "AND s.id = (SELECT s2.id FROM StockEntity s2 WHERE s2.videoGame.id = v.id AND s2.lastUpdated <= :date ORDER BY s2.lastUpdated DESC)")
    List<VideoGameEntity> findVideoGamesWithActivePromotionsAndLatestStock(@Param("videoGameIds") List<Long> videoGameIds, @Param("date") Date date);


    @Query("SELECT v FROM VideoGameEntity v " +
            "JOIN FETCH v.promotions p " +
            "LEFT JOIN FETCH v.stocks s " +
            "WHERE v.id = :videoGameId " +
            "AND p.id = (SELECT MIN(p2.id) FROM PromotionEntity p2 WHERE p2.videoGame.id = v.id AND p2.validFrom <= :date) " +
            "AND s.id = (SELECT MAX(s2.id) FROM StockEntity s2 WHERE s2.videoGame.id = v.id AND s2.lastUpdated <= :date)")
    Optional<VideoGameEntity> findVideoGameWithActivePromotionAndLatestStock(@Param("videoGameId") Long videoGameId, @Param("date") Date date);
    @Query("SELECT v FROM VideoGameEntity v " +
            "LEFT JOIN FETCH v.promotions p " +
            "LEFT JOIN FETCH v.stocks s")
    List<VideoGameEntity> findAllWithPromotionsAndStocks();

}

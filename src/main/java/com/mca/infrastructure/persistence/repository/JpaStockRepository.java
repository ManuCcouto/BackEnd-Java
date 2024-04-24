package com.mca.infrastructure.persistence.repository;



import com.mca.infrastructure.persistence.entities.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;






public interface JpaStockRepository extends JpaRepository<StockEntity, Long> {


}

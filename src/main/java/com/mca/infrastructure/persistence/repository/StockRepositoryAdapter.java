package com.mca.infrastructure.persistence.repository;

import com.mca.domain.model.Stock;
import com.mca.domain.ports.out.StockRepository;

import com.mca.infrastructure.persistence.entities.StockEntity;
import com.mca.infrastructure.persistence.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;


@Repository
public class StockRepositoryAdapter implements StockRepository {
    private final JpaStockRepository jpaStockRepository;
    private StockMapper stockMapper;
    @Autowired
    public StockRepositoryAdapter(JpaStockRepository repository, StockMapper stockMapper) {
        this.jpaStockRepository = repository;
        this.stockMapper = stockMapper;
    }





    @Override
    public Stock save(Stock stock) throws Exception {
        try {
            StockEntity stockEntity = stockMapper.toEntity(stock);
            return stockMapper.toStockDomain(jpaStockRepository.save(stockEntity));
        } catch (DataAccessException e) {
            throw new Exception("Error al guardar el stock en la base de datos", e);
        }
    }






}
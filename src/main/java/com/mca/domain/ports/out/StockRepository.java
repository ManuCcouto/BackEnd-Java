package com.mca.domain.ports.out;

import com.mca.domain.model.Stock;



public interface StockRepository {

    Stock save(Stock stock) throws Exception;


}

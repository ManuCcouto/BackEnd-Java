package com.mca.domain.ports.in.stock;

import com.mca.domain.model.Stock;

public interface UpdateStockUseCase {
    void updateStock(Long videoGameId, Stock stock) throws Exception;
}

package com.mca.application.usecases;

import com.mca.domain.model.Stock;
import com.mca.domain.model.VideoGame;
import com.mca.domain.ports.in.game.GetVideoGameUseCase;
import com.mca.domain.ports.in.stock.UpdateStockUseCase;
import com.mca.domain.ports.out.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateStockUseCaseImpl  implements UpdateStockUseCase {

    private StockRepository stockRepository;

    private GetVideoGameUseCase getVideoGameUseCase;
    @Autowired
    public UpdateStockUseCaseImpl(StockRepository stockRepository, GetVideoGameUseCase getVideoGameUseCase) {
        this.stockRepository = stockRepository;
        this.getVideoGameUseCase = getVideoGameUseCase;
    }

    @Override
    public void updateStock(Long videoGameId, Stock stock) throws Exception {
        VideoGame videoGame = getVideoGameUseCase.getVideoGameById(videoGameId);
        stock.setVideoGame(videoGame);
        stockRepository.save(stock);
    }
}

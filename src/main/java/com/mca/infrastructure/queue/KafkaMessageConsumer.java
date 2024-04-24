package com.mca.infrastructure.queue;

import com.google.gson.Gson;
import com.mca.domain.model.Stock;
import com.mca.domain.ports.in.game.GetVideoGameUseCase;
import com.mca.domain.ports.in.stock.UpdateStockUseCase;
import com.mca.infrastructure.model.VideoGameEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;


@Component
public class KafkaMessageConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaMessageConsumer.class);

    private UpdateStockUseCase updateStockUseCase;


    @Autowired
    public KafkaMessageConsumer(UpdateStockUseCase videoGamesUseCase) {
        this.updateStockUseCase = videoGamesUseCase;

    }

    @KafkaListener(topics = "${topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, String> recordMessage, Acknowledgment acknowledgment) {
        String message = recordMessage.value();
        Gson gson = new Gson();

        try {
            VideoGameEvent stockEvent = gson.fromJson(message, VideoGameEvent.class);
            LOG.info("Guardando evento .... {}", stockEvent.toString());
            mapUpdateStock(stockEvent);
            acknowledgment.acknowledge();
        } catch (IllegalArgumentException illegalArgumentException) {
            LOG.error("Error al procesar el mensaje debido a un argumento ilegal: {}", illegalArgumentException.getMessage());
        } catch (Exception e) {
            LOG.error("Error al guardar el evento en la base de datos: {}", e.getMessage());
        }

    }


    private void mapUpdateStock(VideoGameEvent stockEvent) throws Exception {
        Stock stock = new Stock();
        stock.setAvailability(stockEvent.isAvailability());
        stock.setLastUpdated(stockEvent.getTime_update().toLocalDateTime());
        updateStockUseCase.updateStock(stockEvent.getStock_id(), stock);


    }
}
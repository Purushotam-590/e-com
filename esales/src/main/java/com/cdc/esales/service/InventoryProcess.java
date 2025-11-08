package com.cdc.esales.service;

import com.cdc.esales.dto.InventoryEvent;
import com.cdc.esales.dto.InventoryItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class InventoryProcess {

    Logger logger = LoggerFactory.getLogger(InventoryProcess.class);
    @Value("${kafka.topics.cdc-inventory}")
    private String cdcTopic;

    private final KafkaTemplate<String, InventoryEvent> kafkaTemplate;

    public InventoryProcess(KafkaTemplate<String, InventoryEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void processInventory(InventoryItem newItem, String operation) {

        InventoryEvent event = new InventoryEvent();
        event.setItemId(newItem.getId());
        event.setOperation(operation);
        event.setInventoryItem("c".equals(operation) ? null : newItem);
        event.setTransactionId(UUID.randomUUID().toString()); // Unique ID for idempotency tracking
        event.setTimestamp(Instant.now());

        String key = String.valueOf(event.getItemId());

        logger.info("Sending Inventory event {}: {} to topic {}", key, event, cdcTopic);

//        kafkaTemplate.send(cdcTopic, key, event).whenComplete((result, ex) -> {
//            if (ex == null) {
//                logger.info("Sent message with offset: {}", result.getRecordMetadata().offset());
//            } else {
//                logger.info("Failed to send message {} ", ex);
//            }
//        });
    }
}
package com.lightspeed.usage.usageaudit;

import com.lightspeed.restaurant.usage.EndpointUsedEvent;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Component
@Log
public class EventHandler {

    @KafkaListener(topics = "usage-audit", groupId = "usage")
    public void consumeSalesEvent(EndpointUsedEvent event) {
        log.info("Handling incoming event");

        log.info(String.format("Endpoint: %s, user: %s, company: %s",
                               event.getEndpoint(),
                               event.getUserDetails().getOid(),
                               event.getCompanyDetails().getOid()));

        log.info("Arguments: " + event.getArguments());
    }

}

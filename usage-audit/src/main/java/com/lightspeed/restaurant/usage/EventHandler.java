package com.lightspeed.restaurant.usage;

import com.lightspeed.restaurant.usage.storage.EndpointUsedEntry;
import com.lightspeed.restaurant.usage.storage.EndpointUsedRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Component
@Log
public class EventHandler {

    @Autowired
    private EndpointUsedRepository repository;

    @KafkaListener(topics = "usage-audit", groupId = "usage")
    public void consumeSalesEvent(EndpointUsedEvent event) {
        log.info("Handling incoming event");

        log.info(String.format("Endpoint: %s, user: %s, company: %s",
                               event.getEndpoint(),
                               event.getUserDetails().getOid(),
                               event.getCompanyDetails().getOid()));

        log.info("Arguments: " + event.getArguments());

        EndpointUsedEntry entry = new EndpointUsedEntry(event);
        repository.save(entry);
    }

}

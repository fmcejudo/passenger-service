package com.ryanair.passenger.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.support.leader.LockRegistryLeaderInitiator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Slf4j
public class PassengerScheduler {

    private final PassengerService passengerService;
    private final LockRegistryLeaderInitiator leaderInitiator;

    public PassengerScheduler(final PassengerService passengerService,
                              final LockRegistryLeaderInitiator leaderInitiator) {
        this.passengerService = passengerService;
        this.leaderInitiator = leaderInitiator;
    }

    @Scheduled(fixedRate = 20_000L)
    public void execute() {
        if (!leaderInitiator.getContext().isLeader()) {
            log.info("I am not the leader...");
            return;
        }

        passengerService.findAll().stream()
                .collect(Collectors.groupingBy(p -> String.join("/", p.getFrom(), p.getTo()).toUpperCase()))
                .forEach((key, value) -> log.info(
                        "{} has registered {} {}",
                        key, value.size(), value.size() == 1 ? "passenger" : "passengers")
                );
    }
}

package com.ryanair.passenger.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Slf4j
public class PassengerScheduler {

    private final PassengerService passengerService;

    public PassengerScheduler(final PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @Scheduled(fixedRate = 20_000L)
    public void execute() {
        passengerService.findAll().stream()
                .collect(Collectors.groupingBy(p -> String.join("/", p.getFrom(), p.getTo()).toUpperCase()))
                .forEach((key, value) -> log.info(
                        "{} has registered {} {}",
                        key, value.size(), value.size() == 1 ? "passenger" : "passengers")
                );
    }
}

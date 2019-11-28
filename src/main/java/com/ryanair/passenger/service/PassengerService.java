package com.ryanair.passenger.service;

import com.ryanair.passenger.model.Passenger;
import com.ryanair.passenger.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;

    public PassengerService(final PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public Passenger register(final Passenger passenger) {
        passenger.setBoardingTime(LocalDateTime.now());
        passenger.setFrom(lookupRef(passenger.getFrom()));
        passenger.setTo(lookupRef(passenger.getTo()));
        return passengerRepository.save(passenger);
    }

    private String lookupRef(final String code) {
        return switch (code) {
            case "HQ":
                yield "DUB";
            case "LAB":
                yield "MAD";
            default:
                yield code;
        };
    }

    public List<Passenger> findAll() {
        return StreamSupport.stream(passengerRepository.findAll().spliterator(), false)
                .collect(toList());
    }

}

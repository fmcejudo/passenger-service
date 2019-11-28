package com.ryanair.passenger.controller;

import com.ryanair.passenger.model.Passenger;
import com.ryanair.passenger.service.PassengerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    private final PassengerService passengerService;


    public PassengerController(final PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @PostMapping
    public Passenger register(@RequestBody final Passenger passenger) {
        return passengerService.register(passenger);
    }

    @GetMapping
    public List<Passenger> findAll() {
        return passengerService.findAll();
    }
}

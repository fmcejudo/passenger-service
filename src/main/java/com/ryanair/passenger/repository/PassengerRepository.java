package com.ryanair.passenger.repository;

import com.ryanair.passenger.model.Passenger;
import org.springframework.data.hazelcast.repository.HazelcastRepository;

public interface PassengerRepository extends HazelcastRepository<Passenger, String> {

}

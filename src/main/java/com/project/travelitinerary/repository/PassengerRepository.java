package com.project.travelitinerary.repository;

import com.project.travelitinerary.model.Passenger;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PassengerRepository extends MongoRepository<Passenger, String> {
}


package com.project.travelitinerary.repository;

import com.project.travelitinerary.model.Destination;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DestinationRepository extends MongoRepository<Destination, String> {
}


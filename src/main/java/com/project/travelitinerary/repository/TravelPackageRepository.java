package com.project.travelitinerary.repository;

import com.project.travelitinerary.model.TravelPackage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TravelPackageRepository extends MongoRepository<TravelPackage, String> {
}


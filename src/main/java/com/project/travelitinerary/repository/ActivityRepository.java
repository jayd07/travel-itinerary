package com.project.travelitinerary.repository;


import com.project.travelitinerary.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityRepository extends MongoRepository<Activity, String> {
}

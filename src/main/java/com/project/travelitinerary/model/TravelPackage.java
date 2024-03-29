package com.project.travelitinerary.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@Document(collection = "travel_packages")
public class TravelPackage {
    @Id
    private String id;
    private String name;
    private int passengerCapacity;
//    private List<Destination> itinerary;
    private List<Passenger> passengers;
    private List<Destination> itinerary;
}

package com.project.travelitinerary.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "passengers")
public class Passenger {
    @Id
    private String id;
    private String name;
    private int passengerNumber;
    private PassengerType type;
    private double balance;
    private List<Activity> activities = new ArrayList<>();
    private List<String> activityIds = new ArrayList<>();
}


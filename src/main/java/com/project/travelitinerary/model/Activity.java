package com.project.travelitinerary.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "activities")
public class Activity {
    @Id
    private String id;
    private String name;
    private String description;
    private double cost;
    private int capacity;
    private String  destination;
    private List<String> passengerIds = new ArrayList<>();
}


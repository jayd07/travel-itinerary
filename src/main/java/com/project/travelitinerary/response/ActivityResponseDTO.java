package com.project.travelitinerary.response;

import lombok.Data;

@Data
public class ActivityResponseDTO {
    private String name;
    private String description;
    private double cost;
    private int capacity;
    private String destination;

}

package com.project.travelitinerary.response;

import lombok.Data;

import java.util.List;

@Data
public class PassengerResponseDTO {
    private String name;
    private String passengerNumber;
    private double balance;
    private List<ActivityResponseDTO> activities;

}
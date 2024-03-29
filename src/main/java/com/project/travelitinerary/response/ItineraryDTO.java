package com.project.travelitinerary.response;

import lombok.Data;

import java.util.List;
@Data
public class ItineraryDTO {
    private String destinationName;
    private List<ActivityResponseDTO> activities;

}

package com.project.travelitinerary.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data 
@Document(collection = "destinations")
public class Destination {
    @Id
    private String id;
    private String name;
    private List<Activity> activities;

}
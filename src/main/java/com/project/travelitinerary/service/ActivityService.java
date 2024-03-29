package com.project.travelitinerary.service;

import com.project.travelitinerary.model.Activity;
import com.project.travelitinerary.model.Passenger;
import com.project.travelitinerary.repository.ActivityRepository;
import com.project.travelitinerary.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    public List<Passenger> getActivityPassengers(String activityId) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new RuntimeException("Activity not found"));

        List<String> passengerIds = activity.getPassengerIds();

        Iterable<Passenger> passengersIterable = passengerRepository.findAllById(passengerIds);
        List<Passenger> passengers = new ArrayList<>();
        passengersIterable.forEach(passengers::add);
        return passengers;
    }

    public Activity getActivity(String activityId) {
        return activityRepository.findById(activityId)
                .orElseThrow(() -> new RuntimeException("Activity not found"));
    }

    public Activity createActivity(Activity activityDto) {
        Activity activity = new Activity();
        activity.setName(activityDto.getName());
        activity.setDescription(activityDto.getDescription());
        activity.setCost(activityDto.getCost());
        activity.setCapacity(activityDto.getCapacity());
        return activityRepository.save(activity);
    }

    public Activity updateActivity(String activityId, Activity activityDto) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new RuntimeException("Activity not found"));
        activity.setName(activityDto.getName());
        activity.setDescription(activityDto.getDescription());
        activity.setCost(activityDto.getCost());
        activity.setCapacity(activityDto.getCapacity());
        return activityRepository.save(activity);
    }

    public void deleteActivity(String activityId) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new RuntimeException("Activity not found"));
        activityRepository.delete(activity);
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }
}

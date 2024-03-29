package com.project.travelitinerary.controller;

import com.project.travelitinerary.model.Activity;
import com.project.travelitinerary.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PostMapping
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activityDto) {
        Activity createdActivity = activityService.createActivity(activityDto);
        return new ResponseEntity<>(createdActivity, HttpStatus.CREATED);
    }

    @GetMapping("/{activityId}")
    public ResponseEntity<Activity> getActivity(@PathVariable String activityId) {
        Activity activity = activityService.getActivity(activityId);
        return ResponseEntity.ok(activity);
    }

    @PutMapping("/{activityId}")
    public ResponseEntity<Activity> updateActivity(@PathVariable String activityId, @RequestBody Activity activityDto) {
        Activity updatedActivity = activityService.updateActivity(activityId, activityDto);
        return ResponseEntity.ok(updatedActivity);
    }

    @DeleteMapping("/{activityId}")
    public ResponseEntity<Void> deleteActivity(@PathVariable String activityId) {
        activityService.deleteActivity(activityId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Activity>> getAllActivities() {
        List<Activity> activity = activityService.getAllActivities();
        return ResponseEntity.ok(activity);
    }
}


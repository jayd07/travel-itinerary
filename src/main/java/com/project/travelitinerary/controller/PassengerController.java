package com.project.travelitinerary.controller;

import com.project.travelitinerary.model.Passenger;
import com.project.travelitinerary.response.PassengerResponseDTO;
import com.project.travelitinerary.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passengers")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @GetMapping("/{passengerId}")
    public PassengerResponseDTO getPassengerDetails(@PathVariable String passengerId) {
        return passengerService.printPassengerDetails(passengerId);
    }

    @PostMapping
    public ResponseEntity<Passenger> createPassenger(@RequestBody Passenger passenger) {
        Passenger createdPassenger = passengerService.createPassenger(passenger);
        return ResponseEntity.ok(createdPassenger);
    }

    @PutMapping("/{passengerId}/update")
    public ResponseEntity<Passenger> updatePassenger(@PathVariable String passengerId, @RequestBody Passenger passenger) {
        Passenger updatedPassenger = passengerService.updatePassenger(passengerId, passenger);
        return ResponseEntity.ok(updatedPassenger);
    }

    @DeleteMapping("/{passengerId}")
    public ResponseEntity<Void> deletePassenger(@PathVariable String passengerId) {
        passengerService.deletePassenger(passengerId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{passengerId}/book-activity/{activityId}")
    public ResponseEntity<Void> bookActivity(@PathVariable String passengerId,
                                             @PathVariable String activityId) {
        passengerService.bookActivity(passengerId, activityId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }
}
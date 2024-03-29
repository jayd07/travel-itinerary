package com.project.travelitinerary.service;

import com.project.travelitinerary.model.Activity;
import com.project.travelitinerary.model.Destination;
import com.project.travelitinerary.model.Passenger;
import com.project.travelitinerary.model.PassengerType;
import com.project.travelitinerary.repository.ActivityRepository;
import com.project.travelitinerary.repository.DestinationRepository;
import com.project.travelitinerary.repository.PassengerRepository;
import com.project.travelitinerary.repository.TravelPackageRepository;
import com.project.travelitinerary.response.ActivityResponseDTO;
import com.project.travelitinerary.response.PassengerResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private TravelPackageRepository travelPackageRepository;

    public PassengerResponseDTO printPassengerDetails(String passengerId) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        PassengerResponseDTO passengerDTO = new PassengerResponseDTO();
        passengerDTO.setName(passenger.getName());
        passengerDTO.setPassengerNumber(String.valueOf(passenger.getId()));
        passengerDTO.setBalance(passenger.getBalance());
        List<ActivityResponseDTO> activityDTOs = new ArrayList<>();
        for (Activity activity : passenger.getActivities()) {
            ActivityResponseDTO activityDTO = new ActivityResponseDTO();
            activityDTO.setName(activity.getName());
            activityDTO.setDescription(activity.getDescription());
            activityDTO.setCost(activity.getCost());
            activityDTO.setCapacity(activity.getCapacity());
            activityDTOs.add(activityDTO);
        }
        passengerDTO.setActivities(activityDTOs);
        return passengerDTO;
    }

    public void bookActivity(String passengerId, String activityId) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new RuntimeException("Activity not found"));
        if(activity.getPassengerIds() != null)
            if (activity.getPassengerIds().size() >= activity.getCapacity()) {
                throw new RuntimeException("Activity has reached its capacity");
        }

        if (((passenger.getType() == PassengerType.STANDARD) || (passenger.getType() != PassengerType.PREMIUM)) && passenger.getBalance() < activity.getCost()){
            throw new RuntimeException("Insufficient balance");
        }

        if (passenger.getType() == PassengerType.GOLD) {
            double discountAmount = activity.getCost() * 0.1;
            activity.setCost(activity.getCost() - discountAmount);
            passenger.setBalance(passenger.getBalance() - activity.getCost());
        } else if (passenger.getType() == PassengerType.STANDARD) {
            passenger.setBalance(passenger.getBalance() - activity.getCost());
        }
        activity.setDestination(activity.getDestination());
        passenger.getActivityIds().add(activityId);
        passenger.getActivities().add(activity);
        activity.getPassengerIds().add(passengerId);

        passengerRepository.save(passenger);
        activityRepository.save(activity);
    }

    public Passenger createPassenger(Passenger passengerDto) {
        Passenger passenger = new Passenger();
        passenger.setName(passengerDto.getName());
        passenger.setPassengerNumber(Integer.parseInt(passengerDto.getId()));
        passenger.setType(passengerDto.getType());
        passenger.setBalance(passengerDto.getBalance());
        passenger.setActivities(new ArrayList<>());
        return passengerRepository.save(passenger);
    }

    public Passenger updatePassenger(String passengerId, Passenger passengerDto) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
        passenger.setName(passengerDto.getName());
        passenger.setPassengerNumber(passengerDto.getPassengerNumber());
        passenger.setType(passengerDto.getType());
        passenger.setBalance(passengerDto.getBalance());
        return passengerRepository.save(passenger);
    }

    public void deletePassenger(String passengerId) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
        passengerRepository.delete(passenger);
    }

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }
}

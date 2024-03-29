package com.project.travelitinerary.service;

import com.project.travelitinerary.model.Activity;
import com.project.travelitinerary.model.Destination;
import com.project.travelitinerary.model.TravelPackage;
import com.project.travelitinerary.repository.ActivityRepository;
import com.project.travelitinerary.repository.DestinationRepository;
import com.project.travelitinerary.repository.TravelPackageRepository;
import com.project.travelitinerary.response.ActivityResponseDTO;
import com.project.travelitinerary.response.ItineraryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TravelPackageService {
    @Autowired
    private TravelPackageRepository travelPackageRepository;
    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private ActivityRepository activityRepository;
    public List<ItineraryDTO> printTravelPackageItinerary(String travelPackageId) {
        TravelPackage travelPackage = travelPackageRepository.findById(travelPackageId)
                .orElseThrow(() -> new RuntimeException("Travel package not found"));

        List<ItineraryDTO> itineraryDTOs = new ArrayList<>();
        if (travelPackage != null) {
            for (Destination destination : travelPackage.getItinerary()) {
                ItineraryDTO itineraryDTO = new ItineraryDTO();
                itineraryDTO.setDestinationName(destination.getName());
                List<ActivityResponseDTO> activityDTOs = new ArrayList<>();
                for (Activity activity : destination.getActivities()) {
                    ActivityResponseDTO activityDTO = new ActivityResponseDTO();
                    activityDTO.setName(activity.getName());
                    activityDTO.setDescription(activity.getDescription());
                    activityDTO.setCost(activity.getCost());
                    activityDTO.setCapacity(activity.getCapacity());
                    activityDTO.setDestination(activity.getDestination());
                    activityDTOs.add(activityDTO);
                }
                itineraryDTO.setActivities(activityDTOs);
                itineraryDTOs.add(itineraryDTO);
            }
        }
        return itineraryDTOs;
    }

    public TravelPackage createTravelPackage(TravelPackage travelPackageDto) {
        for (Destination destination : travelPackageDto.getItinerary()) {
            if (destination.getId() == null)
                destinationRepository.save(destination);
            for(Activity activity : destination.getActivities()){
                if (activity.getId() == null)
                    activity.setDestination(destination.getName());
                    activityRepository.save(activity);
            }
        }
        return travelPackageRepository.save(travelPackageDto);
    }

    public TravelPackage updateTravelPackage(String id, TravelPackage updatedTravelPackage) {

        TravelPackage existingTravelPackage = travelPackageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Travel package not found"));
        for (Destination destination : updatedTravelPackage.getItinerary()) {
            if (destination.getId() == null)
                destinationRepository.save(destination);
        }

        existingTravelPackage.setName(updatedTravelPackage.getName());
        existingTravelPackage.setPassengerCapacity(updatedTravelPackage.getPassengerCapacity());
        existingTravelPackage.setItinerary(updatedTravelPackage.getItinerary());
        existingTravelPackage.setPassengers(updatedTravelPackage.getPassengers());
        return travelPackageRepository.save(existingTravelPackage);
    }

    public void deleteTravelPackage(String travelPackageId) {
        TravelPackage travelPackage = travelPackageRepository.findById(travelPackageId)
                .orElseThrow(() -> new RuntimeException("Travel package not found"));
        travelPackageRepository.delete(travelPackage);
    }

    public List<TravelPackage> getAllTravelPackages() {
        return travelPackageRepository.findAll();
    }
}

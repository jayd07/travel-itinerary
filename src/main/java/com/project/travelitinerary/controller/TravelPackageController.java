package com.project.travelitinerary.controller;

import com.project.travelitinerary.model.TravelPackage;
import com.project.travelitinerary.response.ItineraryDTO;
import com.project.travelitinerary.service.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travel-packages")
public class TravelPackageController {
    @Autowired
    private TravelPackageService travelPackageService;

    @GetMapping("/{travelPackageId}/itinerary")
    public List<ItineraryDTO> getTravelPackageItinerary(@PathVariable String travelPackageId) {
        return travelPackageService.printTravelPackageItinerary(travelPackageId);
    }

    @PostMapping
    public ResponseEntity<TravelPackage> createTravelPackage(@RequestBody TravelPackage travelPackage) {
        TravelPackage createdPackage = travelPackageService.createTravelPackage(travelPackage);
        return ResponseEntity.ok(createdPackage);
    }

    @PutMapping("/{travelPackageId}/update")
    public ResponseEntity<TravelPackage> updateTravelPackage(@PathVariable String travelPackageId, @RequestBody TravelPackage travelPackage) {
        TravelPackage updatedPackage = travelPackageService.updateTravelPackage(travelPackageId, travelPackage);
        return ResponseEntity.ok(updatedPackage);
    }

    @DeleteMapping("/{travelPackageId}")
    public ResponseEntity<Void> deleteTravelPackage(@PathVariable String travelPackageId) {
        travelPackageService.deleteTravelPackage(travelPackageId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<TravelPackage> getAllTravelPackages() {
        return travelPackageService.getAllTravelPackages();
    }
}

package com.project.travelitinerary.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.travelitinerary.model.Activity;
import com.project.travelitinerary.model.Destination;
import com.project.travelitinerary.model.Passenger;
import com.project.travelitinerary.model.TravelPackage;
import com.project.travelitinerary.repository.ActivityRepository;
import com.project.travelitinerary.repository.DestinationRepository;
import com.project.travelitinerary.repository.TravelPackageRepository;
import com.project.travelitinerary.response.ActivityResponseDTO;
import com.project.travelitinerary.response.ItineraryDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Ignore;

import org.junit.Rule;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {TravelPackageService.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class TravelPackageServiceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @MockBean
    private ActivityRepository activityRepository;

    @MockBean
    private DestinationRepository destinationRepository;

    @MockBean
    private TravelPackageRepository travelPackageRepository;

    @Autowired
    private TravelPackageService travelPackageService;

    /**
     * Method under test: {@link TravelPackageService#printTravelPackageItinerary(String)}
     */
    @Test
    public void testPrintTravelPackageItinerary() {
        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setId("42");
        travelPackage.setItinerary(new ArrayList<>());
        travelPackage.setName("Name");
        travelPackage.setPassengerCapacity(1);
        travelPackage.setPassengers(new ArrayList<>());
        Optional<TravelPackage> ofResult = Optional.of(travelPackage);
        when(travelPackageRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        assertTrue(travelPackageService.printTravelPackageItinerary("42").isEmpty());
        verify(travelPackageRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link TravelPackageService#printTravelPackageItinerary(String)}
     */
    @Test
    public void testPrintTravelPackageItinerary2() {
        Destination destination = new Destination();
        destination.setActivities(new ArrayList<>());
        destination.setId("42");
        destination.setName("Name");

        ArrayList<Destination> itinerary = new ArrayList<>();
        itinerary.add(destination);

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setId("42");
        travelPackage.setItinerary(itinerary);
        travelPackage.setName("Name");
        travelPackage.setPassengerCapacity(1);
        ArrayList<Passenger> passengers = new ArrayList<>();
        travelPackage.setPassengers(passengers);
        Optional<TravelPackage> ofResult = Optional.of(travelPackage);
        when(travelPackageRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        List<ItineraryDTO> actualPrintTravelPackageItineraryResult = travelPackageService
                .printTravelPackageItinerary("42");
        assertEquals(1, actualPrintTravelPackageItineraryResult.size());
        ItineraryDTO getResult = actualPrintTravelPackageItineraryResult.get(0);
        assertEquals(passengers, getResult.getActivities());
        assertEquals("Name", getResult.getDestinationName());
        verify(travelPackageRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link TravelPackageService#printTravelPackageItinerary(String)}
     */
    @Test
    public void testPrintTravelPackageItinerary3() {
        when(travelPackageRepository.findById(Mockito.<String>any())).thenReturn(Optional.empty());
        thrown.expect(RuntimeException.class);
        travelPackageService.printTravelPackageItinerary("42");
        verify(travelPackageRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link TravelPackageService#printTravelPackageItinerary(String)}
     */
    @Test
    public void testPrintTravelPackageItinerary4() {
        when(travelPackageRepository.findById(Mockito.<String>any()))
                .thenThrow(new RuntimeException("Travel package not found"));
        thrown.expect(RuntimeException.class);
        travelPackageService.printTravelPackageItinerary("42");
        verify(travelPackageRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link TravelPackageService#printTravelPackageItinerary(String)}
     */
    @Test
    public void testPrintTravelPackageItinerary5() {
        Activity activity = new Activity();
        activity.setCapacity(3);
        activity.setCost(10.0d);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestination("Destination");
        activity.setId("42");
        activity.setName("Name");
        activity.setPassengerIds(new ArrayList<>());

        ArrayList<Activity> activities = new ArrayList<>();
        activities.add(activity);

        Destination destination = new Destination();
        destination.setActivities(activities);
        destination.setId("42");
        destination.setName("Name");

        ArrayList<Destination> itinerary = new ArrayList<>();
        itinerary.add(destination);

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setId("42");
        travelPackage.setItinerary(itinerary);
        travelPackage.setName("Name");
        travelPackage.setPassengerCapacity(1);
        travelPackage.setPassengers(new ArrayList<>());
        Optional<TravelPackage> ofResult = Optional.of(travelPackage);
        when(travelPackageRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        List<ItineraryDTO> actualPrintTravelPackageItineraryResult = travelPackageService
                .printTravelPackageItinerary("42");
        assertEquals(1, actualPrintTravelPackageItineraryResult.size());
        ItineraryDTO getResult = actualPrintTravelPackageItineraryResult.get(0);
        List<ActivityResponseDTO> activities2 = getResult.getActivities();
        assertEquals(1, activities2.size());
        assertEquals("Name", getResult.getDestinationName());
        ActivityResponseDTO getResult2 = activities2.get(0);
        assertEquals(3, getResult2.getCapacity());
        assertEquals("Name", getResult2.getName());
        assertEquals("Destination", getResult2.getDestination());
        assertEquals("The characteristics of someone or something", getResult2.getDescription());
        assertEquals(10.0d, getResult2.getCost(), 0.0);
        verify(travelPackageRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link TravelPackageService#createTravelPackage(TravelPackage)}
     */
    @Test
    public void testCreateTravelPackage() {
        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setId("42");
        travelPackage.setItinerary(new ArrayList<>());
        travelPackage.setName("Name");
        travelPackage.setPassengerCapacity(1);
        travelPackage.setPassengers(new ArrayList<>());
        when(travelPackageRepository.save(Mockito.<TravelPackage>any())).thenReturn(travelPackage);

        TravelPackage travelPackageDto = new TravelPackage();
        travelPackageDto.setId("42");
        travelPackageDto.setItinerary(new ArrayList<>());
        travelPackageDto.setName("Name");
        travelPackageDto.setPassengerCapacity(1);
        travelPackageDto.setPassengers(new ArrayList<>());
        assertSame(travelPackage, travelPackageService.createTravelPackage(travelPackageDto));
        verify(travelPackageRepository).save(Mockito.<TravelPackage>any());
    }

    /**
     * Method under test: {@link TravelPackageService#createTravelPackage(TravelPackage)}
     */
    @Test
    public void testCreateTravelPackage2() {
        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setId("42");
        travelPackage.setItinerary(new ArrayList<>());
        travelPackage.setName("Name");
        travelPackage.setPassengerCapacity(1);
        travelPackage.setPassengers(new ArrayList<>());
        when(travelPackageRepository.save(Mockito.<TravelPackage>any())).thenReturn(travelPackage);

        Destination destination = new Destination();
        destination.setActivities(new ArrayList<>());
        destination.setId("42");
        destination.setName("Name");

        ArrayList<Destination> itinerary = new ArrayList<>();
        itinerary.add(destination);

        TravelPackage travelPackageDto = new TravelPackage();
        travelPackageDto.setId("42");
        travelPackageDto.setItinerary(itinerary);
        travelPackageDto.setName("Name");
        travelPackageDto.setPassengerCapacity(1);
        travelPackageDto.setPassengers(new ArrayList<>());
        assertSame(travelPackage, travelPackageService.createTravelPackage(travelPackageDto));
        verify(travelPackageRepository).save(Mockito.<TravelPackage>any());
    }

    /**
     * Method under test: {@link TravelPackageService#createTravelPackage(TravelPackage)}
     */
    @Test
    public void testCreateTravelPackage3() {
        when(travelPackageRepository.save(Mockito.<TravelPackage>any())).thenThrow(new RuntimeException("foo"));

        TravelPackage travelPackageDto = new TravelPackage();
        travelPackageDto.setId("42");
        travelPackageDto.setItinerary(new ArrayList<>());
        travelPackageDto.setName("Name");
        travelPackageDto.setPassengerCapacity(1);
        travelPackageDto.setPassengers(new ArrayList<>());
        thrown.expect(RuntimeException.class);
        travelPackageService.createTravelPackage(travelPackageDto);
        verify(travelPackageRepository).save(Mockito.<TravelPackage>any());
    }

    /**
     * Method under test: {@link TravelPackageService#createTravelPackage(TravelPackage)}
     */
    @Test
    public void testCreateTravelPackage4() {
        Activity activity = new Activity();
        activity.setCapacity(3);
        activity.setCost(10.0d);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestination("Destination");
        activity.setId("42");
        activity.setName("Name");
        activity.setPassengerIds(new ArrayList<>());
        when(activityRepository.save(Mockito.<Activity>any())).thenReturn(activity);

        Destination destination = new Destination();
        destination.setActivities(new ArrayList<>());
        destination.setId("42");
        destination.setName("Name");
        when(destinationRepository.save(Mockito.<Destination>any())).thenReturn(destination);

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setId("42");
        travelPackage.setItinerary(new ArrayList<>());
        travelPackage.setName("Name");
        travelPackage.setPassengerCapacity(1);
        travelPackage.setPassengers(new ArrayList<>());
        when(travelPackageRepository.save(Mockito.<TravelPackage>any())).thenReturn(travelPackage);

        Activity activity2 = new Activity();
        activity2.setCapacity(3);
        activity2.setCost(10.0d);
        activity2.setDescription("The characteristics of someone or something");
        activity2.setDestination("Destination");
        activity2.setId("42");
        activity2.setName("Name");
        activity2.setPassengerIds(new ArrayList<>());

        ArrayList<Activity> activityList = new ArrayList<>();
        activityList.add(activity2);
        Destination destination2 = mock(Destination.class);
        when(destination2.getId()).thenReturn("42");
        when(destination2.getActivities()).thenReturn(activityList);
        doNothing().when(destination2).setActivities(Mockito.<List<Activity>>any());
        doNothing().when(destination2).setId(Mockito.<String>any());
        doNothing().when(destination2).setName(Mockito.<String>any());
        destination2.setActivities(new ArrayList<>());
        destination2.setId("42");
        destination2.setName("Name");

        ArrayList<Destination> itinerary = new ArrayList<>();
        itinerary.add(destination2);

        TravelPackage travelPackageDto = new TravelPackage();
        travelPackageDto.setId("42");
        travelPackageDto.setItinerary(itinerary);
        travelPackageDto.setName("Name");
        travelPackageDto.setPassengerCapacity(1);
        travelPackageDto.setPassengers(new ArrayList<>());
        assertSame(travelPackage, travelPackageService.createTravelPackage(travelPackageDto));
        verify(activityRepository).save(Mockito.<Activity>any());
        verify(travelPackageRepository).save(Mockito.<TravelPackage>any());
        verify(destination2).getId();
        verify(destination2).getActivities();
        verify(destination2).setActivities(Mockito.<List<Activity>>any());
        verify(destination2).setId(Mockito.<String>any());
        verify(destination2).setName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link TravelPackageService#updateTravelPackage(String, TravelPackage)}
     */
    @Test
    public void testUpdateTravelPackage() {
        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setId("42");
        travelPackage.setItinerary(new ArrayList<>());
        travelPackage.setName("Name");
        travelPackage.setPassengerCapacity(1);
        travelPackage.setPassengers(new ArrayList<>());
        Optional<TravelPackage> ofResult = Optional.of(travelPackage);

        TravelPackage travelPackage2 = new TravelPackage();
        travelPackage2.setId("42");
        travelPackage2.setItinerary(new ArrayList<>());
        travelPackage2.setName("Name");
        travelPackage2.setPassengerCapacity(1);
        travelPackage2.setPassengers(new ArrayList<>());
        when(travelPackageRepository.save(Mockito.<TravelPackage>any())).thenReturn(travelPackage2);
        when(travelPackageRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        TravelPackage updatedTravelPackage = new TravelPackage();
        updatedTravelPackage.setId("42");
        updatedTravelPackage.setItinerary(new ArrayList<>());
        updatedTravelPackage.setName("Name");
        updatedTravelPackage.setPassengerCapacity(1);
        updatedTravelPackage.setPassengers(new ArrayList<>());
        assertSame(travelPackage2, travelPackageService.updateTravelPackage("42", updatedTravelPackage));
        verify(travelPackageRepository).save(Mockito.<TravelPackage>any());
        verify(travelPackageRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link TravelPackageService#updateTravelPackage(String, TravelPackage)}
     */
    @Test
    public void testUpdateTravelPackage2() {
        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setId("42");
        travelPackage.setItinerary(new ArrayList<>());
        travelPackage.setName("Name");
        travelPackage.setPassengerCapacity(1);
        travelPackage.setPassengers(new ArrayList<>());
        Optional<TravelPackage> ofResult = Optional.of(travelPackage);
        when(travelPackageRepository.save(Mockito.<TravelPackage>any())).thenThrow(new RuntimeException("foo"));
        when(travelPackageRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        TravelPackage updatedTravelPackage = new TravelPackage();
        updatedTravelPackage.setId("42");
        updatedTravelPackage.setItinerary(new ArrayList<>());
        updatedTravelPackage.setName("Name");
        updatedTravelPackage.setPassengerCapacity(1);
        updatedTravelPackage.setPassengers(new ArrayList<>());
        thrown.expect(RuntimeException.class);
        travelPackageService.updateTravelPackage("42", updatedTravelPackage);
        verify(travelPackageRepository).save(Mockito.<TravelPackage>any());
        verify(travelPackageRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link TravelPackageService#updateTravelPackage(String, TravelPackage)}
     */
    @Test
    public void testUpdateTravelPackage3() {
        when(travelPackageRepository.findById(Mockito.<String>any())).thenReturn(Optional.empty());

        TravelPackage updatedTravelPackage = new TravelPackage();
        updatedTravelPackage.setId("42");
        updatedTravelPackage.setItinerary(new ArrayList<>());
        updatedTravelPackage.setName("Name");
        updatedTravelPackage.setPassengerCapacity(1);
        updatedTravelPackage.setPassengers(new ArrayList<>());
        thrown.expect(RuntimeException.class);
        travelPackageService.updateTravelPackage("42", updatedTravelPackage);
        verify(travelPackageRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link TravelPackageService#updateTravelPackage(String, TravelPackage)}
     */
    @Test
    public void testUpdateTravelPackage4() {
        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setId("42");
        travelPackage.setItinerary(new ArrayList<>());
        travelPackage.setName("Name");
        travelPackage.setPassengerCapacity(1);
        travelPackage.setPassengers(new ArrayList<>());
        Optional<TravelPackage> ofResult = Optional.of(travelPackage);

        TravelPackage travelPackage2 = new TravelPackage();
        travelPackage2.setId("42");
        travelPackage2.setItinerary(new ArrayList<>());
        travelPackage2.setName("Name");
        travelPackage2.setPassengerCapacity(1);
        travelPackage2.setPassengers(new ArrayList<>());
        when(travelPackageRepository.save(Mockito.<TravelPackage>any())).thenReturn(travelPackage2);
        when(travelPackageRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        Destination destination = new Destination();
        destination.setActivities(new ArrayList<>());
        destination.setId("42");
        destination.setName("Name");

        ArrayList<Destination> itinerary = new ArrayList<>();
        itinerary.add(destination);

        TravelPackage updatedTravelPackage = new TravelPackage();
        updatedTravelPackage.setId("42");
        updatedTravelPackage.setItinerary(itinerary);
        updatedTravelPackage.setName("Name");
        updatedTravelPackage.setPassengerCapacity(1);
        updatedTravelPackage.setPassengers(new ArrayList<>());
        assertSame(travelPackage2, travelPackageService.updateTravelPackage("42", updatedTravelPackage));
        verify(travelPackageRepository).save(Mockito.<TravelPackage>any());
        verify(travelPackageRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link TravelPackageService#deleteTravelPackage(String)}
     */
    @Test
    public void testDeleteTravelPackage() {
        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setId("42");
        travelPackage.setItinerary(new ArrayList<>());
        travelPackage.setName("Name");
        travelPackage.setPassengerCapacity(1);
        travelPackage.setPassengers(new ArrayList<>());
        Optional<TravelPackage> ofResult = Optional.of(travelPackage);
        doNothing().when(travelPackageRepository).delete(Mockito.<TravelPackage>any());
        when(travelPackageRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        travelPackageService.deleteTravelPackage("42");
        verify(travelPackageRepository).findById(Mockito.<String>any());
        verify(travelPackageRepository).delete(Mockito.<TravelPackage>any());
        assertTrue(travelPackageService.getAllTravelPackages().isEmpty());
    }

    /**
     * Method under test: {@link TravelPackageService#deleteTravelPackage(String)}
     */
    @Test
    public void testDeleteTravelPackage2() {
        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setId("42");
        travelPackage.setItinerary(new ArrayList<>());
        travelPackage.setName("Name");
        travelPackage.setPassengerCapacity(1);
        travelPackage.setPassengers(new ArrayList<>());
        Optional<TravelPackage> ofResult = Optional.of(travelPackage);
        doThrow(new RuntimeException("foo")).when(travelPackageRepository).delete(Mockito.<TravelPackage>any());
        when(travelPackageRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        thrown.expect(RuntimeException.class);
        travelPackageService.deleteTravelPackage("42");
        verify(travelPackageRepository).findById(Mockito.<String>any());
        verify(travelPackageRepository).delete(Mockito.<TravelPackage>any());
    }

    /**
     * Method under test: {@link TravelPackageService#deleteTravelPackage(String)}
     */
    @Test
    public void testDeleteTravelPackage3() {
        when(travelPackageRepository.findById(Mockito.<String>any())).thenReturn(Optional.empty());
        thrown.expect(RuntimeException.class);
        travelPackageService.deleteTravelPackage("42");
        verify(travelPackageRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link TravelPackageService#getAllTravelPackages()}
     */
    @Test
    public void testGetAllTravelPackages() {
        ArrayList<TravelPackage> travelPackageList = new ArrayList<>();
        when(travelPackageRepository.findAll()).thenReturn(travelPackageList);
        List<TravelPackage> actualAllTravelPackages = travelPackageService.getAllTravelPackages();
        assertSame(travelPackageList, actualAllTravelPackages);
        assertTrue(actualAllTravelPackages.isEmpty());
        verify(travelPackageRepository).findAll();
    }

    /**
     * Method under test: {@link TravelPackageService#getAllTravelPackages()}
     */
    @Test
    public void testGetAllTravelPackages2() {
        when(travelPackageRepository.findAll()).thenThrow(new RuntimeException("foo"));
        thrown.expect(RuntimeException.class);
        travelPackageService.getAllTravelPackages();
        verify(travelPackageRepository).findAll();
    }
}


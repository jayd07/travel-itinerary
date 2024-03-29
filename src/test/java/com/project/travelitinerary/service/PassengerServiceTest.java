package com.project.travelitinerary.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.travelitinerary.model.Activity;
import com.project.travelitinerary.model.Passenger;
import com.project.travelitinerary.model.PassengerType;
import com.project.travelitinerary.repository.ActivityRepository;
import com.project.travelitinerary.repository.DestinationRepository;
import com.project.travelitinerary.repository.PassengerRepository;
import com.project.travelitinerary.repository.TravelPackageRepository;
import com.project.travelitinerary.response.ActivityResponseDTO;
import com.project.travelitinerary.response.PassengerResponseDTO;

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

@ContextConfiguration(classes = {PassengerService.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class PassengerServiceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @MockBean
    private ActivityRepository activityRepository;

    @MockBean
    private DestinationRepository destinationRepository;

    @MockBean
    private PassengerRepository passengerRepository;

    @Autowired
    private PassengerService passengerService;

    @MockBean
    private TravelPackageRepository travelPackageRepository;

    /**
     * Method under test: {@link PassengerService#printPassengerDetails(String)}
     */
    @Test
    public void testPrintPassengerDetails() {
        Passenger passenger = new Passenger();
        ArrayList<Activity> activities = new ArrayList<>();
        passenger.setActivities(activities);
        passenger.setActivityIds(new ArrayList<>());
        passenger.setBalance(10.0d);
        passenger.setId("42");
        passenger.setName("Name");
        passenger.setPassengerNumber(10);
        passenger.setType(PassengerType.STANDARD);
        Optional<Passenger> ofResult = Optional.of(passenger);
        when(passengerRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        PassengerResponseDTO actualPrintPassengerDetailsResult = passengerService.printPassengerDetails("42");
        assertEquals(activities, actualPrintPassengerDetailsResult.getActivities());
        assertEquals("10", actualPrintPassengerDetailsResult.getPassengerNumber());
        assertEquals("Name", actualPrintPassengerDetailsResult.getName());
        assertEquals(10.0d, actualPrintPassengerDetailsResult.getBalance(), 0.0);
        verify(passengerRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PassengerService#printPassengerDetails(String)}
     */
    @Test
    public void testPrintPassengerDetails2() {
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

        Passenger passenger = new Passenger();
        passenger.setActivities(activities);
        passenger.setActivityIds(new ArrayList<>());
        passenger.setBalance(10.0d);
        passenger.setId("42");
        passenger.setName("Name");
        passenger.setPassengerNumber(10);
        passenger.setType(PassengerType.STANDARD);
        Optional<Passenger> ofResult = Optional.of(passenger);
        when(passengerRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        PassengerResponseDTO actualPrintPassengerDetailsResult = passengerService.printPassengerDetails("42");
        List<ActivityResponseDTO> activities2 = actualPrintPassengerDetailsResult.getActivities();
        assertEquals(1, activities2.size());
        assertEquals("10", actualPrintPassengerDetailsResult.getPassengerNumber());
        assertEquals(10.0d, actualPrintPassengerDetailsResult.getBalance(), 0.0);
        assertEquals("Name", actualPrintPassengerDetailsResult.getName());
        ActivityResponseDTO getResult = activities2.get(0);
        assertEquals(3, getResult.getCapacity());
        assertEquals("Name", getResult.getName());
        assertEquals("The characteristics of someone or something", getResult.getDescription());
        assertEquals(10.0d, getResult.getCost(), 0.0);
        verify(passengerRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PassengerService#printPassengerDetails(String)}
     */
    @Test
    public void testPrintPassengerDetails3() {
        when(passengerRepository.findById(Mockito.<String>any())).thenReturn(Optional.empty());
        thrown.expect(RuntimeException.class);
        passengerService.printPassengerDetails("42");
        verify(passengerRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PassengerService#printPassengerDetails(String)}
     */
    @Test
    public void testPrintPassengerDetails4() {
        when(passengerRepository.findById(Mockito.<String>any())).thenThrow(new RuntimeException("Passenger not found"));
        thrown.expect(RuntimeException.class);
        passengerService.printPassengerDetails("42");
        verify(passengerRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PassengerService#bookActivity(String, String)}
     */
    @Test
    public void testBookActivity() {
        Activity activity = new Activity();
        activity.setCapacity(3);
        activity.setCost(10.0d);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestination("Destination");
        activity.setId("42");
        activity.setName("Name");
        activity.setPassengerIds(new ArrayList<>());
        Optional<Activity> ofResult = Optional.of(activity);

        Activity activity2 = new Activity();
        activity2.setCapacity(3);
        activity2.setCost(10.0d);
        activity2.setDescription("The characteristics of someone or something");
        activity2.setDestination("Destination");
        activity2.setId("42");
        activity2.setName("Name");
        activity2.setPassengerIds(new ArrayList<>());
        when(activityRepository.save(Mockito.<Activity>any())).thenReturn(activity2);
        when(activityRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        Passenger passenger = new Passenger();
        passenger.setActivities(new ArrayList<>());
        passenger.setActivityIds(new ArrayList<>());
        passenger.setBalance(10.0d);
        passenger.setId("42");
        passenger.setName("Name");
        passenger.setPassengerNumber(10);
        passenger.setType(PassengerType.STANDARD);
        Optional<Passenger> ofResult2 = Optional.of(passenger);

        Passenger passenger2 = new Passenger();
        passenger2.setActivities(new ArrayList<>());
        passenger2.setActivityIds(new ArrayList<>());
        passenger2.setBalance(10.0d);
        passenger2.setId("42");
        passenger2.setName("Name");
        passenger2.setPassengerNumber(10);
        passenger2.setType(PassengerType.STANDARD);
        when(passengerRepository.save(Mockito.<Passenger>any())).thenReturn(passenger2);
        when(passengerRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        passengerService.bookActivity("42", "42");
        verify(activityRepository).save(Mockito.<Activity>any());
        verify(activityRepository).findById(Mockito.<String>any());
        verify(passengerRepository).save(Mockito.<Passenger>any());
        verify(passengerRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PassengerService#bookActivity(String, String)}
     */
    @Test
    public void testBookActivity2() {
        Activity activity = new Activity();
        activity.setCapacity(3);
        activity.setCost(10.0d);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestination("Destination");
        activity.setId("42");
        activity.setName("Name");
        activity.setPassengerIds(new ArrayList<>());
        Optional<Activity> ofResult = Optional.of(activity);

        Activity activity2 = new Activity();
        activity2.setCapacity(3);
        activity2.setCost(10.0d);
        activity2.setDescription("The characteristics of someone or something");
        activity2.setDestination("Destination");
        activity2.setId("42");
        activity2.setName("Name");
        activity2.setPassengerIds(new ArrayList<>());
        when(activityRepository.save(Mockito.<Activity>any())).thenReturn(activity2);
        when(activityRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        Passenger passenger = new Passenger();
        passenger.setActivities(new ArrayList<>());
        passenger.setActivityIds(new ArrayList<>());
        passenger.setBalance(10.0d);
        passenger.setId("42");
        passenger.setName("Name");
        passenger.setPassengerNumber(10);
        passenger.setType(PassengerType.STANDARD);
        Optional<Passenger> ofResult2 = Optional.of(passenger);
        when(passengerRepository.save(Mockito.<Passenger>any())).thenThrow(new RuntimeException("foo"));
        when(passengerRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        thrown.expect(RuntimeException.class);
        passengerService.bookActivity("42", "42");
        verify(activityRepository).findById(Mockito.<String>any());
        verify(passengerRepository).save(Mockito.<Passenger>any());
        verify(passengerRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PassengerService#bookActivity(String, String)}
     */
    @Test
    public void testBookActivity3() {
        Activity activity = mock(Activity.class);
        when(activity.getCost()).thenThrow(new RuntimeException("foo"));
        when(activity.getCapacity()).thenReturn(3);
        when(activity.getPassengerIds()).thenReturn(new ArrayList<>());
        doNothing().when(activity).setCapacity(anyInt());
        doNothing().when(activity).setCost(anyDouble());
        doNothing().when(activity).setDescription(Mockito.<String>any());
        doNothing().when(activity).setDestination(Mockito.<String>any());
        doNothing().when(activity).setId(Mockito.<String>any());
        doNothing().when(activity).setName(Mockito.<String>any());
        doNothing().when(activity).setPassengerIds(Mockito.<List<String>>any());
        activity.setCapacity(3);
        activity.setCost(10.0d);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestination("Destination");
        activity.setId("42");
        activity.setName("Name");
        activity.setPassengerIds(new ArrayList<>());
        Optional<Activity> ofResult = Optional.of(activity);
        when(activityRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        Passenger passenger = new Passenger();
        passenger.setActivities(new ArrayList<>());
        passenger.setActivityIds(new ArrayList<>());
        passenger.setBalance(10.0d);
        passenger.setId("42");
        passenger.setName("Name");
        passenger.setPassengerNumber(10);
        passenger.setType(PassengerType.STANDARD);
        Optional<Passenger> ofResult2 = Optional.of(passenger);
        when(passengerRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        thrown.expect(RuntimeException.class);
        passengerService.bookActivity("42", "42");
        verify(activityRepository).findById(Mockito.<String>any());
        verify(activity).getCost();
        verify(activity).getCapacity();
        verify(activity, atLeast(1)).getPassengerIds();
        verify(activity).setCapacity(anyInt());
        verify(activity).setCost(anyDouble());
        verify(activity).setDescription(Mockito.<String>any());
        verify(activity).setDestination(Mockito.<String>any());
        verify(activity).setId(Mockito.<String>any());
        verify(activity).setName(Mockito.<String>any());
        verify(activity).setPassengerIds(Mockito.<List<String>>any());
        verify(passengerRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PassengerService#bookActivity(String, String)}
     */
    @Test
    public void testBookActivity4() {
        Activity activity = mock(Activity.class);
        when(activity.getCapacity()).thenReturn(0);
        when(activity.getPassengerIds()).thenReturn(new ArrayList<>());
        doNothing().when(activity).setCapacity(anyInt());
        doNothing().when(activity).setCost(anyDouble());
        doNothing().when(activity).setDescription(Mockito.<String>any());
        doNothing().when(activity).setDestination(Mockito.<String>any());
        doNothing().when(activity).setId(Mockito.<String>any());
        doNothing().when(activity).setName(Mockito.<String>any());
        doNothing().when(activity).setPassengerIds(Mockito.<List<String>>any());
        activity.setCapacity(3);
        activity.setCost(10.0d);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestination("Destination");
        activity.setId("42");
        activity.setName("Name");
        activity.setPassengerIds(new ArrayList<>());
        Optional<Activity> ofResult = Optional.of(activity);
        when(activityRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        Passenger passenger = new Passenger();
        passenger.setActivities(new ArrayList<>());
        passenger.setActivityIds(new ArrayList<>());
        passenger.setBalance(10.0d);
        passenger.setId("42");
        passenger.setName("Name");
        passenger.setPassengerNumber(10);
        passenger.setType(PassengerType.STANDARD);
        Optional<Passenger> ofResult2 = Optional.of(passenger);
        when(passengerRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        thrown.expect(RuntimeException.class);
        passengerService.bookActivity("42", "42");
        verify(activityRepository).findById(Mockito.<String>any());
        verify(activity).getCapacity();
        verify(activity, atLeast(1)).getPassengerIds();
        verify(activity).setCapacity(anyInt());
        verify(activity).setCost(anyDouble());
        verify(activity).setDescription(Mockito.<String>any());
        verify(activity).setDestination(Mockito.<String>any());
        verify(activity).setId(Mockito.<String>any());
        verify(activity).setName(Mockito.<String>any());
        verify(activity).setPassengerIds(Mockito.<List<String>>any());
        verify(passengerRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PassengerService#bookActivity(String, String)}
     */
    @Test
    public void testBookActivity5() {
        when(activityRepository.findById(Mockito.<String>any())).thenReturn(Optional.empty());
        new RuntimeException("foo");
        new RuntimeException("foo");

        Passenger passenger = new Passenger();
        passenger.setActivities(new ArrayList<>());
        passenger.setActivityIds(new ArrayList<>());
        passenger.setBalance(10.0d);
        passenger.setId("42");
        passenger.setName("Name");
        passenger.setPassengerNumber(10);
        passenger.setType(PassengerType.STANDARD);
        Optional<Passenger> ofResult = Optional.of(passenger);
        when(passengerRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        thrown.expect(RuntimeException.class);
        passengerService.bookActivity("42", "42");
        verify(activityRepository).findById(Mockito.<String>any());
        verify(passengerRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PassengerService#createPassenger(Passenger)}
     */
    @Test
    public void testCreatePassenger() {
        Passenger passenger = new Passenger();
        passenger.setActivities(new ArrayList<>());
        passenger.setActivityIds(new ArrayList<>());
        passenger.setBalance(10.0d);
        passenger.setId("42");
        passenger.setName("Name");
        passenger.setPassengerNumber(10);
        passenger.setType(PassengerType.STANDARD);
        when(passengerRepository.save(Mockito.<Passenger>any())).thenReturn(passenger);

        Passenger passengerDto = new Passenger();
        passengerDto.setActivities(new ArrayList<>());
        passengerDto.setActivityIds(new ArrayList<>());
        passengerDto.setBalance(10.0d);
        passengerDto.setId("42");
        passengerDto.setName("Name");
        passengerDto.setPassengerNumber(10);
        passengerDto.setType(PassengerType.STANDARD);
        assertSame(passenger, passengerService.createPassenger(passengerDto));
        verify(passengerRepository).save(Mockito.<Passenger>any());
    }

    /**
     * Method under test: {@link PassengerService#createPassenger(Passenger)}
     */
    @Test
    public void testCreatePassenger2() {
        when(passengerRepository.save(Mockito.<Passenger>any())).thenThrow(new RuntimeException("foo"));

        Passenger passengerDto = new Passenger();
        passengerDto.setActivities(new ArrayList<>());
        passengerDto.setActivityIds(new ArrayList<>());
        passengerDto.setBalance(10.0d);
        passengerDto.setId("42");
        passengerDto.setName("Name");
        passengerDto.setPassengerNumber(10);
        passengerDto.setType(PassengerType.STANDARD);
        thrown.expect(RuntimeException.class);
        passengerService.createPassenger(passengerDto);
        verify(passengerRepository).save(Mockito.<Passenger>any());
    }

    /**
     * Method under test: {@link PassengerService#updatePassenger(String, Passenger)}
     */
    @Test
    public void testUpdatePassenger() {
        Passenger passenger = new Passenger();
        passenger.setActivities(new ArrayList<>());
        passenger.setActivityIds(new ArrayList<>());
        passenger.setBalance(10.0d);
        passenger.setId("42");
        passenger.setName("Name");
        passenger.setPassengerNumber(10);
        passenger.setType(PassengerType.STANDARD);
        Optional<Passenger> ofResult = Optional.of(passenger);

        Passenger passenger2 = new Passenger();
        passenger2.setActivities(new ArrayList<>());
        passenger2.setActivityIds(new ArrayList<>());
        passenger2.setBalance(10.0d);
        passenger2.setId("42");
        passenger2.setName("Name");
        passenger2.setPassengerNumber(10);
        passenger2.setType(PassengerType.STANDARD);
        when(passengerRepository.save(Mockito.<Passenger>any())).thenReturn(passenger2);
        when(passengerRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        Passenger passengerDto = new Passenger();
        passengerDto.setActivities(new ArrayList<>());
        passengerDto.setActivityIds(new ArrayList<>());
        passengerDto.setBalance(10.0d);
        passengerDto.setId("42");
        passengerDto.setName("Name");
        passengerDto.setPassengerNumber(10);
        passengerDto.setType(PassengerType.STANDARD);
        assertSame(passenger2, passengerService.updatePassenger("42", passengerDto));
        verify(passengerRepository).save(Mockito.<Passenger>any());
        verify(passengerRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PassengerService#updatePassenger(String, Passenger)}
     */
    @Test
    public void testUpdatePassenger2() {
        Passenger passenger = new Passenger();
        passenger.setActivities(new ArrayList<>());
        passenger.setActivityIds(new ArrayList<>());
        passenger.setBalance(10.0d);
        passenger.setId("42");
        passenger.setName("Name");
        passenger.setPassengerNumber(10);
        passenger.setType(PassengerType.STANDARD);
        Optional<Passenger> ofResult = Optional.of(passenger);
        when(passengerRepository.save(Mockito.<Passenger>any())).thenThrow(new RuntimeException("foo"));
        when(passengerRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        Passenger passengerDto = new Passenger();
        passengerDto.setActivities(new ArrayList<>());
        passengerDto.setActivityIds(new ArrayList<>());
        passengerDto.setBalance(10.0d);
        passengerDto.setId("42");
        passengerDto.setName("Name");
        passengerDto.setPassengerNumber(10);
        passengerDto.setType(PassengerType.STANDARD);
        thrown.expect(RuntimeException.class);
        passengerService.updatePassenger("42", passengerDto);
        verify(passengerRepository).save(Mockito.<Passenger>any());
        verify(passengerRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PassengerService#updatePassenger(String, Passenger)}
     */
    @Test
    public void testUpdatePassenger3() {
        when(passengerRepository.findById(Mockito.<String>any())).thenReturn(Optional.empty());

        Passenger passengerDto = new Passenger();
        passengerDto.setActivities(new ArrayList<>());
        passengerDto.setActivityIds(new ArrayList<>());
        passengerDto.setBalance(10.0d);
        passengerDto.setId("42");
        passengerDto.setName("Name");
        passengerDto.setPassengerNumber(10);
        passengerDto.setType(PassengerType.STANDARD);
        thrown.expect(RuntimeException.class);
        passengerService.updatePassenger("42", passengerDto);
        verify(passengerRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PassengerService#deletePassenger(String)}
     */
    @Test
    public void testDeletePassenger() {
        Passenger passenger = new Passenger();
        passenger.setActivities(new ArrayList<>());
        passenger.setActivityIds(new ArrayList<>());
        passenger.setBalance(10.0d);
        passenger.setId("42");
        passenger.setName("Name");
        passenger.setPassengerNumber(10);
        passenger.setType(PassengerType.STANDARD);
        Optional<Passenger> ofResult = Optional.of(passenger);
        doNothing().when(passengerRepository).delete(Mockito.<Passenger>any());
        when(passengerRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        passengerService.deletePassenger("42");
        verify(passengerRepository).findById(Mockito.<String>any());
        verify(passengerRepository).delete(Mockito.<Passenger>any());
        assertTrue(passengerService.getAllPassengers().isEmpty());
    }

    /**
     * Method under test: {@link PassengerService#deletePassenger(String)}
     */
    @Test
    public void testDeletePassenger2() {
        Passenger passenger = new Passenger();
        passenger.setActivities(new ArrayList<>());
        passenger.setActivityIds(new ArrayList<>());
        passenger.setBalance(10.0d);
        passenger.setId("42");
        passenger.setName("Name");
        passenger.setPassengerNumber(10);
        passenger.setType(PassengerType.STANDARD);
        Optional<Passenger> ofResult = Optional.of(passenger);
        doThrow(new RuntimeException("foo")).when(passengerRepository).delete(Mockito.<Passenger>any());
        when(passengerRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        thrown.expect(RuntimeException.class);
        passengerService.deletePassenger("42");
        verify(passengerRepository).findById(Mockito.<String>any());
        verify(passengerRepository).delete(Mockito.<Passenger>any());
    }

    /**
     * Method under test: {@link PassengerService#deletePassenger(String)}
     */
    @Test
    public void testDeletePassenger3() {
        when(passengerRepository.findById(Mockito.<String>any())).thenReturn(Optional.empty());
        thrown.expect(RuntimeException.class);
        passengerService.deletePassenger("42");
        verify(passengerRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link PassengerService#getAllPassengers()}
     */
    @Test
    public void testGetAllPassengers() {
        ArrayList<Passenger> passengerList = new ArrayList<>();
        when(passengerRepository.findAll()).thenReturn(passengerList);
        List<Passenger> actualAllPassengers = passengerService.getAllPassengers();
        assertSame(passengerList, actualAllPassengers);
        assertTrue(actualAllPassengers.isEmpty());
        verify(passengerRepository).findAll();
    }

    /**
     * Method under test: {@link PassengerService#getAllPassengers()}
     */
    @Test
    public void testGetAllPassengers2() {
        when(passengerRepository.findAll()).thenThrow(new RuntimeException("foo"));
        thrown.expect(RuntimeException.class);
        passengerService.getAllPassengers();
        verify(passengerRepository).findAll();
    }
}


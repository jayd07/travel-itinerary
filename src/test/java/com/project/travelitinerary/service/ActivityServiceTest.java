package com.project.travelitinerary.service;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.travelitinerary.model.Activity;
import com.project.travelitinerary.model.Passenger;
import com.project.travelitinerary.repository.ActivityRepository;
import com.project.travelitinerary.repository.PassengerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.junit.Rule;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {ActivityService.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ActivityServiceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @MockBean
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityService activityService;

    @MockBean
    private PassengerRepository passengerRepository;

    /**
     * Method under test: {@link ActivityService#getActivityPassengers(String)}
     */
    @Test
    public void testGetActivityPassengers() {
        Activity activity = new Activity();
        activity.setCapacity(3);
        activity.setCost(10.0d);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestination("Destination");
        activity.setId("42");
        activity.setName("Name");
        activity.setPassengerIds(new ArrayList<>());
        Optional<Activity> ofResult = Optional.of(activity);
        when(activityRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        Iterable<Passenger> iterable = mock(Iterable.class);
        doNothing().when(iterable).forEach(Mockito.<Consumer<Passenger>>any());
        when(passengerRepository.findAllById(Mockito.<Iterable<String>>any())).thenReturn(iterable);
        assertTrue(activityService.getActivityPassengers("42").isEmpty());
        verify(activityRepository).findById(Mockito.<String>any());
        verify(passengerRepository).findAllById(Mockito.<Iterable<String>>any());
        verify(iterable).forEach(Mockito.<Consumer<Passenger>>any());
    }

    /**
     * Method under test: {@link ActivityService#getActivityPassengers(String)}
     */
    @Test
    public void testGetActivityPassengers2() {
        Activity activity = new Activity();
        activity.setCapacity(3);
        activity.setCost(10.0d);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestination("Destination");
        activity.setId("42");
        activity.setName("Name");
        activity.setPassengerIds(new ArrayList<>());
        Optional<Activity> ofResult = Optional.of(activity);
        when(activityRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        Iterable<Passenger> iterable = mock(Iterable.class);
        doThrow(new RuntimeException("foo")).when(iterable).forEach(Mockito.<Consumer<Passenger>>any());
        when(passengerRepository.findAllById(Mockito.<Iterable<String>>any())).thenReturn(iterable);
        thrown.expect(RuntimeException.class);
        activityService.getActivityPassengers("42");
        verify(activityRepository).findById(Mockito.<String>any());
        verify(passengerRepository).findAllById(Mockito.<Iterable<String>>any());
        verify(iterable).forEach(Mockito.<Consumer<Passenger>>any());
    }

    /**
     * Method under test: {@link ActivityService#getActivityPassengers(String)}
     */
    @Test
    public void testGetActivityPassengers3() {
        when(activityRepository.findById(Mockito.<String>any())).thenReturn(Optional.empty());
        thrown.expect(RuntimeException.class);
        activityService.getActivityPassengers("42");
        verify(activityRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ActivityService#getActivity(String)}
     */
    @Test
    public void testGetActivity() {
        Activity activity = new Activity();
        activity.setCapacity(3);
        activity.setCost(10.0d);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestination("Destination");
        activity.setId("42");
        activity.setName("Name");
        activity.setPassengerIds(new ArrayList<>());
        Optional<Activity> ofResult = Optional.of(activity);
        when(activityRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        assertSame(activity, activityService.getActivity("42"));
        verify(activityRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ActivityService#getActivity(String)}
     */
    @Test
    public void testGetActivity2() {
        when(activityRepository.findById(Mockito.<String>any())).thenReturn(Optional.empty());
        thrown.expect(RuntimeException.class);
        activityService.getActivity("42");
        verify(activityRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ActivityService#getActivity(String)}
     */
    @Test
    public void testGetActivity3() {
        when(activityRepository.findById(Mockito.<String>any())).thenThrow(new RuntimeException("Activity not found"));
        thrown.expect(RuntimeException.class);
        activityService.getActivity("42");
        verify(activityRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ActivityService#createActivity(Activity)}
     */
    @Test
    public void testCreateActivity() {
        Activity activity = new Activity();
        activity.setCapacity(3);
        activity.setCost(10.0d);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestination("Destination");
        activity.setId("42");
        activity.setName("Name");
        activity.setPassengerIds(new ArrayList<>());
        when(activityRepository.save(Mockito.<Activity>any())).thenReturn(activity);

        Activity activityDto = new Activity();
        activityDto.setCapacity(3);
        activityDto.setCost(10.0d);
        activityDto.setDescription("The characteristics of someone or something");
        activityDto.setDestination("Destination");
        activityDto.setId("42");
        activityDto.setName("Name");
        activityDto.setPassengerIds(new ArrayList<>());
        assertSame(activity, activityService.createActivity(activityDto));
        verify(activityRepository).save(Mockito.<Activity>any());
    }

    /**
     * Method under test: {@link ActivityService#createActivity(Activity)}
     */
    @Test
    public void testCreateActivity2() {
        when(activityRepository.save(Mockito.<Activity>any())).thenThrow(new RuntimeException("foo"));

        Activity activityDto = new Activity();
        activityDto.setCapacity(3);
        activityDto.setCost(10.0d);
        activityDto.setDescription("The characteristics of someone or something");
        activityDto.setDestination("Destination");
        activityDto.setId("42");
        activityDto.setName("Name");
        activityDto.setPassengerIds(new ArrayList<>());
        thrown.expect(RuntimeException.class);
        activityService.createActivity(activityDto);
        verify(activityRepository).save(Mockito.<Activity>any());
    }

    /**
     * Method under test: {@link ActivityService#updateActivity(String, Activity)}
     */
    @Test
    public void testUpdateActivity() {
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

        Activity activityDto = new Activity();
        activityDto.setCapacity(3);
        activityDto.setCost(10.0d);
        activityDto.setDescription("The characteristics of someone or something");
        activityDto.setDestination("Destination");
        activityDto.setId("42");
        activityDto.setName("Name");
        activityDto.setPassengerIds(new ArrayList<>());
        assertSame(activity2, activityService.updateActivity("42", activityDto));
        verify(activityRepository).save(Mockito.<Activity>any());
        verify(activityRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ActivityService#updateActivity(String, Activity)}
     */
    @Test
    public void testUpdateActivity2() {
        Activity activity = new Activity();
        activity.setCapacity(3);
        activity.setCost(10.0d);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestination("Destination");
        activity.setId("42");
        activity.setName("Name");
        activity.setPassengerIds(new ArrayList<>());
        Optional<Activity> ofResult = Optional.of(activity);
        when(activityRepository.save(Mockito.<Activity>any())).thenThrow(new RuntimeException("foo"));
        when(activityRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        Activity activityDto = new Activity();
        activityDto.setCapacity(3);
        activityDto.setCost(10.0d);
        activityDto.setDescription("The characteristics of someone or something");
        activityDto.setDestination("Destination");
        activityDto.setId("42");
        activityDto.setName("Name");
        activityDto.setPassengerIds(new ArrayList<>());
        thrown.expect(RuntimeException.class);
        activityService.updateActivity("42", activityDto);
        verify(activityRepository).save(Mockito.<Activity>any());
        verify(activityRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ActivityService#updateActivity(String, Activity)}
     */
    @Test
    public void testUpdateActivity3() {
        when(activityRepository.findById(Mockito.<String>any())).thenReturn(Optional.empty());

        Activity activityDto = new Activity();
        activityDto.setCapacity(3);
        activityDto.setCost(10.0d);
        activityDto.setDescription("The characteristics of someone or something");
        activityDto.setDestination("Destination");
        activityDto.setId("42");
        activityDto.setName("Name");
        activityDto.setPassengerIds(new ArrayList<>());
        thrown.expect(RuntimeException.class);
        activityService.updateActivity("42", activityDto);
        verify(activityRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ActivityService#deleteActivity(String)}
     */
    @Test
    public void testDeleteActivity() {
        Activity activity = new Activity();
        activity.setCapacity(3);
        activity.setCost(10.0d);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestination("Destination");
        activity.setId("42");
        activity.setName("Name");
        activity.setPassengerIds(new ArrayList<>());
        Optional<Activity> ofResult = Optional.of(activity);
        doNothing().when(activityRepository).delete(Mockito.<Activity>any());
        when(activityRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        activityService.deleteActivity("42");
        verify(activityRepository).findById(Mockito.<String>any());
        verify(activityRepository).delete(Mockito.<Activity>any());
        assertTrue(activityService.getAllActivities().isEmpty());
    }

    /**
     * Method under test: {@link ActivityService#deleteActivity(String)}
     */
    @Test
    public void testDeleteActivity2() {
        Activity activity = new Activity();
        activity.setCapacity(3);
        activity.setCost(10.0d);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestination("Destination");
        activity.setId("42");
        activity.setName("Name");
        activity.setPassengerIds(new ArrayList<>());
        Optional<Activity> ofResult = Optional.of(activity);
        doThrow(new RuntimeException("foo")).when(activityRepository).delete(Mockito.<Activity>any());
        when(activityRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        thrown.expect(RuntimeException.class);
        activityService.deleteActivity("42");
        verify(activityRepository).findById(Mockito.<String>any());
        verify(activityRepository).delete(Mockito.<Activity>any());
    }

    /**
     * Method under test: {@link ActivityService#deleteActivity(String)}
     */
    @Test
    public void testDeleteActivity3() {
        when(activityRepository.findById(Mockito.<String>any())).thenReturn(Optional.empty());
        thrown.expect(RuntimeException.class);
        activityService.deleteActivity("42");
        verify(activityRepository).findById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ActivityService#getAllActivities()}
     */
    @Test
    public void testGetAllActivities() {
        ArrayList<Activity> activityList = new ArrayList<>();
        when(activityRepository.findAll()).thenReturn(activityList);
        List<Activity> actualAllActivities = activityService.getAllActivities();
        assertSame(activityList, actualAllActivities);
        assertTrue(actualAllActivities.isEmpty());
        verify(activityRepository).findAll();
    }

    /**
     * Method under test: {@link ActivityService#getAllActivities()}
     */
    @Test
    public void testGetAllActivities2() {
        when(activityRepository.findAll()).thenThrow(new RuntimeException("foo"));
        thrown.expect(RuntimeException.class);
        activityService.getAllActivities();
        verify(activityRepository).findAll();
    }
}


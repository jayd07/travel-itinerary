package com.project.travelitinerary.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.travelitinerary.model.Activity;
import com.project.travelitinerary.model.Passenger;
import com.project.travelitinerary.model.PassengerType;
import com.project.travelitinerary.repository.ActivityRepository;
import com.project.travelitinerary.repository.DestinationRepository;
import com.project.travelitinerary.repository.PassengerRepository;
import com.project.travelitinerary.repository.TravelPackageRepository;
import com.project.travelitinerary.service.PassengerService;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PassengerController.class, PassengerService.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class PassengerControllerTest {
    @MockBean
    private ActivityRepository activityRepository;

    @MockBean
    private DestinationRepository destinationRepository;

    @Autowired
    private PassengerController passengerController;

    @MockBean
    private PassengerRepository passengerRepository;

    @MockBean
    private TravelPackageRepository travelPackageRepository;

    /**
     * Method under test: {@link PassengerController#bookActivity(String, String)}
     */
    @Test
    public void testBookActivity() throws Exception {
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

        Activity activity = new Activity();
        activity.setCapacity(3);
        activity.setCost(10.0d);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestination("Destination");
        activity.setId("42");
        activity.setName("Name");
        activity.setPassengerIds(new ArrayList<>());
        Optional<Activity> ofResult2 = Optional.of(activity);

        Activity activity2 = new Activity();
        activity2.setCapacity(3);
        activity2.setCost(10.0d);
        activity2.setDescription("The characteristics of someone or something");
        activity2.setDestination("Destination");
        activity2.setId("42");
        activity2.setName("Name");
        activity2.setPassengerIds(new ArrayList<>());
        when(activityRepository.save(Mockito.<Activity>any())).thenReturn(activity2);
        when(activityRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/passengers/{passengerId}/book-activity/{activityId}", "42", "42");
        MockMvcBuilders.standaloneSetup(passengerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link PassengerController#bookActivity(String, String)}
     */
    @Test
    public void testBookActivity2() throws Exception {
        Passenger passenger = new Passenger();
        passenger.setActivities(new ArrayList<>());
        passenger.setActivityIds(new ArrayList<>());
        passenger.setBalance(10.0d);
        passenger.setId("42");
        passenger.setName("Name");
        passenger.setPassengerNumber(10);
        passenger.setType(PassengerType.GOLD);
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

        Activity activity = new Activity();
        activity.setCapacity(3);
        activity.setCost(10.0d);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestination("Destination");
        activity.setId("42");
        activity.setName("Name");
        activity.setPassengerIds(new ArrayList<>());
        Optional<Activity> ofResult2 = Optional.of(activity);

        Activity activity2 = new Activity();
        activity2.setCapacity(3);
        activity2.setCost(10.0d);
        activity2.setDescription("The characteristics of someone or something");
        activity2.setDestination("Destination");
        activity2.setId("42");
        activity2.setName("Name");
        activity2.setPassengerIds(new ArrayList<>());
        when(activityRepository.save(Mockito.<Activity>any())).thenReturn(activity2);
        when(activityRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/passengers/{passengerId}/book-activity/{activityId}", "42", "42");
        MockMvcBuilders.standaloneSetup(passengerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link PassengerController#bookActivity(String, String)}
     */
    @Test
    public void testBookActivity3() throws Exception {
        Passenger passenger = new Passenger();
        passenger.setActivities(new ArrayList<>());
        passenger.setActivityIds(new ArrayList<>());
        passenger.setBalance(10.0d);
        passenger.setId("42");
        passenger.setName("Name");
        passenger.setPassengerNumber(10);
        passenger.setType(PassengerType.PREMIUM);
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

        Activity activity = new Activity();
        activity.setCapacity(3);
        activity.setCost(10.0d);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestination("Destination");
        activity.setId("42");
        activity.setName("Name");
        activity.setPassengerIds(new ArrayList<>());
        Optional<Activity> ofResult2 = Optional.of(activity);

        Activity activity2 = new Activity();
        activity2.setCapacity(3);
        activity2.setCost(10.0d);
        activity2.setDescription("The characteristics of someone or something");
        activity2.setDestination("Destination");
        activity2.setId("42");
        activity2.setName("Name");
        activity2.setPassengerIds(new ArrayList<>());
        when(activityRepository.save(Mockito.<Activity>any())).thenReturn(activity2);
        when(activityRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/passengers/{passengerId}/book-activity/{activityId}", "42", "42");
        MockMvcBuilders.standaloneSetup(passengerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link PassengerController#createPassenger(Passenger)}
     */
    @Test
    public void testCreatePassenger() throws Exception {
        when(passengerRepository.findAll()).thenReturn(new ArrayList<>());

        Passenger passenger = new Passenger();
        passenger.setActivities(new ArrayList<>());
        passenger.setActivityIds(new ArrayList<>());
        passenger.setBalance(10.0d);
        passenger.setId("42");
        passenger.setName("Name");
        passenger.setPassengerNumber(10);
        passenger.setType(PassengerType.STANDARD);
        String content = (new ObjectMapper()).writeValueAsString(passenger);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/passengers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(passengerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PassengerController#deletePassenger(String)}
     */
    @Test
    public void testDeletePassenger() throws Exception {
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/passengers/{passengerId}", "42");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(passengerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link PassengerController#getAllPassengers()}
     */
    @Test
    public void testGetAllPassengers() throws Exception {
        when(passengerRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/passengers");
        MockMvcBuilders.standaloneSetup(passengerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PassengerController#getAllPassengers()}
     */
    @Test
    public void testGetAllPassengers2() throws Exception {
        when(passengerRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/passengers");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(passengerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PassengerController#getPassengerDetails(String)}
     */
    @Test
    public void testGetPassengerDetails() throws Exception {
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/passengers/{passengerId}", "42");
        MockMvcBuilders.standaloneSetup(passengerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"name\":\"Name\",\"passengerNumber\":\"10\",\"balance\":10.0,\"activities\":[]}"));
    }

    /**
     * Method under test: {@link PassengerController#getPassengerDetails(String)}
     */
    @Test
    public void testGetPassengerDetails2() throws Exception {
        Activity activity = new Activity();
        activity.setCapacity(3);
        activity.setCost(10.0d);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestination("?");
        activity.setId("42");
        activity.setName("?");
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/passengers/{passengerId}", "42");
        MockMvcBuilders.standaloneSetup(passengerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"name\":\"Name\",\"passengerNumber\":\"10\",\"balance\":10.0,\"activities\":[{\"name\":\"?\",\"description\":\"The"
                                        + " characteristics of someone or something\",\"cost\":10.0,\"capacity\":3,\"destination\":null}]}"));
    }

    /**
     * Method under test: {@link PassengerController#getPassengerDetails(String)}
     */
    @Test
    public void testGetPassengerDetails3() throws Exception {
        when(passengerRepository.findAll()).thenReturn(new ArrayList<>());
        when(passengerRepository.findById(Mockito.<String>any())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/passengers/{passengerId}", "",
                "Uri Vars");
        MockMvcBuilders.standaloneSetup(passengerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PassengerController#updatePassenger(String, Passenger)}
     */
    @Test
    public void testUpdatePassenger() throws Exception {
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

        Passenger passenger3 = new Passenger();
        passenger3.setActivities(new ArrayList<>());
        passenger3.setActivityIds(new ArrayList<>());
        passenger3.setBalance(10.0d);
        passenger3.setId("42");
        passenger3.setName("Name");
        passenger3.setPassengerNumber(10);
        passenger3.setType(PassengerType.STANDARD);
        String content = (new ObjectMapper()).writeValueAsString(passenger3);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/passengers/{passengerId}/update", "42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(passengerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"name\":\"Name\",\"passengerNumber\":10,\"type\":\"STANDARD\",\"balance\":10.0,\"activities\":[],"
                                        + "\"activityIds\":[]}"));
    }
}


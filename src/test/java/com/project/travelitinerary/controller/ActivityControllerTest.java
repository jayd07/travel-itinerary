package com.project.travelitinerary.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.travelitinerary.model.Activity;
import com.project.travelitinerary.repository.ActivityRepository;
import com.project.travelitinerary.repository.PassengerRepository;
import com.project.travelitinerary.service.ActivityService;

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

@ContextConfiguration(classes = {ActivityController.class, ActivityService.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ActivityControllerTest {
    @Autowired
    private ActivityController activityController;

    @MockBean
    private ActivityRepository activityRepository;

    @MockBean
    private PassengerRepository passengerRepository;

    /**
     * Method under test: {@link ActivityController#createActivity(Activity)}
     */
    @Test
    public void testCreateActivity() throws Exception {
        when(activityRepository.findAll()).thenReturn(new ArrayList<>());

        Activity activity = new Activity();
        activity.setCapacity(3);
        activity.setCost(10.0d);
        activity.setDescription("The characteristics of someone or something");
        activity.setDestination("Destination");
        activity.setId("42");
        activity.setName("Name");
        activity.setPassengerIds(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(activity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/activities")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(activityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ActivityController#deleteActivity(String)}
     */
    @Test
    public void testDeleteActivity() throws Exception {
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/activities/{activityId}", "42");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(activityController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link ActivityController#getActivity(String)}
     */
    @Test
    public void testGetActivity() throws Exception {
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/activities/{activityId}", "42");
        MockMvcBuilders.standaloneSetup(activityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"cost\":10.0,"
                                        + "\"capacity\":3,\"destination\":\"Destination\",\"passengerIds\":[]}"));
    }

    /**
     * Method under test: {@link ActivityController#getActivity(String)}
     */
    @Test
    public void testGetActivity2() throws Exception {
        when(activityRepository.findAll()).thenReturn(new ArrayList<>());
        when(activityRepository.findById(Mockito.<String>any())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/activities/{activityId}", "",
                "Uri Vars");
        MockMvcBuilders.standaloneSetup(activityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ActivityController#getAllActivities()}
     */
    @Test
    public void testGetAllActivities() throws Exception {
        when(activityRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/activities");
        MockMvcBuilders.standaloneSetup(activityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ActivityController#getAllActivities()}
     */
    @Test
    public void testGetAllActivities2() throws Exception {
        when(activityRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/activities");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(activityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ActivityController#updateActivity(String, Activity)}
     */
    @Test
    public void testUpdateActivity() throws Exception {
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

        Activity activity3 = new Activity();
        activity3.setCapacity(3);
        activity3.setCost(10.0d);
        activity3.setDescription("The characteristics of someone or something");
        activity3.setDestination("Destination");
        activity3.setId("42");
        activity3.setName("Name");
        activity3.setPassengerIds(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(activity3);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/activities/{activityId}", "42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(activityController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"cost\":10.0,"
                                        + "\"capacity\":3,\"destination\":\"Destination\",\"passengerIds\":[]}"));
    }
}


package com.project.travelitinerary.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.travelitinerary.model.Destination;
import com.project.travelitinerary.model.TravelPackage;
import com.project.travelitinerary.repository.ActivityRepository;
import com.project.travelitinerary.repository.DestinationRepository;
import com.project.travelitinerary.repository.TravelPackageRepository;
import com.project.travelitinerary.service.TravelPackageService;

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

@ContextConfiguration(classes = {TravelPackageController.class, TravelPackageService.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class TravelPackageControllerTest {
    @MockBean
    private ActivityRepository activityRepository;

    @MockBean
    private DestinationRepository destinationRepository;

    @Autowired
    private TravelPackageController travelPackageController;

    @MockBean
    private TravelPackageRepository travelPackageRepository;

    /**
     * Method under test: {@link TravelPackageController#createTravelPackage(TravelPackage)}
     */
    @Test
    public void testCreateTravelPackage() throws Exception {
        when(travelPackageRepository.findAll()).thenReturn(new ArrayList<>());

        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setId("42");
        travelPackage.setItinerary(new ArrayList<>());
        travelPackage.setName("Name");
        travelPackage.setPassengerCapacity(1);
        travelPackage.setPassengers(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(travelPackage);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/travel-packages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(travelPackageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TravelPackageController#deleteTravelPackage(String)}
     */
    @Test
    public void testDeleteTravelPackage() throws Exception {
        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setId("42");
        travelPackage.setItinerary(new ArrayList<>());
        travelPackage.setName("Name");
        travelPackage.setPassengerCapacity(1);
        travelPackage.setPassengers(new ArrayList<>());
        Optional<TravelPackage> ofResult = Optional.of(travelPackage);
        doNothing().when(travelPackageRepository).delete(Mockito.<TravelPackage>any());
        when(travelPackageRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/travel-packages/{travelPackageId}",
                "42");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(travelPackageController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link TravelPackageController#getAllTravelPackages()}
     */
    @Test
    public void testGetAllTravelPackages() throws Exception {
        when(travelPackageRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/travel-packages");
        MockMvcBuilders.standaloneSetup(travelPackageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TravelPackageController#getAllTravelPackages()}
     */
    @Test
    public void testGetAllTravelPackages2() throws Exception {
        when(travelPackageRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/travel-packages");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(travelPackageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TravelPackageController#getTravelPackageItinerary(String)}
     */
    @Test
    public void testGetTravelPackageItinerary() throws Exception {
        TravelPackage travelPackage = new TravelPackage();
        travelPackage.setId("42");
        travelPackage.setItinerary(new ArrayList<>());
        travelPackage.setName("Name");
        travelPackage.setPassengerCapacity(1);
        travelPackage.setPassengers(new ArrayList<>());
        Optional<TravelPackage> ofResult = Optional.of(travelPackage);
        when(travelPackageRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/travel-packages/{travelPackageId}/itinerary", "42");
        MockMvcBuilders.standaloneSetup(travelPackageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TravelPackageController#getTravelPackageItinerary(String)}
     */
    @Test
    public void testGetTravelPackageItinerary2() throws Exception {
        Destination destination = new Destination();
        destination.setActivities(new ArrayList<>());
        destination.setId("42");
        destination.setName("?");

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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/travel-packages/{travelPackageId}/itinerary", "42");
        MockMvcBuilders.standaloneSetup(travelPackageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[{\"destinationName\":\"?\",\"activities\":[]}]"));
    }

    /**
     * Method under test: {@link TravelPackageController#updateTravelPackage(String, TravelPackage)}
     */
    @Test
    public void testUpdateTravelPackage() throws Exception {
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

        TravelPackage travelPackage3 = new TravelPackage();
        travelPackage3.setId("42");
        travelPackage3.setItinerary(new ArrayList<>());
        travelPackage3.setName("Name");
        travelPackage3.setPassengerCapacity(1);
        travelPackage3.setPassengers(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(travelPackage3);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/travel-packages/{travelPackageId}/update", "42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(travelPackageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":\"42\",\"name\":\"Name\",\"passengerCapacity\":1,\"passengers\":[],\"itinerary\":[]}"));
    }

    /**
     * Method under test: {@link TravelPackageController#updateTravelPackage(String, TravelPackage)}
     */
    @Test
    public void testUpdateTravelPackage2() throws Exception {
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
        destination.setName("?");

        ArrayList<Destination> itinerary = new ArrayList<>();
        itinerary.add(destination);

        TravelPackage travelPackage3 = new TravelPackage();
        travelPackage3.setId("42");
        travelPackage3.setItinerary(itinerary);
        travelPackage3.setName("Name");
        travelPackage3.setPassengerCapacity(1);
        travelPackage3.setPassengers(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(travelPackage3);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/travel-packages/{travelPackageId}/update", "42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(travelPackageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":\"42\",\"name\":\"Name\",\"passengerCapacity\":1,\"passengers\":[],\"itinerary\":[]}"));
    }
}


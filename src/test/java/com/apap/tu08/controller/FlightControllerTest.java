package com.apap.tu08.controller;

import com.apap.tu08.model.FlightModel;
import com.apap.tu08.service.FlightService;
import com.apap.tu08.service.PilotService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
public class FlightControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FlightService flightService;

    @MockBean
    private PilotService pilotService;

    @Test
    public void givenFlithNumber_whenViewFliht_thenReturnJsonFlight() throws Exception {
        //Given
        FlightModel flightModel = new FlightModel();
        flightModel.setFlightNumber("1765");
        flightModel.setOrigin("Jakarta");
        flightModel.setDestination("Bali");
        flightModel.setTime(new Date(System.currentTimeMillis()));
        Optional<FlightModel> flight = Optional.of(flightModel);
        Mockito.when(flightService.getFlightDetailByFlightNumber(flight.get().getFlightNumber())).thenReturn(flight);

        //when
        mvc.perform(MockMvcRequestBuilders.get("/flight/view")
                .param("flightNumber", flight.get().getFlightNumber())
                .contentType(MediaType.APPLICATION_JSON))
                //Then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.flightNumber", Matchers.is(flight.get().getFlightNumber())));

    }
}

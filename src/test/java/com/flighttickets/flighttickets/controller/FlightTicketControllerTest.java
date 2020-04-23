package com.flighttickets.flighttickets.controller;

import com.flighttickets.flighttickets.TestUtils;
import com.flighttickets.flighttickets.entity.FlightTicket;
import com.flighttickets.flighttickets.model.FlightTicketRequest;
import com.flighttickets.flighttickets.service.FlightTicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FlightTicketControllerTest {

    @Mock
    FlightTicketService service;

    FlightTicketController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new FlightTicketController(service);
    }

    @Test
    public void shouldCreateNewFlightTicketAndReturn200Response() {
        when(service.createNewTicket(any(FlightTicketRequest.class))).thenReturn(Long.valueOf(1));

        ResponseEntity response = controller.createFlightTicket(new FlightTicketRequest());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), Long.valueOf(1));
    }

    @Test
    public void shouldGetTicketByIdAndReturnTicketAnd200Response() {
        FlightTicket returnedTicket = TestUtils.createDummyFlightTicket();

        when(service.getTicketById(any(Long.class))).thenReturn(returnedTicket);

        ResponseEntity response = controller.getTicketById(Long.valueOf(1));

        FlightTicket expectedTicket = (FlightTicket) response.getBody();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(expectedTicket, returnedTicket);

    }

    @Test
    public void shouldGetTicketByIdAndReturnNoTicketFoundMessageAnd200Response() {

        when(service.getTicketById(any(Long.class))).thenReturn(null);

        ResponseEntity response = controller.getTicketById(Long.valueOf(1));

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), "No ticket found with Id 1");

    }
}
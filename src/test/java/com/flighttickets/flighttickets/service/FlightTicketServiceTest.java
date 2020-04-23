package com.flighttickets.flighttickets.service;

import com.flighttickets.flighttickets.TestUtils;
import com.flighttickets.flighttickets.entity.FlightTicket;
import com.flighttickets.flighttickets.model.FlightTicketRequest;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FlightTicketServiceTest {

    @Mock
    EntityManager em;

    @Mock
    Session session;

    FlightTicketService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new FlightTicketService(em);

        when(em.unwrap(Session.class)).thenReturn(session);
    }

    @Test
    public void shouldGetTicketByIdAndReturnFoundTicket() {
        FlightTicket expectedTicket = TestUtils.createDummyFlightTicket();
        when(session.get(FlightTicket.class, Long.valueOf(1))).thenReturn(expectedTicket);

        FlightTicket returnedTicket = service.getTicketById(Long.valueOf(1));

        verify(session).get(FlightTicket.class, Long.valueOf(1));
        assertEquals(expectedTicket, returnedTicket);

    }

    @Test
    public void shouldGetTicketByIdAndReturnNullWhenNotFound() {
        when(session.get(FlightTicket.class, Long.valueOf(2))).thenReturn(null);

        FlightTicket returnedTicket = service.getTicketById(Long.valueOf(2));

        verify(session).get(FlightTicket.class, Long.valueOf(2));
        assertNull(returnedTicket);

    }

    @Test
    public void shouldCreateAndSaveFlightTicket() {
        when(session.save(any(FlightTicket.class))).thenReturn(Long.valueOf(1));
        Long insertedTicketId = service.createNewTicket(TestUtils.createDummyFlightTicketRequest());

        verify(session).save(any(FlightTicket.class));
        assertEquals(insertedTicketId, Long.valueOf(1));

    }

    @Test
    public void shouldThrowExceptionWhenSavingTicketWithEmptyDepartureDate() {
        FlightTicketRequest request = TestUtils.createDummyFlightTicketRequest();
        request.setDepartureDate("");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.createNewTicket(request);
        });

        String expectedMessage = "Ticket is not valid: departureDate Can't be empty nor null";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage,expectedMessage);
    }

    @Test
    public void shouldThrowExceptionWhenSavingTicketWithNullArrivalDate() {
        FlightTicketRequest request = TestUtils.createDummyFlightTicketRequest();
        request.setArrivalDate(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.createNewTicket(request);
        });

        String expectedMessage = "Ticket is not valid: arrivalDate Can't be empty nor null";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage,expectedMessage);
    }

    @Test
    public void shouldThrowExceptionWhenSavingTicketWithEmptyOriginCity() {
        FlightTicketRequest request = TestUtils.createDummyFlightTicketRequest();
        request.setOriginCity("");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.createNewTicket(request);
        });

        String expectedMessage = "Ticket is not valid: originCity Can't be empty nor null";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage,expectedMessage);
    }

    @Test
    public void shouldThrowExceptionWhenSavingTicketWithNullDestinationCity() {
        FlightTicketRequest request = TestUtils.createDummyFlightTicketRequest();
        request.setDestinationCity(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.createNewTicket(request);
        });

        String expectedMessage = "Ticket is not valid: destinationCity Can't be empty nor null";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage,expectedMessage);
    }

    @Test
    public void shouldThrowExceptionWhenSavingTicketWithEmptyPassengerName() {
        FlightTicketRequest request = TestUtils.createDummyFlightTicketRequest();
        request.setPassengerName("");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.createNewTicket(request);
        });

        String expectedMessage = "Ticket is not valid: passengerName Can't be empty nor null";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage,expectedMessage);
    }

    @Test
    public void shouldThrowExceptionWhenSavingTicketWithPassengerAgeLessThanOne() {
        FlightTicketRequest request = TestUtils.createDummyFlightTicketRequest();
        request.setPassengerAge(0);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.createNewTicket(request);
        });

        String expectedMessage = "Ticket is not valid: passengerAge Must be a positive number";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage,expectedMessage);
    }

    @Test
    public void shouldThrowExceptionWhenSavingTicketWithNullHasLuggage() {
        FlightTicketRequest request = TestUtils.createDummyFlightTicketRequest();
        request.setHasLuggage(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.createNewTicket(request);
        });

        String expectedMessage = "Ticket is not valid: hasLuggage Can't be empty nor null";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage,expectedMessage);
    }

    @Test
    public void shouldThrowExceptionWhenSavingTicketWithNegativePrice() {
        FlightTicketRequest request = TestUtils.createDummyFlightTicketRequest();
        request.setPrice(-375.25);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.createNewTicket(request);
        });

        String expectedMessage = "Ticket is not valid: price Must be a positive number or zero";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage,expectedMessage);
    }

    @Test
    public void shouldThrowExceptionWhenSavingTicketWithEmptyDepartureTime() {
        FlightTicketRequest request = TestUtils.createDummyFlightTicketRequest();
        request.setDepartureTime("");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.createNewTicket(request);
        });

        String expectedMessage = "Ticket is not valid: departureTime Can't be empty nor null";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage,expectedMessage);
    }

    @Test
    public void shouldThrowExceptionWhenSavingTicketWithNullArrivalTime() {
        FlightTicketRequest request = TestUtils.createDummyFlightTicketRequest();
        request.setArrivalTime(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.createNewTicket(request);
        });

        String expectedMessage = "Ticket is not valid: arrivalTime Can't be empty nor null";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage,expectedMessage);
    }

    @Test
    public void shouldThrowExceptionWhenSavingTicketArrivalDateTimeBeforeDepartureDateTime() {
        FlightTicketRequest request = TestUtils.createDummyFlightTicketRequest();
        request.setArrivalDate("2020-10-09");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.createNewTicket(request);
        });

        String expectedMessage = "Ticket is not valid: Arrival can't be before Departure";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage,expectedMessage);
    }

}
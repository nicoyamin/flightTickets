package com.flighttickets.flighttickets;

import com.flighttickets.flighttickets.entity.FlightTicket;
import com.flighttickets.flighttickets.model.FlightTicketRequest;

import java.time.LocalDate;
import java.time.LocalTime;

public final class TestUtils {

    public static FlightTicketRequest createDummyFlightTicketRequest() {
        FlightTicketRequest request = new FlightTicketRequest();

        request.setArrivalDate("2020-10-12");
        request.setDepartureDate("2020-10-10");
        request.setArrivalTime("13:00");
        request.setDepartureTime("15:00");
        request.setDestinationCity("New York");
        request.setOriginCity("Sydney");
        request.setHasLuggage(false);
        request.setPassengerAge(30);
        request.setPassengerName("John Doe");
        request.setPrice(150.00);

        return request;
    }

    public static FlightTicket createDummyFlightTicket() {
        FlightTicket flightTicket = new FlightTicket();

        flightTicket.setId(Long.valueOf(1));
        flightTicket.setArrivalDate(LocalDate.parse("2020-10-12"));
        flightTicket.setDepartureDate(LocalDate.parse("2020-10-10"));
        flightTicket.setArrivalTime(LocalTime.parse("13:00"));
        flightTicket.setDepartureTime(LocalTime.parse("15:00"));
        flightTicket.setDestinationCity("New York");
        flightTicket.setOriginCity("Sydney");
        flightTicket.setHasLuggage(false);
        flightTicket.setPassengerAge(30);
        flightTicket.setPassengerName("John Doe");
        flightTicket.setPrice(150.00);

        return flightTicket;
    }
}

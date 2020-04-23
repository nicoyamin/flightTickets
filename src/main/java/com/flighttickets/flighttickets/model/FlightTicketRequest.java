package com.flighttickets.flighttickets.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FlightTicketRequest {

    private String departureDate;
    private String arrivalDate;
    private String originCity;
    private String destinationCity;
    private String passengerName;
    private int passengerAge;
    private Boolean hasLuggage;
    private Double price;
    private String departureTime;
    private String arrivalTime;

}

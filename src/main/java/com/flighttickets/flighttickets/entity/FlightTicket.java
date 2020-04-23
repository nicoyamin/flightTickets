package com.flighttickets.flighttickets.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class FlightTicket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @NotNull(message = "Can't be empty nor null")
    @FutureOrPresent(message = "must be future or present date")
    @Column(name = "DEPARTURE_DATE")
    private LocalDate departureDate;

    @NotNull(message = "Can't be empty nor null")
    @FutureOrPresent(message = "must be future or present date")
    @Column(name = "ARRIVAL_DATE")
    private LocalDate arrivalDate;

    @NotEmpty(message = "Can't be empty nor null")
    @Column(name = "ORIGIN_CITY")
    private String originCity;

    @NotEmpty(message = "Can't be empty nor null")
    @Column(name = "DESTINATION_CITY")
    private String destinationCity;

    @NotEmpty(message = "Can't be empty nor null")
    @Column(name = "PASSENGER_NAME")
    private String passengerName;

    @NotNull(message = "Can't be empty nor null")
    @Positive(message = "Must be a positive number")
    @Column(name = "PASSENGER_AGE")
    private int passengerAge;

    @NotNull(message = "Can't be empty nor null")
    @Type(type="yes_no")
    @Column(name="HAS_LUGGAGE")
    private Boolean hasLuggage;

    @NotNull(message = "Can't be empty nor null")
    @PositiveOrZero(message = "Must be a positive number or zero")
    @Column(name="PRICE", columnDefinition="Decimal(10,2) default '0.00'")
    private Double price;

    @NotNull(message = "Can't be empty nor null")
    @Column(name = "DEPARTURE_TIME")
    private LocalTime departureTime;

    @NotNull(message = "Can't be empty nor null")
    @Column(name = "ARRIVAL_TIME")
    private LocalTime arrivalTime;

}

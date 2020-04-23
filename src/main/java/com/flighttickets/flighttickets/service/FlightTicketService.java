package com.flighttickets.flighttickets.service;

import com.flighttickets.flighttickets.entity.FlightTicket;
import com.flighttickets.flighttickets.model.FlightTicketRequest;
import com.google.common.base.Strings;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

@Service
public class FlightTicketService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public FlightTicketService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Long createNewTicket(FlightTicketRequest request) {
        FlightTicket newTicket = createFlightTicket(request);
        Session session = entityManager.unwrap(Session.class);

        return (Long) session.save(newTicket);

    }

    public FlightTicket getTicketById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        FlightTicket retrievedTicket = session.get(FlightTicket.class, id);
        return retrievedTicket;
    }

    private FlightTicket createFlightTicket(FlightTicketRequest request) {
        FlightTicket newTicket = new FlightTicket();


        newTicket.setDepartureDate(Strings.isNullOrEmpty(request.getDepartureDate()) ?
                null : LocalDate.parse(request.getDepartureDate()));
        newTicket.setArrivalDate(Strings.isNullOrEmpty(request.getArrivalDate()) ?
                null : LocalDate.parse(request.getArrivalDate()));
        newTicket.setDepartureTime(Strings.isNullOrEmpty(request.getDepartureTime()) ?
                null : LocalTime.parse(request.getDepartureTime()));
        newTicket.setArrivalTime(Strings.isNullOrEmpty(request.getArrivalTime()) ?
                null : LocalTime.parse(request.getArrivalTime()));
        newTicket.setOriginCity(request.getOriginCity());
        newTicket.setDestinationCity(request.getDestinationCity());
        newTicket.setPassengerName(request.getPassengerName());
        newTicket.setPassengerAge(request.getPassengerAge());
        newTicket.setHasLuggage(request.getHasLuggage());
        newTicket.setPrice(request.getPrice());

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<FlightTicket>> constraintViolations = validator.validate(newTicket);

        if (constraintViolations.size() > 0) {
            StringJoiner joiner = new StringJoiner(", ","Ticket is not valid: ", "");
            Set<String> violationMessages = new HashSet<String>();

            for (ConstraintViolation<FlightTicket> constraintViolation : constraintViolations) {
                joiner.add(constraintViolation.getPropertyPath() +" "+constraintViolation.getMessage());
            }

            throw new RuntimeException(joiner.toString());
        }

        LocalDateTime departureDateTime = LocalDateTime.of(newTicket.getDepartureDate(), newTicket.getDepartureTime());
        LocalDateTime arrivalDateTime = LocalDateTime.of(newTicket.getArrivalDate(), newTicket.getArrivalTime());

        if(arrivalDateTime.isBefore(departureDateTime)) {
            throw new RuntimeException("Ticket is not valid: Arrival can't be before Departure");
        }

        return newTicket;
    }

}

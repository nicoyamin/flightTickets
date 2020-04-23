package com.flighttickets.flighttickets.controller;

import com.flighttickets.flighttickets.entity.FlightTicket;
import com.flighttickets.flighttickets.model.FlightTicketRequest;
import com.flighttickets.flighttickets.service.FlightTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class FlightTicketController implements FlightTicketApi{

    FlightTicketService flightTicketService;

    @Autowired
    public FlightTicketController(FlightTicketService flightTicketService) {
        this.flightTicketService = flightTicketService;
    }

    @Override
    public ResponseEntity<Long> createFlightTicket(FlightTicketRequest request) {

        Long createdTicektId = flightTicketService.createNewTicket(request);

        return ResponseEntity.ok(createdTicektId);
    }

    @Override
    public ResponseEntity<Object> getTicketById(@NotNull @Valid Long flightTicketId) {

        FlightTicket retrievedTicket = flightTicketService.getTicketById(flightTicketId);

        if(retrievedTicket == null) return ResponseEntity.ok("No ticket found with Id "+flightTicketId);

        return ResponseEntity.ok(retrievedTicket);
    }
}

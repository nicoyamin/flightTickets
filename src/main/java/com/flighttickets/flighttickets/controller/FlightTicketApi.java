package com.flighttickets.flighttickets.controller;

import com.flighttickets.flighttickets.entity.FlightTicket;
import com.flighttickets.flighttickets.model.FlightTicketRequest;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Api(value="flightTickets")
public interface FlightTicketApi {

    @ApiOperation(value = "Create a new flight ticket", nickname = "createTicket", response = Long.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "New ticket successfully created"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 204, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/flightTickets/createTicket",
    produces={"application/JSON"},
    method= RequestMethod.POST)
    ResponseEntity<Long> createFlightTicket(@ApiParam(value="Data for new Ticket")
                                      @Valid@RequestBody(required=true) FlightTicketRequest request);

    @ApiOperation(value = "Retrieve flight ticket by id", nickname = "getTicketById", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieved flight ticket with provided Id"),
            @ApiResponse(code = 204, message = "No Ticket found for Provided Id"),
            @ApiResponse(code = 204, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/flightTickets/getTicketById",
            produces={"application/JSON"},
            method= RequestMethod.GET)
    ResponseEntity<Object> getTicketById(@NotNull
                                               @ApiParam(value = "Flight Ticket Id", required = true)
                                               @Valid@RequestParam(value = "flightTicketId", required = true) Long flightTicketId);

}

package com.flighttickets.flighttickets.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestResponseEntityExceptionHandlerTest {

    RestResponseEntityExceptionHandler exceptionHandler = new RestResponseEntityExceptionHandler();

    @Mock
    WebRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldTestExceptionHandler() {
        RuntimeException exception = new RuntimeException("test exception");

        ResponseEntity<Object> returnedResponse = exceptionHandler.handleConflict(exception, request);

        assertEquals(returnedResponse.getStatusCode(), HttpStatus.CONFLICT);
        assertEquals(returnedResponse.getBody(), "test exception");
    }



}
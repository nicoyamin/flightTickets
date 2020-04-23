# flightTickets
DataArt Challenge

Some Useful notes to keep in mind:

**DATABASE**

* The app is using the H2 in-memory database for easier setup. 
   If you are running in localhost, you can access the h2 console from  
  _http://localhost:8080/h2-console/_
 * User:sa
 * Password:test
 * The table is automatically created by following the corresponding entity
 * There's a data.sql file which prepopulates teh table with some mocked data,
 so you have something to fetch at first
 
 **ENDPOINT ACCESS AND DOCUMENTATION**
 
 * I've implemented Swagger in order to document endpoint information, and also to 
 provide a quick way to test them. 
 * You can access Swagger from _http://localhost:8080/swagger-ui.html#_
 *Find the required endpoints under flight-ticket-controller
 
 **UNIT TESTING AND VALDATION**
 
 Unit Testing was performed under jUnit5 and Mockito framework for mocking dependencies.
 According to JaCoCo coverage runner, coverage should be around 100%.
 
 **GENERAL SOFTWARE PRACTICES**
 
 * This being a quick prototype, I decided to take some "shortcuts" in order to speed up
 development time.
 *  I went for a single entity and table (FLIGHT_TICKET), since the amount of information was manageable that
 way. Under normal circumstances, Passenger, Cities and Schedule (Departure and Arrival DateTime)
 would have been separate entities/tables with the corresponding relationships.
 * I didn't model a proper FlightTicketResponse, since FlightTicket entity
 didn't need to be processed in order to be retrieved.
 * For brevity's sake, I didn't code a repo nor an service interface for Flight Ticket controller
 * I used Lombok to create necessary getters/setters and set the config file to
 ignore those methods when calculating coverage
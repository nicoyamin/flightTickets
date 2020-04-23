--Inserting some mocked data into DB to have something to query

INSERT INTO FLIGHT_TICKET
(ID, ARRIVAL_DATE, ARRIVAL_TIME, DEPARTURE_DATE, DEPARTURE_TIME, DESTINATION_CITY, HAS_LUGGAGE, ORIGIN_CITY, PASSENGER_AGE, PASSENGER_NAME, PRICE)
VALUES
  (1,'2020-10-10', '15:06', '2020-10-09', '17:55', 'El Cairo', TRUE, 'Sydney', 34, 'Eva Smith', 450.56),
  (2,'2019-05-01', '22:00', '2019-05-01', '03:25', 'Chicago', FALSE, 'London', 65, 'John Die', 270.25),
  (3,'2021-01-12', '03:00', '2021-01-01', '23:50', 'Lima', FALSE, 'Madrid', 22, 'Lebron James', 452.00);
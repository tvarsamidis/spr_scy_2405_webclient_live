package gr.codehub.flights.service;

import gr.codehub.flights.domain.Flight;

import java.util.List;

public interface WebClientService {
    String ping();
    String readStringByNameFromServer(String name);
    Flight readFlightByNameFromServer(String name);
    Flight deleteByNameFromServer(String name);
    Flight create(Flight flight);
    List<Flight> read();
}

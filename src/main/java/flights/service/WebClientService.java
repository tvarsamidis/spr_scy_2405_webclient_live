package flights.service;

import flights.domain.Flight;

import java.util.List;

public interface WebClientService {
    String ping();
    String readStringByNameFromServer(String flightName);
    Flight readFlightByNameFromServer(String flightName);
    Flight deleteByNameFromServer(String flightName);
    Flight create(Flight flight);
    List<Flight> read();
}

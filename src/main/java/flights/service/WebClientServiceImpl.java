package flights.service;

import flights.domain.Flight;

import java.util.List;

public class WebClientServiceImpl implements WebClientService {


    @Override
    public String ping() {
        throw new RuntimeException("ping() is not ready yet");
    }

    @Override
    public String readStringByNameFromServer(String name) {
        throw new RuntimeException("ping() is not ready yet");
    }

    @Override
    public Flight readFlightByNameFromServer(String name) {
        throw new RuntimeException("readFlightByNameFromServer() is not ready yet");
    }

    @Override
    public Flight deleteByNameFromServer(String name) {
        throw new RuntimeException("deleteByNameFromServer() is not ready yet");
    }

    @Override
    public Flight create(Flight flight) {
        throw new RuntimeException("create() is not ready yet");
    }

    @Override
    public List<Flight> read() {
        throw new RuntimeException("read() is not ready yet");
    }
}

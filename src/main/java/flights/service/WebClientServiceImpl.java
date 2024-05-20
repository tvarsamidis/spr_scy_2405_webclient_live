package flights.service;

import flights.domain.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class WebClientServiceImpl implements WebClientService {

    private final WebClient webClient;

    public WebClientServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public String ping() {
        return  webClient.get()
                .uri("/flight/ping")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public String readStringByNameFromServer(String name) {
        throw new RuntimeException("readStringByNameFromServer() is not ready yet");
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

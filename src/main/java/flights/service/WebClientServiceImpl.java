package flights.service;

import flights.domain.Flight;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
    public String readStringByNameFromServer(String flightName) {
        Mono<String> flightMono = webClient.get()
                .uri("/flight/" + flightName)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                .retrieve()
                .bodyToMono(String.class);
        String result = flightMono.block();
        return result;
    }

    @Override
    public Flight readFlightByNameFromServer(String flightName) {
        Mono<Flight> flightMono = webClient.get()
                .uri("/flight/" + flightName)
                .retrieve()
                .bodyToMono(Flight.class);
        Flight flight = flightMono.block();
        return flight;
    }

    @Override
    public Flight deleteByNameFromServer(String flightName) {
        return webClient.delete()
                .uri("/flight/" + flightName)
                .retrieve()
                .bodyToMono(Flight.class)
                .block();
    }

    @Override
    public Flight create(Flight flight) {
        Mono<Flight> flightMono = webClient.post()
                .uri("/flight/create")
                .bodyValue(flight)
                .retrieve()
                .bodyToMono(Flight.class);
        Flight savedFlight = flightMono.block();
        return savedFlight;
    }

    @Override
    public List<Flight> read() {
        throw new RuntimeException("read() is not ready yet");
    }
}

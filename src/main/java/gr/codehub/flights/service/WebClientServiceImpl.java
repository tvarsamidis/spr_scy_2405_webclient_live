package gr.codehub.flights.service;

import gr.codehub.flights.domain.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class WebClientServiceImpl implements WebClientService {

    private final WebClient webClient;

    @Autowired
    public WebClientServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public String ping() {
        return webClient.get()
                .uri("/flight/ping")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public String readStringByNameFromServer(String name) {
        Mono<String> flightMono = webClient.get()
                .uri("/flight/" + name)
                // header is the same as the default, so it is not really needed
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class);
        return flightMono.block();
    }

    @Override
    public Flight readFlightByNameFromServer(String name) {
        Mono<Flight> flightMono = webClient.get()
                .uri("/flight/" + name)
                .retrieve()
                .bodyToMono(Flight.class);
        return flightMono.block();
    }

    @Override
    public Flight deleteByNameFromServer(String name) {
        Mono<Flight> flightMono = webClient.delete()
                .uri("/flight/" + name)
                .retrieve()
                .bodyToMono(Flight.class);
        return flightMono.block();
    }

    @Override
    public Flight create(Flight flight) {
        Mono<Flight> flightMono = webClient.post()
                .uri("/flight/create")
                .bodyValue(flight)
                .retrieve()
                .bodyToMono(Flight.class);
        return flightMono.block();
    }

    @Override
    public List<Flight> read() {
        Mono<List<Flight>> flightMono = webClient.get()
                .uri("/flight")
                .retrieve()
                .bodyToFlux(Flight.class)
                .collectList();
        List<Flight> flightList = flightMono.block();
        return flightList;
    }
}

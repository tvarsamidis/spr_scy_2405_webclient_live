package gr.codehub.flights.controller;

import gr.codehub.flights.config.WebConfig;
import gr.codehub.flights.domain.Flight;
import gr.codehub.flights.service.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/webclient")
public class WebClientController {

    @Autowired
    private WebClientService webClientService;

    @GetMapping("/ping")
    public String s01_ping() {
        return webClientService.ping();
    }

    @GetMapping("/info/{flightName}")
    public String s02_getFlightAsText(@PathVariable String flightName) {
        String result = webClientService.readStringByNameFromServer(flightName);
        result = result.replaceAll(":", " is ");
        result = result.replaceAll(",", "<br/>and ");
        result = result.replaceAll("\\{", "START:<br/>");
        result = result.replaceAll("\\}", "</br>END!");
        return result;
    }

    @GetMapping("/{flightName}")
    public Flight s03_getFlight(@PathVariable String flightName) {
        Flight result = webClientService.readFlightByNameFromServer(flightName);
        return result;
    }

    @GetMapping("/delete/{flightName}") // we are getting a GET and changing it to a DELETE
    public Flight s04_deleteFlight(@PathVariable String flightName) {
        Flight result = webClientService.deleteByNameFromServer(flightName);
        return result;
    }

    @GetMapping("/create/{name}/{from}/{to}/{price}") // we are getting a GET and changing it to a POST
    public Flight s05_createFlight(@PathVariable String name, @PathVariable String from, @PathVariable String to, @PathVariable double price) {
        Flight flight = new Flight(name, from, to, price);
        Flight result = webClientService.create(flight);
        return result;
    }


    @GetMapping("/read")
    public List<Flight> s06_read() {
        List<Flight> flights = webClientService.read();
        s07_runFluxDemo();
        s08_runFluxDemo(flights);
        return flights;
    }

    private void s07_runFluxDemo() {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);
        flux.subscribe(System.out::println);
    }

    public void s08_runFluxDemo(List<Flight> flights) {
        Flux<Flight> flux = Flux.empty();
        for (Flight f : flights) {
            Flux<Flight> singleFlux = Flux.just(f);
            flux = flux.concatWith(singleFlux);
        }
        System.out.println("********* Flux subscribe 1 *********");
        flux.subscribe(System.out::println);
        if (flights.size() % 2 == 1) {
            System.out.println("********* Flux subscribe for odd no of items *********");
            flux.subscribe(
                    flight -> System.out.println("Flight item: " + flight),
                    error -> System.err.println("Error: " + error),
                    () -> System.out.println("Completed")
            );
        }
    }

}

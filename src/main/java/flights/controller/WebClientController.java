package flights.controller;

import flights.domain.Flight;
import flights.service.WebClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webclient")
@AllArgsConstructor
public class WebClientController {

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
        Flight flight = webClientService.readFlightByNameFromServer(flightName);
        return flight;
    }

    @GetMapping("/delete/{flightName}")
    public Flight s04_deleteFlight(@PathVariable String flightName) {
        return webClientService.deleteByNameFromServer(flightName);
    }

    @GetMapping("/create/{flightName}/{from}/{to}/{price}")
    public Flight s05_createFlight(@PathVariable String flightName,
                                   @PathVariable String from,
                                   @PathVariable String to,
                                   @PathVariable double price) {
        Flight flight = new Flight(-249862941, flightName, from, to, price);
        Flight resultFlight = webClientService.create(flight);
        return resultFlight;
    }
}

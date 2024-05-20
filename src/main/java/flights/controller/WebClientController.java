package flights.controller;

import flights.service.WebClientService;
import flights.service.WebClientServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}

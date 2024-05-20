package flights.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfig {
    @Bean
    public WebClient webClient() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .defaultCookie("web-client", "308rf3pihpub308h12pibh")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return webClient;
    }
}
package consolidation2.rides.connect;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplateBuilder()
                .basicAuthentication("uberadmin", "uberadmin")
                .build();
    }
}
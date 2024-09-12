package consolidation2.rides.connect;

import consolidation2.drivers.data.Driver;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class RidesClient {
    private final RestTemplate restTemplate;

    public RidesClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Driver[] getAllDriversFromDriversApp() {
        return restTemplate.getForObject("http://localhost:9100/drivers", Driver[].class);
    }
}
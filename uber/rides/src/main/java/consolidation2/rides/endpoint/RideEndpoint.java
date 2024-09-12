package consolidation2.rides.endpoint;

import consolidation2.rides.data.Ride;
import consolidation2.rides.logic.RideService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drivers")
@EnableMethodSecurity(securedEnabled = true)
public class RideEndpoint {
    private final RideService rideService;

    public RideEndpoint(RideService rideService) {
        this.rideService = rideService;
    }

    @PostMapping("/{id}/rides")
    @Secured("ROLE_APP")
    public Ride postRide(@RequestBody Ride ride, @PathVariable Long id) {
        return rideService.addNewRide(ride, id);
    }
}
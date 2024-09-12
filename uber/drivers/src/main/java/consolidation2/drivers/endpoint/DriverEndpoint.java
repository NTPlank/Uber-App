package consolidation2.drivers.endpoint;

import consolidation2.drivers.data.Driver;
import consolidation2.drivers.logic.DriverService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverEndpoint {

    private final DriverService driverService;

    public DriverEndpoint(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping()
    public Driver addNewDriver(@RequestBody Driver driver) {
        return driverService.addNewDriver(driver);
    }

    @GetMapping()
    public List<Driver> getDrivers() {
        return driverService.getAllDrivers();
    }

    @GetMapping("/{id}")
    @Secured("ROLE_DRIVER")
    public Driver getDriver(@PathVariable String id) {
        return driverService.findDriverById(id).orElse(null);
    }

    @PutMapping("/{id}/available")
    @Secured("ROLE_DRIVER")
    public void setDriverToAvailable(@PathVariable String id) {
        driverService.setDriverToAvailable(id);
    }

    @PutMapping("/{id}/unavailable")
    @Secured("ROLE_DRIVER")
    public void setDriverToUnavailable(@PathVariable String id) {
        driverService.setDriverToUnavailable(id);
    }
}
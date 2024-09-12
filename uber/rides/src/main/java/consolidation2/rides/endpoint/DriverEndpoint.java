package consolidation2.rides.endpoint;

import consolidation2.rides.connect.RidesClient;
import consolidation2.rides.data.Driver;
import consolidation2.rides.logic.ExOneDriverToExTwoDriverConverter;
import consolidation2.rides.logic.DriverService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/drivers")
@EnableMethodSecurity(securedEnabled = true)
public class DriverEndpoint {
    private final DriverService driverService;
    private final RidesClient ridesClient;
    private final ExOneDriverToExTwoDriverConverter converter;

    public DriverEndpoint(DriverService driverService, RidesClient ridesClient, ExOneDriverToExTwoDriverConverter converter) {
        this.driverService = driverService;
        this.ridesClient = ridesClient;
        this.converter = converter;
    }

    @PostMapping()
    @Secured("ROLE_ADMIN")
    Driver addDriver(@RequestBody Driver driver) {
        return driverService.addNewDriver(driver);
    }

    @GetMapping()
    @Secured("ROLE_ADMIN")
    List<Driver> getDrivers() {
        return driverService.getAllDrivers();
    }

    @DeleteMapping("/deleteAll")
    @Secured("ROLE_ADMIN")
    void deleteAllDrivers() {
        driverService.deleteAllDrivers();
    }

    @GetMapping("/getFromDriversApp")
    @Secured("ROLE_ADMIN")
    List<Driver> getAllDriversFromDriversApp() {
        consolidation2.drivers.data.Driver[] driver = ridesClient.getAllDriversFromDriversApp();

        List<Driver> convertedDrivers = new ArrayList<>();

        for (consolidation2.drivers.data.Driver d : driver) {
            Driver convertedDriver = converter.convertExOneDriversToExTwoDrivers(d);
            convertedDrivers.add(convertedDriver);
        }

        return convertedDrivers;
    }
}
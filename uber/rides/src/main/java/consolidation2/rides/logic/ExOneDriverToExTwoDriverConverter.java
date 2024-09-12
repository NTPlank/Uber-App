package consolidation2.rides.logic;

import consolidation2.rides.data.Driver;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExOneDriverToExTwoDriverConverter {

    private final DriverService driverService;

    public ExOneDriverToExTwoDriverConverter(DriverService driverService) {
        this.driverService = driverService;
    }

    public Driver convertExOneDriversToExTwoDrivers(consolidation2.drivers.data.Driver driver) {
        List<Driver> drivers = driverService.getAllDrivers();
        for (Driver existingDriver : drivers) {
            if (existingDriver.getUsername().equals(driver.getUsername())) {
                return existingDriver;
            }
        }

        return driverService.addNewDriver(new Driver(driver.getUsername(), driver.getCarType()));
    }
}
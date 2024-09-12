package consolidation2.rides.logic;

import consolidation2.rides.data.Driver;
import consolidation2.rides.repos.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver addNewDriver(Driver driver) {
        if (driverRepository.existsByUsername(driver.getUsername())) {
            throw new RuntimeException("Driver with username '" + driver.getUsername() + "' already exists.");
        }
        driver.setRides(List.of());
        return driverRepository.save(driver);
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public void deleteAllDrivers(){
        driverRepository.deleteAll();
    }
}
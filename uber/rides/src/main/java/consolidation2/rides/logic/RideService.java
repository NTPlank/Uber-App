package consolidation2.rides.logic;

import consolidation2.rides.data.Driver;
import consolidation2.rides.data.Ride;
import consolidation2.rides.repos.DriverRepository;
import consolidation2.rides.repos.RideRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RideService {

    private final RideRepository rideRepository;
    private final DriverRepository driverRepository;

    public RideService(RideRepository rideRepository, DriverRepository driverRepository) {
        this.rideRepository = rideRepository;
        this.driverRepository = driverRepository;
    }

    public Ride addNewRide(Ride ride, Long driverId) {
        Optional<Driver> oDriver = driverRepository.findById(driverId);

        if (oDriver.isPresent()) {
            Driver driver = oDriver.get();
            driver.getRides().add(ride);
            Ride savedRide = rideRepository.save(ride);
            driverRepository.save(driver);
            return savedRide;
        } else {
            throw new EntityNotFoundException("Driver not found with id: " + driverId);
        }
    }
}
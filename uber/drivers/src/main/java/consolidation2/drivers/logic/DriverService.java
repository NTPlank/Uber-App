package consolidation2.drivers.logic;

import consolidation2.drivers.data.Driver;
import consolidation2.drivers.repo.DriverRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private final PasswordEncoder passwordEncoder;
    private final String driverAuthorities;

    public DriverService(DriverRepository driverRepository,
                         PasswordEncoder passwordEncoder,
                         @Value("${uber_driver.driver.authorities}") String driverAuthorities) {
        this.driverRepository = driverRepository;
        this.passwordEncoder = passwordEncoder;
        this.driverAuthorities = driverAuthorities;
    }

    public Driver addNewDriver(Driver driver) {
        if (driverRepository.existsByUsername(driver.getUsername())) {
            return driverRepository.findOneByUsername(driver.getUsername()).get();
        }
        driver.setAuthorities(Set.of(driverAuthorities));
        String password = driver.getPassword();
        String encryptedPassword = passwordEncoder.encode(password);
        driver.setPassword(encryptedPassword);
        driver.setAvailable(false);
        return driverRepository.save(driver);
    }

    public Optional<Driver> findDriverById(String id) {
        return driverRepository.findById(id);
    }

    public void setDriverToAvailable(String id) {
        Optional<Driver> oDriver = findDriverById(id);

        if(oDriver.isEmpty()){
            return;
        }

        oDriver.get().setAvailable(true);
        driverRepository.save(oDriver.get());
    }

    public void setDriverToUnavailable(String id) {
        Optional<Driver> oDriver = findDriverById(id);

        if(oDriver.isEmpty()){
            return;
        }

        oDriver.get().setAvailable(false);
        driverRepository.save(oDriver.get());
    }

    public List<Driver> getAllDrivers(){
        return driverRepository.findAll();
    }
}
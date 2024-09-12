package consolidation2.drivers.logic;

import consolidation2.drivers.data.Driver;
import consolidation2.drivers.repo.DriverRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class DriverServiceTest {

    @Autowired
    private DriverService driverService;

    @MockBean
    private DriverRepository driverRepository;

    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    SecurityFilterChain securityFilterChain;

    @Value("${uber_driver.driver.authorities}")
    String driverAuthorities;

    private final String driverId = "driverId";
    private final Driver driver1 = new Driver("name1", "pw1", "car1");
    private final Driver driver2 = new Driver("name2", "pw2", "car2");

    List<Driver> drivers = List.of(driver1, driver2);

    @Test
    void addNewDriver() {
        Mockito.when(driverRepository.save(Mockito.any(Driver.class))).thenReturn(driver1);
        Driver result = driverService.addNewDriver(driver1);
        assertEquals(driver1, result);
        Mockito.verify(driverRepository).save(Mockito.any(Driver.class));
    }

    @Test
    void findDriverByIdPresent() {
        Mockito.when(driverRepository.findById(driverId)).thenReturn(Optional.of(driver2));
        Optional<Driver> result = driverService.findDriverById(driverId);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(driver2, result.get());
        Mockito.verify(driverRepository).findById(driverId);
    }

    @Test
    void setDriverToAvailable() {
        driver1.setAvailable(false);
        Assertions.assertEquals(false, driver1.getAvailable());

        Mockito.when(driverRepository.findById(driverId)).thenReturn(Optional.of(driver1));
        driverService.setDriverToAvailable(driverId);
        Mockito.when(driverRepository.save(Mockito.any(Driver.class))).thenReturn(driver1);

        Assertions.assertEquals(true, driver1.getAvailable());
        Mockito.verify(driverRepository).findById(driverId);
        Mockito.verify(driverRepository).save(Mockito.any(Driver.class));
    }

    @Test
    void setDriverToUnavailable() {
        driver1.setAvailable(true);
        Assertions.assertEquals(true, driver1.getAvailable());

        Mockito.when(driverRepository.findById(driverId)).thenReturn(Optional.of(driver1));
        driverService.setDriverToUnavailable(driverId);
        Mockito.when(driverRepository.save(Mockito.any(Driver.class))).thenReturn(driver1);

        Assertions.assertEquals(false, driver1.getAvailable());
        Mockito.verify(driverRepository).findById(driverId);
        Mockito.verify(driverRepository).save(Mockito.any(Driver.class));
    }

    @Test
    void getAllDrivers() {
        Mockito.when(driverRepository.findAll()).thenReturn(drivers);
        List<Driver> result = driverService.getAllDrivers();
        Assertions.assertEquals(result, drivers);
        Mockito.verify(driverRepository).findAll();
    }
}
package consolidation2.rides.repos;

import consolidation2.rides.data.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository <Driver, Long> {
    boolean existsByUsername(String username);
}
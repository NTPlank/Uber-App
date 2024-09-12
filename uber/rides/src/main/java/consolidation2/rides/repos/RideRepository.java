package consolidation2.rides.repos;

import consolidation2.rides.data.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository <Ride, Long> {
}
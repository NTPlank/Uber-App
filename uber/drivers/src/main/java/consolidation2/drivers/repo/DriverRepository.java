package consolidation2.drivers.repo;

import consolidation2.drivers.data.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DriverRepository extends MongoRepository<Driver, String> {
    Optional<Driver> findOneByUsername(String username);
    boolean existsByUsername(String username);
}
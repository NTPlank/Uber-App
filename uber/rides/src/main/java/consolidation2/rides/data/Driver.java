package consolidation2.rides.data;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Driver {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true, nullable = false)
    private String username;

    private String carType;

    @OneToMany
    private List<Ride> rides;

    public Driver(String username, String carType) {
        this.username = username;
        this.carType = carType;
    }

    public Driver() {
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getCarType() {
        return carType;
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver driver)) return false;
        return Objects.equals(getUsername(), driver.getUsername()) && Objects.equals(getCarType(), driver.getCarType()) && Objects.equals(getRides(), driver.getRides());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getCarType(), getRides());
    }
}
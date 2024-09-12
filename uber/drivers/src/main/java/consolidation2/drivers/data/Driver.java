package consolidation2.drivers.data;

import java.util.Objects;
import java.util.Set;

public class Driver {

    private String id;
    private String username;
    private String password;
    private String carType;
    private Set<String> authorities;
    private Boolean available;

    public Driver(String username, String password, String carType) {
        this.username = username;
        this.password = password;
        this.carType = carType;
    }

    public Driver(String username, String password, String carType, Set<String> authorities, Boolean available) {
        this.username = username;
        this.password = password;
        this.carType = carType;
        this.authorities = authorities;
        this.available = available;
    }

    public Driver() {
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCarType() {
        return carType;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver driver)) return false;
        return Objects.equals(getUsername(), driver.getUsername()) && Objects.equals(getPassword(), driver.getPassword()) && Objects.equals(getCarType(), driver.getCarType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getCarType());
    }
}
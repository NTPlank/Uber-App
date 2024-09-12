package consolidation2.rides.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Ride {

    @Id
    @GeneratedValue
    private Long id;

    private String customerUsername;
    private double distanceTraveled;
    private double price;

    public Ride(String customerUsername, double distanceTraveled, double price) {
        this.customerUsername = customerUsername;
        this.distanceTraveled = distanceTraveled;
        this.price = price;
    }

    public Ride() {
    }

    public Long getId() {
        return id;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    public double getPrice() {
        return price;
    }

    public void setDistanceTraveled(double distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
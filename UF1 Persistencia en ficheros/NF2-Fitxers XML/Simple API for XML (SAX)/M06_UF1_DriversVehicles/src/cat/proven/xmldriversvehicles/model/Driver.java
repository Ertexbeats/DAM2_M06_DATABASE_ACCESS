package cat.proven.xmldriversvehicles.model;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    private long id;
    private String firstname;
    private String lastname;
    private List<Vehicle> vehicles;

    public Driver(long id, String firstname, String lastname, List<Vehicle> vehicles) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.vehicles = vehicles;
    }

    public Driver(long id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.vehicles = new ArrayList<>();
    }

    public Driver(long id) {
        this.id = id;
        this.vehicles = new ArrayList<>();
    }

    public Driver() {
        this.vehicles = new ArrayList<>();
    }

    public Driver(Driver other) {
        this.id = other.id;
        this.firstname = other.firstname;
        this.lastname = other.lastname;
        this.vehicles = other.vehicles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Driver other = (Driver) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Driver{");
        sb.append("id=").append(id);
        sb.append(", firstname=").append(firstname);
        sb.append(", lastname=").append(lastname);
        sb.append(", vehicles=").append(vehicles);
        sb.append('}');
        return sb.toString();
    }
    
}

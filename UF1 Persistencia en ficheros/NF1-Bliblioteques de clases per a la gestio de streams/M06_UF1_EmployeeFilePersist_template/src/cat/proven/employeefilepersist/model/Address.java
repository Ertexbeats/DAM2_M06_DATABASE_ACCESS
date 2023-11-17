package cat.proven.employeefilepersist.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author ProvenSoft
 */
public class Address implements Serializable {

    private String streetName;
    private int streetNumber;
    private String zipCode;

    public Address(String streetName, int streetNumber, String zipCode) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
    }

    public Address() {
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.streetName);
        hash = 29 * hash + this.streetNumber;
        hash = 29 * hash + Objects.hashCode(this.zipCode);
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
        final Address other = (Address) obj;
        if (this.streetNumber != other.streetNumber) {
            return false;
        }
        if (!Objects.equals(this.streetName, other.streetName)) {
            return false;
        }
        if (!Objects.equals(this.zipCode, other.zipCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Address{streetName=").append(streetName);
        sb.append(", streetNumber=").append(streetNumber);
        sb.append(", zipCode=").append(zipCode);
        sb.append('}');
        return sb.toString();
    }

}

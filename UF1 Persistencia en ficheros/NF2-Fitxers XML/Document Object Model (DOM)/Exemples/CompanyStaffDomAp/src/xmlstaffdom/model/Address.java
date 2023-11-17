package xmlstaffdom.model;

import java.util.Objects;

/**
 * ADT for an address
 * @author ProvenSoft
 */

public class Address {
    private String street;
    private int number;

    public Address() {
    }

    public Address(String street, int number) {
        this.street = street;
        this.number = number;
    }
    
    public Address(Address other) {
        this.street = other.street;
        this.number = other.number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.street);
        hash = 89 * hash + this.number;
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
        if (this.number != other.number) {
            return false;
        }
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Address{street=").append(street);
        sb.append(", number=").append(number);
        sb.append('}');
        return sb.toString();
    }
    
    
    
    
}

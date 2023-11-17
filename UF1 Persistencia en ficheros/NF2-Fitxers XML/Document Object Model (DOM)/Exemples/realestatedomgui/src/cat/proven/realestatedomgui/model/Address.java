package cat.proven.realestatedomgui.model;

import java.util.Objects;

/**
 *
 * @author ProvenSoft
 */
public class Address {
    private String street;
    private int number;
    private int floor;
    private int door;

    public Address(String street, int number, int floor, int door) {
        this.street = street;
        this.number = number;
        this.floor = floor;
        this.door = door;
    }

    public Address() {
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

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getDoor() {
        return door;
    }

    public void setDoor(int door) {
        this.door = door;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        if (this.floor != other.floor) {
            return false;
        }
        if (this.door != other.door) {
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
        sb.append("Address{");
        sb.append("street=").append(street);
        sb.append(", number=").append(number);
        sb.append(", floor=").append(floor);
        sb.append(", door=").append(door);
        sb.append('}');
        return sb.toString();
    }


    
}

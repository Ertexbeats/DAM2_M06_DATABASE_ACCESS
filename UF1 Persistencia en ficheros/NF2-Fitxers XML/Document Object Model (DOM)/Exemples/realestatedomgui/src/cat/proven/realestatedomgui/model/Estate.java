package cat.proven.realestatedomgui.model;

import java.util.Objects;

/**
 *
 * @author ProvenSoft
 */
public class Estate {
    private String id;
    private String type;
    private double surface;
    private Address address;
    private double price;

    public Estate(String id, String type, double surface, Address address, double price) {
        this.id = id;
        this.type = type;
        this.surface = surface;
        this.address = address;
        this.price = price;
    }

    public Estate() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Estate other = (Estate) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Estate{");
        sb.append("id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", surface=").append(surface);
        sb.append(", address=").append(address);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
    
}

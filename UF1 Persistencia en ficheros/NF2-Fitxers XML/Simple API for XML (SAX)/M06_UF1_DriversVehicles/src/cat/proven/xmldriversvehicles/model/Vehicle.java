package cat.proven.xmldriversvehicles.model;

public class Vehicle {
    private long id;
    private String name;

    public Vehicle(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Vehicle(long id) {
        this.id = id;
    }

    public Vehicle() {
    }

    public Vehicle(Vehicle other) {
        this.id = other.id;
        this.name = other.name;
    }    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Vehicle other = (Vehicle) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vehicle{");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append('}');
        return sb.toString();
    }
}

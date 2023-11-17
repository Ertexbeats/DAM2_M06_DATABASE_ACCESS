
public class Account {
    private int id;
    private String name;
    private double amt;
    private String type;

    public Account() {
    }

    public Account(int id, String name, double amt, String type) {
        this.id = id;
        this.name = name;
        this.amt = amt;
        this.type = type;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Account Details - ");
        sb.append("Name:").append(getName());
        sb.append(", ");
        sb.append("Type:").append(getType());
        sb.append(", ");
        sb.append("Id:").append(getId());
        sb.append(", ");
        sb.append("Age:").append(getAmt());
        sb.append(".");
        return sb.toString();
    }
}

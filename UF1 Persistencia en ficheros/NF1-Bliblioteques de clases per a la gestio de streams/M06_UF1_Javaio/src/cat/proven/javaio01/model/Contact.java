package cat.proven.javaio01.model;

/**
 * Contact.java
 *
 * @author ProvenSoft
 */
import java.io.Serializable;

public class Contact implements Serializable {

    private String name;
    private String phone;
    private int age;

    public Contact() {
    }

    public Contact(String name, String phone, int age) {
        this.name = name;
        this.phone = phone;
        this.age = age;
    }

    //accessors
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return ("{Contact [name=" + getName() + "][phone=" + getPhone() + "][age=" + getAge() + "]}");
    }
}

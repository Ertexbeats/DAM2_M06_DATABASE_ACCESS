package cat.proven.employeefilepersist.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author ProvenSoft
 */
public class Employee implements Serializable {

    private String phone;
    private String name;
    private int age;
    private boolean senior;
    private double salary;
    private Address address;

    public Employee(String phone, String name, int age, boolean senior, double salary, Address address) {
        this.phone = phone;
        this.name = name;
        this.age = age;
        this.senior = senior;
        this.salary = salary;
        this.address = address;
    }

    public Employee(String phone, String name, int age, boolean senior, double salary) {
        this.phone = phone;
        this.name = name;
        this.age = age;
        this.senior = senior;
        this.salary = salary;
    }

    public Employee() {
    }

    public Employee(String phone) {
        this.phone = phone;
    }

    public Employee(Employee other) {
        this.phone = other.phone;
        this.name = other.name;
        this.age = other.age;
        this.senior = other.senior;
        this.salary = other.salary;
        this.address = other.address;

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSenior() {
        return senior;
    }

    public void setSenior(boolean senior) {
        this.senior = senior;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.phone);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean b = false;
        if (obj == null) {
            b = false;
        } else {
            if (this == obj) {
                b = true; //the same object.
            } else {
                if (obj instanceof Employee) { //the same class.
                    Employee other = (Employee) obj; //convert to Person.
                    if (this.phone.equals(other.phone)) {
                        b = true;
                    } else {
                        b = false;
                    }
                } else {
                    b = false;
                }
            }
        }
        return b;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Employee{phone=").append(phone);
        sb.append(", name=").append(name);
        sb.append(", age=").append(age);
        sb.append(", senior=").append(senior);
        sb.append(", salary=").append(salary);
        sb.append(", Address=").append(address/*.toString()*/);
        sb.append('}');
        return sb.toString();
    }

}

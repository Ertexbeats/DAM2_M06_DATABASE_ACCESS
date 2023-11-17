package xmlstaffdom.model;

import java.util.Objects;

/**
 * ADT for an employee
 * @author alumne
 */

public class Employee {
    
    private String nif;
    private String firstname;
    private String lastname;
    private String nickname;
    private double salary;
    private Address address;

    public Employee(String nif, String firstname, String lastname, String nickname, double salary, Address address) {
        this.nif = nif;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.salary = salary;
        this.address = address;
    }
    
    public Employee(String nif, String firstname, String lastname, String nickname, double salary) {
        this.nif = nif;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.salary = salary;
        this.address = new Address();
       
    }

    public Employee() {
    }

    public Employee(String nif) {
        this.nif = nif;
    }
    
    public Employee(Employee other) {
        this.nif = nif;
        this.firstname = other.firstname;
        this.lastname = other.lastname;
        this.nickname = other.nickname;
        this.salary = other.salary;
        this.address = other.address;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
        hash = 97 * hash + Objects.hashCode(this.nif);
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
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.nif, other.nif)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Employee{nif=").append(nif);
        sb.append(", firstname=").append(firstname);
        sb.append(", lastname=").append(lastname);
        sb.append(", nickname=").append(nickname);
        sb.append(", salary=").append(salary);
        sb.append(", address=").append(address);
        sb.append('}');
        return sb.toString();
    }
    
    
    

  
    
}

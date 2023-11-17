package xmlstaffdom.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * ADT for a company 
 * @author ProvenSoft
 */

public class Company {
    
    private String name;
    private List<Employee> employees;
    
    public Company(){
        employees = new ArrayList<>();
    }
    
    public Company(String name, List<Employee> employees){
        this.name = name;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Company{name=").append(name);
        sb.append(", employees=").append(employees);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.name);
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
        final Company other = (Company) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

   
    
  
    
}

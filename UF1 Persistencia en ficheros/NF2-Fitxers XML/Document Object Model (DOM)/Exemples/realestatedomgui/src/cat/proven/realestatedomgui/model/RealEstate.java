package cat.proven.realestatedomgui.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ProvenSoft
 */
public class RealEstate {
    private String name;
    private List<Estate> estates;

    public RealEstate(String name) {
        this.name = name;
        this.estates = new ArrayList<>();
    }
    
    public RealEstate() {
        this.name = "";
        this.estates = new ArrayList<>();
    }  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Estate> getEstates() {
        return estates;
    }

    public void setEstates(List<Estate> estates) {
        this.estates = estates;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.name);
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
        final RealEstate other = (RealEstate) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RealEstate{" + "name=" + name + ", estates=" + estates + '}';
    }
    
    /**
     * retrieves all estates.
     * @return a list with all the estates.
     */
    public List<Estate> findAll() {
        return estates;
    }
    
    /**
     * retrieves all houses.
     * @return a list with all the houses.
     */
    public List<Estate> findAllHouses() {
        List<Estate> found = new ArrayList<>();
        for (Estate e: estates) {
            if (e.getType().equalsIgnoreCase("house")) {
                found.add(e);
            }
        }
        return found;
    }
    
    /**
     * retrieves all premises.
     * @return a list with all the premises.
     */
    public List<Estate> findAllPremises() {
        List<Estate> found = new ArrayList<>();
        for (Estate e: estates) {
            if (e.getType().equalsIgnoreCase("premises")) {
                found.add(e);
            }
        }        
        return found;
    }
        
    
}

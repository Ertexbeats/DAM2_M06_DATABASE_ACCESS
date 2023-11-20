package damm06.uf1pe1.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Miguel Mendez
 */
public class Office implements Serializable {
    private String name;
    private List< Workspace > workspaces;

    public Office( String name, List< Workspace > workspaces ) {
        this.name = name;
        this.workspaces = workspaces;
    }

    public Office( String name ) {
        this.name = name;
        this.workspaces = new ArrayList<>();
    }

    public Office() {
        this.workspaces = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public List< Workspace > getWorkspaces() {
        return workspaces;
    }

    public void setWorkspaces( List< Workspace > workspaces ) {
        this.workspaces = workspaces;
    }

    public void addWorkspace( Workspace workspace ) {
        this.workspaces.add( workspace );
    }

    public void removeWorkspace( Workspace workspace ) {
        this.workspaces.remove( workspace );
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode( this.name );
        return hash;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Office other = ( Office ) obj;
        return Objects.equals( this.name, other.name );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "Office{" );
        sb.append( "name=" ).append( name );
        sb.append( ", workspaces=" ).append( workspaces );
        sb.append( '}' );
        return sb.toString();
    }

}

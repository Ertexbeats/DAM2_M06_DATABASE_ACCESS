package damm06.uf1pe1.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Miguel Mendez
 */
public class Workspace implements Serializable {
    private String code;
    private int seats;
    private List< String > phones;
    private boolean enclosed;

    public Workspace( String code, int seats, List< String > phones, boolean enclosed ) {
        this.code = code;
        this.seats = seats;
        this.phones = phones;
        this.enclosed = enclosed;
    }

    public Workspace( String code ) {
        this.code = code;
        this.phones = new ArrayList<>();
    }

    public Workspace( String code, int seats, boolean enclosed ) {
        this.code = code;
        this.seats = seats;
        this.phones = new ArrayList<>();
        this.enclosed = enclosed;
    }

    public Workspace() {
        this.phones = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode( String code ) {
        this.code = code;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats( int seats ) {
        this.seats = seats;
    }

    public List< String > getPhones() {
        return phones;
    }

    public void setPhones( List< String > phones ) {
        this.phones = phones;
    }

    public boolean isEnclosed() {
        return enclosed;
    }

    public void setEnclosed( boolean enclosed ) {
        this.enclosed = enclosed;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode( this.code );
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
        final Workspace other = ( Workspace ) obj;
        return Objects.equals( this.code, other.code );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "Workspace{" );
        sb.append( "code=" ).append( code );
        sb.append( ", seats=" ).append( seats );
        sb.append( ", phones=" ).append( phones );
        sb.append( ", enclosed=" ).append( enclosed );
        sb.append( '}' );
        return sb.toString();
    }

}

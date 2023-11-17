package cat.proven.game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ADT for a player
 *
 * @author ProvenSoft
 */
public class Players {
    //TODO
    // Attributes
    private String id;
    private String name;
    private int level;
    private double score;
    private List< Game > games;

    // Constructor
    public Players( String id, String name, int level, double score, List< Game > games ) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.score = score;
        this.games = new ArrayList<>();
    }

    public Players( String id, String name, int level, double score ) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.score = score;
    }

    public Players() {
    }

    public Players( String id ) {
        this.id = id;
    }

    public Players( Players other ) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.score = score;
    }

    // Getter and Setter
    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel( int level ) {
        this.level = level;
    }

    public double getScore() {
        return score;
    }

    public void setScore( double score ) {
        this.score = score;
    }

    public List< Game > getGames() {
        return games;
    }

    public void setGames( List< Game > games ) {
        this.games = games;
    }

    // Equals and HashCode
    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Players players ) ) return false;
        return Objects.equals( id, players.id );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id );
    }

    // toString

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer( "Players{" );
        sb.append( "id='" ).append( id ).append( '\'' );
        sb.append( ", name='" ).append( name ).append( '\'' );
        sb.append( ", level=" ).append( level );
        sb.append( ", score=" ).append( score );
        sb.append( '}' );
        return sb.toString();
    }
}

package cat.proven.game.model;

import java.util.List;
import java.util.Objects;

/**
 * ADT class for a game
 *
 * @author ProvenSoft
 */
public class Game {
    // TODO
    // Attributes
    private String id;
    private List< Players > players;

    // Constructor
    public Game( String id, List< Players > players ) {
        this.id = id;
        this.players = players;
    }

    public Game() {
    }

    public Game( String id ) {
        this.id = id;
    }

    public Game( Game other ) {
        this.id = id;
        this.players = players;
    }

    // Getter and Setter
    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public List< Players > getPlayers() {
        return players;
    }

    public void setPlayers( List< Players > players ) {
        this.players = players;
    }

    // Equals and HashCode
    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( !( o instanceof Game game ) ) return false;
        return Objects.equals( id, game.id );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id );
    }

    // toString

    @Override
    public String toString() {
        String sb = "Game{" + "id='" + id + '\'' +
                ", players=" + players +
                '}';
        return sb;
    }
}

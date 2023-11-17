package cat.proven.game.model.persist;

import cat.proven.game.model.Game;
import cat.proven.game.model.Players;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Content handler for games xml file.
 *
 * @author ProvenSoft
 */
public class GameContentHandler extends DefaultHandler {
    /**
     * the id of the game to retrieve
     */
    private String gameId;
    private ArrayList< Game > games;
    private Game game;
    private ArrayList< Players > players;
    private Players player;
    private String text;

    public String getGameId() {
        return gameId;
    }

    public void setGameId( String gameId ) {
        this.gameId = gameId;
    }

    public Game getGame( String gameId ) {
        return game;
    }

    @Override
    public void startDocument() throws SAXException {
        //TODO
    }

    @Override
    public void endDocument() throws SAXException {
        //TODO
    }

    @Override
    public void startElement( String uri, String localName, String qName, Attributes atts ) throws SAXException {
        //TODO
        switch ( qName.toLowerCase() ) {
            case "games" -> games = new ArrayList<>();
            case "game" -> {
                game = new Game();
                game.setId( atts.getValue( "id" ) );
            }

            case "players" -> players = new ArrayList<>();
            case "player" -> {
                player = new Players();
                player.setId( atts.getValue( "id" ) );
            }
            case "name", "level", "score" -> {
                //nothing to do
            }
            default -> {
                //nothing to do
            }
        }
    }

    @Override
    public void endElement( String uri, String localName, String qName ) throws SAXException {
        //TODO
        switch ( qName.toLowerCase() ) {
            case "games" -> {
                //nothing to do
            }
            case "game" -> games.add( game );
            case "players" -> game.setPlayers( players );
            case "player" -> players.add( player );
            case "name" -> player.setName( text );
            case "level" -> player.setLevel( Integer.parseInt( text ) );
            case "score" -> player.setScore( Double.parseDouble( text ) );
        }
    }

    @Override
    public void characters( char[] ch, int start, int length ) throws SAXException {
        text = new String( ch, start, length );
    }
}

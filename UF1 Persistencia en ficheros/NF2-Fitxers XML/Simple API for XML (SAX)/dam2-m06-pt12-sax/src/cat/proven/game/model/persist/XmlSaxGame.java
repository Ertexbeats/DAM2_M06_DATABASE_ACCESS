package cat.proven.game.model.persist;

import cat.proven.game.model.Game;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * SAX XML file reader for game.
 *
 * @author ProvenSoft
 */
public class XmlSaxGame {
    private String XML_FILENAME;

    /**
     * loads game data from xml file given the game id.
     *
     * @param gameId the id of the game to retrieve
     * @return TODO
     */
    public Game load( String gameId ) {
        Game data = null;
        //TODO
        //the path to xml file
        //final String XML_FILENAME = "resources/games.xml";
        //get a SAXParserFactory
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try ( InputStream xmlInput = new FileInputStream( XML_FILENAME ) ) {
            //get a SAXParser
            SAXParser saxParser = factory.newSAXParser();
            //instantiate a proper content handler
            GameContentHandler handler = new GameContentHandler();
            //parse xml file
            saxParser.parse( xmlInput, handler );
            //get read game from handler
            data = handler.getGame( gameId );
        } catch ( FileNotFoundException e ) {
            throw new RuntimeException( e );
        } catch ( IOException e ) {
            throw new RuntimeException( e );
        } catch ( ParserConfigurationException e ) {
            throw new RuntimeException( e );
        } catch ( SAXException e ) {
            throw new RuntimeException( e );
        }
        return data;
    }

    public String getXML_FILENAME() {
        return XML_FILENAME;
    }

    public void setXML_FILENAME( String XML_FILENAME ) {
        this.XML_FILENAME = XML_FILENAME;
    }
}

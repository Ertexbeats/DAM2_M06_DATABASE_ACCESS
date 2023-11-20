package damm06.uf1pe1.persist;

import damm06.uf1pe1.common.Office;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Miguel Mendez
 */
public class SAXOfficePersist {

    /**
     * prints a list
     *
     * @param <T>  type of each element in list
     * @param data the list to print
     */
    private static < T > void printData( List< T > data ) {
        data.forEach( System.out::println );
    }

    /**
     * reads with SAX a list of offices from xml file
     *
     * @param filename the filename to read
     * @return list of offices
     * @throw //TODO add throws to exceptions
     */
    public List< Office > readOfficesFromXML( String filename ) {
        List< Office > offices = null;
        //TODO
        //get a SAXParserFactory
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try ( InputStream xmlInput = new FileInputStream( filename ) ) {
            //get a SAXParser
            SAXParser saxParser = factory.newSAXParser();
            //instantiate a proper content handler
            SAXOfficeContentHandler handler = new SAXOfficeContentHandler();
            //parse xml file
            saxParser.parse( xmlInput, handler );
            //get read data from handler
            offices = handler.getData();
            //display data
            printData( offices );
        } catch ( FileNotFoundException e ) {
            throw new RuntimeException( e );
        } catch ( SAXException e ) {
            throw new RuntimeException( e );
        } catch ( IOException e ) {
            throw new RuntimeException( e );
        } catch ( ParserConfigurationException e ) {
            throw new RuntimeException( e );
        }
        return offices;
    }
}

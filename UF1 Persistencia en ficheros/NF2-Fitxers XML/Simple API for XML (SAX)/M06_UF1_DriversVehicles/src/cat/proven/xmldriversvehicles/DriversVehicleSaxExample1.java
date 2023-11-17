package cat.proven.xmldriversvehicles;

import cat.proven.xmldriversvehicles.model.persist.DriversSaxContentHandler1;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Parse xml file to get list of drivers with their vehicles
 *
 * @author ProvenSoft
 */
public class DriversVehicleSaxExample1 {

    public static void main( String argv[] ) {
        //the path to xml file
        final String XML_FILENAME = "drivers-vehicles.xml";
        //get a SAXParserFactory
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try ( InputStream xmlInput = new FileInputStream( XML_FILENAME ) ) {
            //get a SAXParser
            SAXParser saxParser = factory.newSAXParser();
            //instantiate a proper content handler
            DriversSaxContentHandler1 handler = new DriversSaxContentHandler1();
            //parse xml file
            saxParser.parse( xmlInput, handler );
            //get read data from handler
            Map< String, Integer > drivers = handler.getData();
            //display data
            printData( drivers );
        } catch ( FileNotFoundException ex ) {
            Logger.getLogger( DriversVehicleSaxExample.class.getName() ).log( Level.SEVERE, null, ex );
        } catch ( SAXException ex ) {
            Logger.getLogger( DriversVehicleSaxExample.class.getName() ).log( Level.SEVERE, null, ex );
        } catch ( IOException ex ) {
            Logger.getLogger( DriversVehicleSaxExample.class.getName() ).log( Level.SEVERE, null, ex );
        } catch ( ParserConfigurationException ex ) {
            Logger.getLogger( DriversVehicleSaxExample.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }

    /**
     * @param data
     */
    private static <T> void printData( Map< String, Integer > data ) {
        data.forEach( ( key, value ) -> System.out.format( "Driver %s can drive %d vehicles.\n", key, value ) );
    }
}


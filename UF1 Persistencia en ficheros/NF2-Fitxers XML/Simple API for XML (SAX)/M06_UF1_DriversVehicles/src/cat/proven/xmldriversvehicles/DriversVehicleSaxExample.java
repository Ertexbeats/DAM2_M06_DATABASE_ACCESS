package cat.proven.xmldriversvehicles;

import cat.proven.xmldriversvehicles.model.persist.DriversSaxContentHandler;
import cat.proven.xmldriversvehicles.model.Driver;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
/**
 * Parse xml file to get list of drivers with their vehicles
 * @author ProvenSoft
 */
public class DriversVehicleSaxExample {

    public static void main (String argv []) {
        //the path to xml file
        final String XML_FILENAME = "drivers-vehicles.xml";
        //get a SAXParserFactory
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try (InputStream xmlInput = new FileInputStream(XML_FILENAME)) {
            //get a SAXParser
            SAXParser saxParser = factory.newSAXParser();
            //instantiate a proper content handler
            DriversSaxContentHandler handler = new DriversSaxContentHandler();
            //parse xml file
            saxParser.parse(xmlInput, handler);
            //get read data from handler
            List<Driver> drivers = handler.getData();
            //display data
            printData(drivers);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DriversVehicleSaxExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DriversVehicleSaxExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DriversVehicleSaxExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DriversVehicleSaxExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * prints a list
     * @param <T> type of each element in list
     * @param data the list to print
     */
    private static <T> void printData(List<T> data) {
        data.forEach(System.out::println);
    }
}


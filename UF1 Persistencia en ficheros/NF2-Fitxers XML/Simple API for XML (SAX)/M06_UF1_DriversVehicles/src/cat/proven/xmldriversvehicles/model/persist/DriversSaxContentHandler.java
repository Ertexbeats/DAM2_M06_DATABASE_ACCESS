package cat.proven.xmldriversvehicles.model.persist;

import cat.proven.xmldriversvehicles.model.Driver;
import cat.proven.xmldriversvehicles.model.Vehicle;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Content handler to get list of drivers with their vehicles
 * from xml file
 *
 * @author ProvenSoft
 */
public class DriversSaxContentHandler extends DefaultHandler {

    private List< Driver > drivers;
    private List< Vehicle > vehicles;
    private Driver driver;
    private Vehicle vehicle;
    private String text;

    @Override
    public void startDocument() {
        //nothing to do
    }

    @Override
    public void endDocument() {
        //nothing to do
    }

    @Override
    public void characters( char[] ch, int start, int length )
            throws SAXException {
        this.text = new String( ch, start, length ).trim();
    }

    @Override
    public void startElement( String uri, String localName,
                              String qName, Attributes attributes ) throws SAXException {

        switch ( qName.toLowerCase() ) {
            case "drivers" -> drivers = new ArrayList<>();
            case "driver" -> driver = new Driver();
            case "driver-id", "firstname", "lastname" -> {
                //nothing to do
            }
            case "vehicles" -> vehicles = new ArrayList<>();
            case "vehicle" -> vehicle = new Vehicle();
            case "vehicle-id", "name" -> {
                //nothing to do
            }
            default -> {
                //nothing to do
            }
        }
    }

    @Override
    public void endElement( String uri, String localName,
                            String qName ) throws SAXException {
        switch ( qName.toLowerCase() ) {
            case "drivers" -> {
                //nothing to do
            }
            case "driver" -> drivers.add( driver );
            case "driver-id" -> driver.setId( Long.parseLong( text ) );
            case "firstname" -> driver.setFirstname( text );
            case "lastname" -> driver.setLastname( text );
            case "vehicles" -> driver.setVehicles( vehicles );
            case "vehicle" -> vehicles.add( vehicle );
            case "vehicle-id" -> vehicle.setId( Long.parseLong( text ) );
            case "name" -> vehicle.setName( text );
            default -> {
                //nothing to do
            }
        }
    }

    /**
     * gets the list of drivers
     *
     * @return list of drivers
     */
    public List< Driver > getData() {
        return this.drivers;
    }

}

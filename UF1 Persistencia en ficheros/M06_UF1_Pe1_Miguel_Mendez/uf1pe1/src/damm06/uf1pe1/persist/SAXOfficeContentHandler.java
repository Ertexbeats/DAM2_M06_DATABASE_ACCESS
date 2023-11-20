package damm06.uf1pe1.persist;

import damm06.uf1pe1.common.Office;
import damm06.uf1pe1.common.Workspace;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Miguel Mendez
 */
public class SAXOfficeContentHandler extends DefaultHandler {

    private List< Office > offices;
    private Office office;
    private List< Workspace > workspaces;
    private Workspace workspace;
    private String text;
    private List< String > phones;

    @Override
    public void characters( char[] ch, int start, int length ) throws SAXException {
        text = new String( ch, start, length );
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
            case "offices" -> {
                offices = new ArrayList<>();
            }
            case "office" -> {
                office = new Office();
            }
            case "name" -> {
            }
            case "workspaces" -> {
                workspaces = new ArrayList<>();
            }
            case "workspace" -> {
                workspace = new Workspace();
            }
            case "code" -> {
            }
            case "seats" -> {
            }
            case "phone" -> {
                phones = new ArrayList<>();
            }
            case "enclosed" -> {
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
            case "offices" -> {
            }
            case "office" -> {
                offices.add( office );
            }
            case "name" -> {
                office.setName( text );
            }
            case "workspaces" -> {
                office.setWorkspaces( workspaces );
            }
            case "workspace" -> {
                workspaces.add( workspace );
            }
            case "code" -> {
                workspace.setCode( text );
            }
            case "seats" -> {
                workspace.setSeats( Integer.parseInt( text ) );
            }
            case "phone" -> {
                phones.add( text );
                workspace.setPhones( phones );
            }
            case "enclosed" -> {
                workspace.setEnclosed( Boolean.parseBoolean( text ) );
            }
            default -> {
                //nothing to do
            }
        }
    }

    /**
     * gets the list of offices
     *
     * @return list of offices
     */
    public List< Office > getData() {
        return this.offices;
    }

}

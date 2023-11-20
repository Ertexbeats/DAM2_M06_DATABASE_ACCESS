package damm06.uf1pe1.persist;

import damm06.uf1pe1.common.Workspace;

import java.io.File;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * @author Miguel Mendez
 */
public class DOMOfficePersist {

    /**
     * creates a DOM simple type element
     *
     * @param document     the document to work with
     * @param elementName  the name of the element to create
     * @param elementValue the value of the text node
     * @return Element
     */
    private Element createSimpleTypeElement( Document document, String elementName, String elementValue ) {
        // create element.
        Element element = document.createElement( elementName );
        // create text node.
        Text textNode = document.createTextNode( elementValue );
        // append text node to element.
        element.appendChild( textNode );
        return element;
    }

    /**
     * saves to file a list of offices
     *
     * @param filename   the path to file to write
     * @param workspaces the workspaces to write
     * @return true if successfully written, false otherwise
     * @throws //TODO add throws to exceptions
     */
    public boolean saveWorkspacesToFile( String filename, List< Workspace > workspaces ) {
        boolean success = false;
        //TODO
        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();

            // create the root element Workspaces
            Element rootElement = document.createElement( "Workspaces" );
            document.appendChild( rootElement );

            // create and append the Workspace elements
            for ( Workspace workspacee : workspaces ) {
                Element workspaceElement = createWorkSpaceElement( document, workspacee );
                rootElement.appendChild( workspaceElement );
            }


            // Perform transformation.
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty( OutputKeys.METHOD, "xml" );
            transformer.setOutputProperty( OutputKeys.INDENT, "yes" );
            transformer.setOutputProperty( OutputKeys.ENCODING, "UTF-8" );
            transformer.setOutputProperty( OutputKeys.OMIT_XML_DECLARATION, "no" );

            // create a new DOMSource with the modified document
            DOMSource source = new DOMSource( document );

            // create a new StreamResult with the file where you want to save the XML
            StreamResult result = new StreamResult( new File( filename ) );

            // perform the transformation
            transformer.transform( source, result );

            success = true;
        } catch ( ParserConfigurationException | TransformerException e ) {
            throw new RuntimeException( e );
        }
        return success;
    }

    private Element createWorkSpaceElement( Document document, Workspace workspace ) {
        Element workSpaceElement = document.createElement( "Workspace" );

        // create and append the code, seats, and enclosed elements
        workSpaceElement.appendChild( createSimpleTypeElement( document, "code", workspace.getCode() ) );
        workSpaceElement.appendChild( createSimpleTypeElement( document, "seats", String.valueOf( workspace.getSeats() ) ) );
        workSpaceElement.appendChild( createSimpleTypeElement( document, "enclosed", String.valueOf( workspace.isEnclosed() ) ) );

        return workSpaceElement;
    }
}
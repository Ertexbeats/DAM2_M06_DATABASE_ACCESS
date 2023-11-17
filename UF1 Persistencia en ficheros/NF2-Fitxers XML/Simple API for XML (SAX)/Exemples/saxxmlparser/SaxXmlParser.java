
import java.io.*;
import java.net.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import org.xml.sax.ext.*;

import javax.xml.parsers.*;

/**
 * SaxXmlParser.java Exemple of parsing and displaying content of an xml file
 *
 * @author Jose Moreno
 */
public class SaxXmlParser {

    private static final String JAXP_SCHEMA_LANGUAGE
            = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    private static final String W3C_XML_SCHEMA
            = "http://www.w3.org/2001/XMLSchema";
    private static final String JAXP_SCHEMA_SOURCE
            = "http://java.sun.com/xml/jaxp/properties/schemaSource";

    public static void main(String[] args) {

        // The name of the XML file. 
        String xmlFilename = null;
        // The name of the file containing the Schema to validate the XML file. 
        String schemaFilename = null;

        // Configure validation, if necessary
        boolean dtdValidate = false;   // Change if necessary to validate DTD
        boolean xsdValidate = false;   // Change if necessary to validate XSL

        try {
            // Get command line parameters: the names of XML and Schema files.
            switch (args.length) {
                case 1: // Only name of XML file.
                    xmlFilename = args[0];
                    schemaFilename = null;
                    break;
                case 2: // Both XMl and Schema file names.
                    xmlFilename = args[0];
                    schemaFilename = args[1];
                    xsdValidate = true; // If XSD file, then validate schema
                    break;
                default: // No parameters.
                    // Must pass in the name of the XML file!.
                    System.err.println("Usage: SaxXmlParser filename [schemafilename]");
                    System.exit(1);
                    break;
            }
            // Create a parser factory for creating SAX parsers.
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // Set namespace capability
            spf.setNamespaceAware(true);

            //spf.setValidating(dtdValidate || xsdValidate);
            spf.setValidating(true);

            // Get a SAXParser object from the parser factory
            SAXParser parser = spf.newSAXParser();

            // Ser parser property for XSD validation. 
            //if (xsdValidate) {
            try {
                parser.setProperty(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
                // Specify the schema file name.
                // Not necessary if already defined in xml file.
                if (schemaFilename != null) {
                    parser.setProperty(JAXP_SCHEMA_SOURCE, new File(schemaFilename));
                }
            } catch (SAXNotRecognizedException x) {
                System.err.println("Error: JAXP SAXParser property not recognized: "
                        + JAXP_SCHEMA_LANGUAGE);

                System.err.println("Check to see if parser conforms to JAXP 1.2 spec.");
                System.exit(1);
            }
            //}

            // Get an XMLReader from the parser
            XMLReader xmlReader = parser.getXMLReader();

            // Set handlers to reader-----------------------
            // Create and set a new content handler for the parser. 
            // It implements DefaultHandler methods.
            SaxXmlContentHandler contentHandler = new SaxXmlContentHandler();
            xmlReader.setContentHandler(contentHandler);

            // Set DTD handler for the parser.		
            xmlReader.setDTDHandler(contentHandler);

            // Create and set a new error handler for the parser. 
            // It implements ErrorHandler methods.
            SaxXmlErrorHandler errorHandler = new SaxXmlErrorHandler();
            xmlReader.setErrorHandler(errorHandler);

            // Set entity resolver handler if necessary
            xmlReader.setEntityResolver(contentHandler);

            // Create and set a new lexical handler for the parser.
            // It implements LexicalHandler methods.
            // Do this if you want to identity 
            // comments, CDATA sections and references to parsed entities.
            // Create a new error handler for the parser. It implements ErrorHandler methods.
            SaxXmlLexicalHandler lexicalHandler = new SaxXmlLexicalHandler();
            xmlReader.setProperty("http://xml.org/sax/properties/lexical-handler",
                    lexicalHandler);

            // Begin parsing.-------------------------
            try {
                // Convert file to URL.
                URL url = contentHandler.fileToURL(new File(xmlFilename));
                // Parse file.
                xmlReader.parse(url.toString());
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            } catch (SAXParseException e) {
                System.err.println(e.getMessage());
            } catch (SAXException e) {
                System.err.println(e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    } //end main()

}

/**
 * SaxXmlContentHandler class Handler for content events with SaxXmlParser. It
 * handles the xml events and outputs the data.
 *
 * @author Jose Moreno
 */
class SaxXmlContentHandler extends DefaultHandler {

    @Override
    public void startDocument() {
        System.out.println("Begin of XML document");
    }

    @Override
    public void endDocument() {
        System.out.println("End of XML document");
    }

    @Override
    public void startElement(String uri, String name,
            String qName, Attributes atts) {
        System.out.println("\tBegin element: " + name);

        for (int i = 0; i < atts.getLength(); i++) {
            System.out.println("\t\tAtribute: "
                    + atts.getLocalName(i) + " = " + atts.getValue(i));
        }
    }

    @Override
    public void endElement(String uri, String name,
            String qName) {
        System.out.println("\tEnd Element: " + name);
    }

    @Override
    /**
     * notationDecl() Method belonging to interface DTDHandler, implemented by
     * DefaultHandler Receives notification of a notation declaration event.
     */
    public void notationDecl(String name, String publicId, String systemId) {
        // ...
    }

    @Override
    /**
     * unparsedEntityDecl() Method belonging to interface DTDHandler,
     * implemented by DefaultHandler Receives notification of an unparsed entity
     * declaration event.
     */
    public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) {
        // ...		
    }

    /**
     * fileToURL() Convenience method to convert a file to an url
     *
     * @throws Error if malformed url exception is generated
     */
    public URL fileToURL(File file) {
        String path = file.getAbsolutePath();
        String fSep = System.getProperty("file.separator");
        if (fSep != null && fSep.length() == 1) {
            path = path.replace(fSep.charAt(0), '/');
        }
        if (path.length() > 0 && path.charAt(0) != '/') {
            path = '/' + path;
        }
        try {
            return new URL("file", null, path);
        } catch (java.net.MalformedURLException e) {
            throw new Error("Unexpected MalformedURLException");
        }
    }
}

/**
 * SaxXmlErrorHandler class Handler for validation errors with SaxXmlParser. It
 * handles the xml validation events.
 *
 * @author Jose Moreno
 */
class SaxXmlErrorHandler implements ErrorHandler {

    @Override
    public void warning(SAXParseException e) throws SAXException {
        System.out.println(e.getMessage());
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.out.println(e.getMessage());
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.out.println(e.getMessage());
    }
}

/**
 * SaxXmlLexicalHandler class Handler for validation errors with SaxXmlParser.
 * It handles the DTD events.
 *
 * @author Jose Moreno
 */
class SaxXmlLexicalHandler implements LexicalHandler {

    public void warning(SAXParseException err) {
        // ...
    }

    @Override
    public void comment(char[] ch, int start, int length) throws SAXException {
        // ...   
    }

    @Override
    public void startCDATA() throws SAXException {
        // ...
    }

    @Override
    public void endCDATA() throws SAXException {
        // ...
    }

    @Override
    public void startEntity(String name) throws SAXException {
        // ...
    }

    @Override
    public void endEntity(String name) throws SAXException {
        // ...
    }

    @Override
    public void startDTD(String name, String publicId, String systemId)
            throws SAXException {
        // ...
    }

    @Override
    public void endDTD() throws SAXException {
        // ...
    }
    // ...

}

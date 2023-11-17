package schooldomappt.model.persistence;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import schooldomappt.model.Group;
import schooldomappt.model.School;
import schooldomappt.model.Student;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alumne
 */
public class SchoolXmlDomPersist implements SchoolPersist {
    private String filename;

    public SchoolXmlDomPersist() {
    }

    public SchoolXmlDomPersist( String filename ) {
        this.filename = filename;
    }

    public void setFileName( String filename ) {
        this.filename = filename;
    }

    @Override
    public School load() {

        School mSchool = null;
        try {
            // create document from XML using DocumentBuilder.
            File file = new File( filename );
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse( file );

            Element schoolElement = document.getDocumentElement();
            document.getDocumentElement().normalize();

            //read school data from document
            //get school name
            Element schoolNameElement = ( Element ) schoolElement.getElementsByTagName( "name" ).item( 0 );
            String name = schoolNameElement.getTextContent();

            //get groups
            Element groupsElement = ( Element ) schoolElement.getElementsByTagName( "groups" ).item( 0 );
            List< Group > groups = readGroups( groupsElement );

            //Instantiate school with data read from XML
            mSchool = new School( name, groups );
        } catch ( FileNotFoundException ex ) {
            mSchool = null;
            ex.printStackTrace( System.out );
            System.exit( 1 );
        } catch ( ParserConfigurationException | SAXException | IOException ex ) {
            mSchool = null;
            ex.printStackTrace( System.out );
            System.exit( 1 );
        }
        //TODO
        return mSchool;
    }

    /**
     * @param groupsElement
     * @return
     */
    private List< Group > readGroups( Element groupsElement ) {
        List< Group > groups = new ArrayList<>();

        NodeList groupNodes = groupsElement.getChildNodes();
        for ( int i = 0; i < groupNodes.getLength(); i++ ) {
            Node groupNode = groupNodes.item( i );
            if ( groupNode instanceof Element groupElement ) {
                Group group = readGroup( groupElement );
                groups.add( group );
            }
        }
        return groups;
    }

    private Group readGroup( Element groupElement ) {
        Group group;
        try {
            String name = groupElement.getElementsByTagName( "name" ).item( 0 ).getTextContent();
            String tutor = groupElement.getElementsByTagName( "tutor" ).item( 0 ).getTextContent();
            String curriculum = groupElement.getElementsByTagName( "curriculum" ).item( 0 ).getTextContent();
            Element studentsElement = ( Element ) groupElement.getElementsByTagName( "students" ).item( 0 );
            List< Student > students = readStudents( studentsElement );
            if ( students != null ) {
                group = new Group( name, tutor, curriculum, students );
            } else {
                group = new Group( name, tutor, curriculum );
            }
        } catch ( Exception ex ) {
            group = null;
        }
        return group;
    }

    private List< Student > readStudents( Element studentsElement ) {
        List< Student > students = new ArrayList<>();
        NodeList studentNodes = studentsElement.getChildNodes();
        for ( int i = 0; i < studentNodes.getLength(); i++ ) {
            Node studentNode = studentNodes.item( i );
            if ( studentNode instanceof Element studentElement ) {
                Student student = readStudent( studentElement );
                students.add( student );
            }
        }
        return students;
    }

    private Student readStudent( Element studentElement ) {
        Student student = null;
        try {
            String id = studentElement.getAttribute( "id" );
            String name = studentElement.getElementsByTagName( "name" ).item( 0 ).getTextContent();
            String surname = studentElement.getElementsByTagName( "surname" ).item( 0 ).getTextContent();
            Element emailsElement = ( Element ) studentElement.getElementsByTagName( "emails" ).item( 0 );
            List< String > emails = readEmails( emailsElement );
            String ageS = studentElement.getElementsByTagName( "age" ).item( 0 ).getTextContent();
            int age = Integer.parseInt( ageS );

            if ( emails != null ) {
                student = new Student( id, name, surname, emails, age );
            } else {
                student = new Student( id, name, surname, age );
            }
        } catch ( Exception ex ) {
            student = null;
        }
        return student;
    }

    private List< String > readEmails( Element emailsElement ) {
        List< String > emails = new ArrayList<>();
        NodeList emailNodes = emailsElement.getElementsByTagName( "email" );
        if ( emailNodes.getLength() >= 1 ) {
            for ( int i = 0; i < emailNodes.getLength(); i++ ) {
                String email = readEmail( ( Element ) emailNodes.item( i ) );
                emails.add( email );
            }
        } else {
            emails = null;
        }
        return emails;
    }

    private String readEmail( Element emailElement ) {
        return emailElement.getTextContent();
    }


    @Override
    public void save( School school ) {
        //TODO
        try {
            // create a new document
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();

            // create the root element (school)
            Element rootElement = document.createElement( "school" );
            document.appendChild( rootElement );

            // create and append the name element
            Element nameElement = createSimpleTypeElement( document, "name", school.getName() );
            rootElement.appendChild( nameElement );

            // create and append the groups element
            Element groupsElement = document.createElement( "groups" );
            rootElement.appendChild( groupsElement );

            // iterate over groups and add them to the groups element
            for ( Group group : school.getGroups() ) {
                Element groupElement = createGroupElement( document, group );
                groupsElement.appendChild( groupElement );
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


        } catch ( ParserConfigurationException | TransformerException e ) {
            throw new RuntimeException( e );
        }
    }

    public Element createSimpleTypeElement( Document document, String elementName, String elementValue ) {
        // create element.
        Element element = document.createElement( elementName );
        // create text node.
        Text textNode = document.createTextNode( elementValue );
        // append text node to element.
        element.appendChild( textNode );
        return element;
    }

    // Helper method to create a group element
    private Element createGroupElement( Document document, Group group ) {
        Element groupElement = document.createElement( "group" );

        // create and append the name, tutor, and curriculum elements
        groupElement.appendChild( createSimpleTypeElement( document, "name", group.getName() ) );
        groupElement.appendChild( createSimpleTypeElement( document, "tutor", group.getTutor() ) );
        groupElement.appendChild( createSimpleTypeElement( document, "curriculum", group.getCurriculum() ) );

        // create and append the students element
        Element studentsElement = document.createElement( "students" );
        groupElement.appendChild( studentsElement );

        // iterate over students and add them to the students element
        for ( Student student : group.getStudents() ) {
            Element studentElement = createStudentElement( document, student );
            studentsElement.appendChild( studentElement );
        }

        return groupElement;
    }

    // Helper method to create a student element
    private Element createStudentElement( Document document, Student student ) {
        Element studentElement = document.createElement( "student" );

        // set the id attribute
        studentElement.setAttribute( "id", student.getId() );

        // create and append the name, surname, age elements
        studentElement.appendChild( createSimpleTypeElement( document, "name", student.getName() ) );
        studentElement.appendChild( createSimpleTypeElement( document, "surname", student.getSurname() ) );
        // create and append the emails element
        Element emailsElement = document.createElement( "emails" );
        studentElement.appendChild( emailsElement );
        // iterate over emails and add them to the emails element
        for ( String email : student.getEmails() ) {
            Element emailElement = createSimpleTypeElement( document, "email", email );
            emailsElement.appendChild( emailElement );
        }
        studentElement.appendChild( createSimpleTypeElement( document, "age", String.valueOf( student.getAge() ) ) );


        return studentElement;
    }
}
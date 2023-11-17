package xmlstaffdom.model.persistence;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import xmlstaffdom.model.Address;
import xmlstaffdom.model.Company;
import xmlstaffdom.model.Employee;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author ProvenSoft
 */

public class CompanyXmlDomPersist2 {

    private String filename;

    public CompanyXmlDomPersist2() {
    }

    public CompanyXmlDomPersist2( String filename ) {
        this.filename = filename;
    }

    public void setFileName( String filename ) {
        this.filename = filename;
    }

    /**
     * Read data from data source based on filename attribute
     *
     * @return Company read or null if any error occurs
     */
    public Company loadCompany() {

        Company company = null;
        try {
            // create document from XML using DocumentBuilder.
            File file = new File( filename );
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse( file );

            //get root element
            Element companyElement = document.getDocumentElement();
            companyElement.normalize();

            //read company data from document
            //get company name
            Element companyNameElement = ( Element ) companyElement.getElementsByTagName( "name" ).item( 0 );
            String name = companyNameElement.getTextContent();

            //get employees
            Element employeesElement = ( Element ) companyElement.getElementsByTagName( "employees" ).item( 0 );
            List< Employee > employees = readEmployees( employeesElement );

            //Instantiate company with data read from XML
            company = new Company( name, employees );

        } catch ( FileNotFoundException ex ) {
            //ex.printStackTrace(System.out);
            //System.exit(1);
            company = null;
        } catch ( ParserConfigurationException | SAXException | IOException ex ) {
            //ex.printStackTrace(System.out);
            //System.exit(1);
            company = null;
        }

        return company;
    }

    /**
     * Transform a collection of employees emelement to a employees list
     *
     * @param employeesElement to be transformed
     * @return employees list or an empty list if no data can be transformed
     */
    private List< Employee > readEmployees( Element employeesElement ) {
        List< Employee > employees = new ArrayList<>();

        NodeList employeeNodes = employeesElement.getChildNodes();
        for ( int i = 0; i < employeeNodes.getLength(); i++ ) {
            Node employeeNode = employeeNodes.item( i );
            if ( employeeNode instanceof Element employeeElement ) {
                Employee employee = readEmployee( employeeElement );
                employees.add( employee );
            }
        }

        return employees;
    }

    /**
     * Transform an employee element to an Employee
     *
     * @param employeeElement to be transformed
     * @return en Employee or null if any error occurs
     */
    private Employee readEmployee( Element employeeElement ) {
        Employee employee = null;

        try {
            String nif = employeeElement.getAttribute( "nif" );
            String firstname = employeeElement.getElementsByTagName( "firstname" ).item( 0 ).getTextContent();
            String lastname = employeeElement.getElementsByTagName( "lastname" ).item( 0 ).getTextContent();
            String nickname = employeeElement.getElementsByTagName( "nickname" ).item( 0 ).getTextContent();
            String sSalary = employeeElement.getElementsByTagName( "salary" ).item( 0 ).getTextContent();
            double salary = Double.parseDouble( sSalary );
            NodeList addressNodes = employeeElement.getElementsByTagName( "address" );
            if ( addressNodes.getLength() == 1 ) {
                Address address = readAddress( ( Element ) addressNodes.item( 0 ) );
                employee = new Employee( nif, firstname, lastname, nickname, salary, address );
            } else {
                employee = new Employee( nif, firstname, lastname, nickname, salary );
            }


        } catch ( NumberFormatException e ) {
            employee = null;
        }

        return employee;
    }


    /**
     * Transform an address element to an Address
     *
     * @param addressElement to be transformed
     * @return an Address or null if any error occurs
     */
    private Address readAddress( Element addressElement ) {
        Address address = null;

        try {
            String street = addressElement.getElementsByTagName( "street" ).item( 0 ).getTextContent();
            String sNumber = addressElement.getElementsByTagName( "number" ).item( 0 ).getTextContent();
            int number = Integer.parseInt( sNumber );
            address = new Address( street, number );
        } catch ( NumberFormatException e ) {
            address = null;
        }

        return address;
    }

}

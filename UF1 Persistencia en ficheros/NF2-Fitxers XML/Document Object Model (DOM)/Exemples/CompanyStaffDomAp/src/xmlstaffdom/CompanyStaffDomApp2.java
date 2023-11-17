package xmlstaffdom;

import xmlstaffdom.model.Company;
import xmlstaffdom.model.Employee;
import xmlstaffdom.model.persistence.CompanyXmlDomPersist;
import xmlstaffdom.model.persistence.CompanyXmlDomPersist2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class CompanyStaffDomApp2 {

    private boolean exit;


    public static void main( String[] args ) {
        CompanyStaffDomApp2 ap = new CompanyStaffDomApp2();
        ap.run();
    }

    /**
     * Application main method.
     * Show the main menu and execute control methods.
     */
    private void run() {

        exit = false;


        do {
            int op = printMenu();
            switch ( op ) {
                case 0 -> exit = true;
                case 1 -> showCompanyData();
                default -> {
                }
            }
        } while ( !exit );
    }


    /**
     * prints main menu an gets user's option.
     *
     * @return user's option.
     */
    private int printMenu() {
        final String[] opts = {
                "Exit", "Show company data"
        };
        for ( int i = 0; i < opts.length; i++ ) {
            System.out.format( "[%d]. %s\n", i, opts[ i ] );
        }
        Scanner sc = new Scanner( System.in );
        System.out.print( "Select an option: " );
        int option = -1;
        try {
            option = Integer.parseInt( sc.next() );
        } catch ( NumberFormatException e ) {
            option = -1;
        }
        return option;
    }

    /**
     * Ask to the user the XML file name where data source is. Parse xml data in
     * order to load a Company object and show it.
     */
    private void showCompanyData() {
        String message;

        //read file name
        String filename = showInputDialog( "Input file name: " );

        CompanyXmlDomPersist2 persister = new CompanyXmlDomPersist2( filename );
        Company company = persister.loadCompany();


        if ( company != null ) {
            showMessage( "Successfully read" );
            showCompany( company );

        } else {
            showMessage( "Read fail" );
        }

    }


    /********* VIEW METHODS ************/
    /**
     * Displays a message to the users and reads the answer
     *
     * @param message to be shown
     * @return input string from the user
     */
    public String showInputDialog( String message ) {
        System.out.print( message );
        Scanner sc = new Scanner( System.in );
        return sc.next();
    }

    /**
     * Displays a message.
     *
     * @param message to display
     */
    public void showMessage( String message ) {
        System.out.println( message );
    }

    /**
     * Show company data
     *
     * @param company to be shown
     */
    public void showCompany( Company company ) {
        Map< String, Double > hm = new HashMap<>();
        showMessage( "Company name: " + company.getName() );
        showMessage( "Employees: " );

        for ( Employee employee : company.getEmployees() ) {
            String name = employee.getFirstname() + " " + employee.getLastname();
            hm.put( name, employee.getSalary() );
        }
        Iterator it = hm.keySet().iterator();
        while ( it.hasNext() ) {
            String name = (String) it.next();
            Double salary = hm.get( name );
            showMessage( name + " " + salary );
        }


        //company.getEmployees().forEach( System.out::println );
    }


}

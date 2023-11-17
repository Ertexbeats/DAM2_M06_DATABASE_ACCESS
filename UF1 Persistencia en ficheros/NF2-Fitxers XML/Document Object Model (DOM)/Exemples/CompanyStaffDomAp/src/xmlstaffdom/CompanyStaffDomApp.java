package xmlstaffdom;

import java.util.Scanner;

import xmlstaffdom.model.Company;
import xmlstaffdom.model.persistence.CompanyXmlDomPersist;

public class CompanyStaffDomApp {
    
    private boolean exit;
    
    
    
    public static void main(String[] args) {
        CompanyStaffDomApp ap = new CompanyStaffDomApp();
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
            switch (op) {
                case 0 -> exit = true;
                case 1 -> showCompanyData();
                default -> {
                }
            }
        } while (!exit);
    }
       

    /**
     * prints main menu an gets user's option.
     * @return user's option.
    */
    private int printMenu() {
        final String [] opts = {
            "Exit", "Show company data"
        };
        for (int i=0; i<opts.length; i++) {
            System.out.format("[%d]. %s\n", i, opts[i]);
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("Select an option: ");
        int option = -1;
        try {
            option = Integer.parseInt(sc.next());
        } catch (NumberFormatException e) {
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
        String filename = showInputDialog("Input file name: ");
        
        CompanyXmlDomPersist persister = new CompanyXmlDomPersist(filename);
        Company company = persister.loadCompany();
        
      
        if (company!= null) {
            showMessage("Successfully read");
            showCompany(company);
            
        } else {
             showMessage("Read fail");
        }
              
    }
    
    
    /********* VIEW METHODS ************/
    /**
     * Displays a message to the users and reads the answer
     * @param message to be shown
     * @return input string from the user
     */
    public String showInputDialog(String message) {
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    /**
     * Displays a message.
     * @param message to display
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Show company data
     * @param company to be shown
     */
    public void showCompany(Company company) {
        showMessage("Company name: "+ company.getName());
        showMessage("Employees: ");
        company.getEmployees().forEach(System.out::println);
    }
    
    
    
}

package cat.proven.categprods;

import cat.proven.categprods.utils.StoreDalException;
import cat.proven.categprods.model.Category;
import cat.proven.categprods.model.Product;
import cat.proven.categprods.model.StoreModel;
import cat.proven.categprods.views.MainMenu;

import java.util.List;
import java.util.Scanner;

/**
 * @author ProvenSoft
 */
public class Main {

    private MainMenu mainMenu;
    private boolean exit;  //flag to exit application

    private StoreModel model;

    public static void main( String[] args ) {
        Main ap = new Main();
        ap.start();
    }

    private void start() {
        //instantiate model
        model = new StoreModel();
        exit = false;
        mainMenu = new MainMenu();
        //interact with user
        do {
            //display menu
            mainMenu.show();
            //get action
            String action = mainMenu.getSelectedOptionActionCommand();
            if ( action == null ) {
                action = "invalidaction";
            }
            //control
            switch ( action ) {
                case "exit": //exit
                    exit = true;
                    break;
                case "category/all":  //list all categories
                    listAllCategories();
                    break;
                case "category/code":  //search category by code
                    break;
                case "category/add":  //add a new category
                    addCategory();
                    break;
                case "category/modify":  //modify a new category
                    modifyCategory();
                    break;
                case "category/remove":  //remove a new category
                    break;
                case "product/all":  //list all products
                    break;
                case "product/code":  //search product by code
                    listProductByCode();
                    break;
                case "product/name":  //search product like name
                    break;
                case "product/stockmin":  //search product by minim stock
                    break;
                case "product/add":  //add a new product
                    break;
                case "product/modify":  //modify a new product
                    break;
                case "product/remove":  //remove a new product
                    break;
                case "product/category": //search products by category
                    break;
                default:
                    System.out.println( "Invalid action" );
                    break;
            }
        } while ( !exit );
    }

    /*******  CONTROL METHODS *******/

    /**
     * gets a list with all categories and displays it.
     */
    private void listAllCategories() {
        try {
            List< Category > data = model.findAllCategories();
            if ( data != null ) {
                displayList( data );
            } else {
                displayMessage( "Error retrieving data" );
            }
        } catch ( StoreDalException ex ) {
            displayMessage( "Error connecting to database" );
        }
    }

    /**
     * asks the user the code to search,
     * if correcty read, it gets the product with that code,
     * and reports result to user
     */
    public void listProductByCode() {
        //read code
        String code = inputString( "Input product code: " );
        if ( code != null ) {
            Product found = model.findProductByCode( code );
            if ( found != null ) {
                //display product
                displayProduct( found );
            } else {
                displayMessage( "Product not found" );
            }
        } else {
            displayMessage( "Error reading code" );
        }
    }

    /**
     * asks the user to input data for the new category,
     * if correctly read, adds the category to the database,
     * preventing code duplicates, null objects, null codes.
     * and report result to user.
     */
    public void addCategory() {
        Category category = inputCategory();
        if ( category != null ) {
            int result = model.addCategory( category );
            if ( result == 1 ) {
                displayMessage( "Category succeessfully added" );
            } else {
                displayMessage( "Error adding category" );
            }
        } else {
            displayMessage( "Error reading data" );
        }
    }

    /**
     * asks the user to input the code of the category to modify,
     * searches the category in database with given code,
     * if found, it displays category to user, if not found it reports to user
     * asks for confirmation and asks new data for the category,
     * otherwise it reports tu user.
     * If new data correctly read, then it modifies the category in database.
     * Finally, it reports result to user.
     * It prevents code duplicates, null codes, ...
     */
    private void modifyCategory() {
        throw new UnsupportedOperationException( "Not supported yet." ); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /*******  VIEW METHODS *******/

    /**
     * displays a list of data
     *
     * @param <T>  data type to display
     * @param data the list to display
     */
    public < T > void displayList( List< T > data ) {
        if ( data != null ) {
            data.forEach( System.out::println );
            System.out.format( "%d elements displayed\n", data.size() );
        }
    }

    /**
     * prompts a message to user and read answer
     *
     * @param message the message to display
     * @return user's answer
     */
    public String inputString( String message ) {
        System.out.print( message );
        Scanner sc = new Scanner( System.in );
        return sc.next();
    }

    /**
     * displays a product
     *
     * @param product the product to display
     */
    public void displayProduct( Product product ) {
        System.out.println( product );
    }

    /**
     * displays a message
     *
     * @param message the message to display
     */
    public void displayMessage( String message ) {
        System.out.println( message );
    }

    /**
     * asks data for a category
     *
     * @return a category object with entered data or null in case or error
     */
    public Category inputCategory() {
        String code = inputString( "code: " );
        String name = inputString( "name: " );
        return new Category( 0, code, name );
    }

}

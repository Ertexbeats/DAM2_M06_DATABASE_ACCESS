package cat.proven.categprods;

import cat.proven.categprods.exceptions.StoreDalException;
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
    private String code;
    private String name;

    /*******  CONTROL METHODS *******/
    private int stock;
    private double price;
    private Category category;

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
                    listCategoryByCode();
                    break;
                case "category/add":  //add a new category
                    addCategory();
                    break;
                case "category/modify":  //modify a new category
                    modifyCategory();
                    break;
                case "category/remove":  //remove a new category
                    removeCategory();
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

    /*******  VIEW METHODS *******/

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
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            displayMessage( "Error connecting to database" );
        }
    }

    public void listCategoryByCode() {
        //read code
        String code = inputString( "Input category code: " );
        if ( code != null ) {
            Category found = model.findCategoryByCode( code );
            if ( found != null ) {
                //display product
                displayCategory( found );
            } else {
                displayMessage( "Category not found" );
            }
        } else {
            displayMessage( "Error reading code" );
        }
    }

    /**
     * asks the user to input data for the new category,
     * if correctly read, adds the category to the database,
     * preventing code duplicates, null objects, null codes.
     * and report result to user,.
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
        String code = inputString( "Input the code of the category to modify: " );
        displayMessage( "Category to modify" );
        Category oldCategory = model.findCategoryByCode( code );
        if ( oldCategory != null ) {
            displayCategory( oldCategory );
            String answer = inputString( "Are you sure? (y/n): " );
            if ( answer.equalsIgnoreCase( "y" ) ) {
                displayMessage( "Input new category" );
                Category newCategory = inputCategory();
                if ( newCategory != null ) {
                    int result = model.modifyCategory( oldCategory, newCategory );
                    String message = result == 1 ? "Category modified" : "Error modifying category";
                    displayMessage( message );
                } else {
                    displayMessage( "Error reading data" );
                }
            }
        } else {
            displayMessage( "Category not found" );
        }
    }

    public void removeCategory() {
        String code = inputString( "Input the code of the category to remove: " );
        displayMessage( "Category to remove remove" );
        Category category = model.findCategoryByCode( code );
        displayCategory( category );
        String answer = inputString( "Are you sure? (y/n): " );
        if ( answer.equalsIgnoreCase( "y" ) ) {
            int result = model.removeCategory( category );
            String message = result == 1 ? "Category removed" : "Error removing category";
            displayMessage( message );
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
     * prompts a message to user and read answer
     *
     * @param message the message to display
     * @return user's answer
     */
    public int inputInt( String message ) {
        System.out.print( message );
        Scanner sc = new Scanner( System.in );
        return sc.nextInt();
    }

    /**
     * prompts a message to user and read answer
     *
     * @param message the message to display
     * @return user's answer
     */
    public Double inputDouble( String message ) {
        System.out.print( message );
        Scanner sc = new Scanner( System.in );
        return sc.nextDouble();
    }

    /**
     * prompts a message to user and read answer
     *
     * @param message the message to display
     * @return user's answer
     */
    public Long inputLong( String message ) {
        System.out.print( message );
        Scanner sc = new Scanner( System.in );
        return sc.nextLong();
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
     * displays a product
     *
     * @param category the product to display
     */
    public void displayCategory( Category category ) {
        System.out.println( category );
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

    /**
     * asks data for a Product
     *
     * @return a product object with entered data or null in case or error
     */
    public Product inputProduct() {
        String code = inputString( "code: " );
        String name = inputString( "name: " );
        int stock = inputInt( "stock: " );
        double price = inputDouble( "price: " );
        Long categoryId = inputLong( "category id: " );
        Category category = new Category( categoryId );
        return new Product( 0, code, name, stock, price, category );
    }
}

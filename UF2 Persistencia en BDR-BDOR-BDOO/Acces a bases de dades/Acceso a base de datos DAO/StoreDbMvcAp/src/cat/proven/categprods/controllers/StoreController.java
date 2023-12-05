package cat.proven.categprods.controllers;

import cat.proven.categprods.model.Product;
import cat.proven.categprods.utils.StoreDalException;
import cat.proven.categprods.model.Category;
import cat.proven.categprods.model.StoreModel;
import cat.proven.categprods.views.StoreView;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ProvenSoft
 */
public class StoreController {

    private final StoreModel model;
    private final StoreView view;

    public StoreController( StoreModel model ) {
        this.model = model;
        this.view = new StoreView( this, model );
    }

    public void start() {
        view.display();
    }

    /**
     * Processes the given action and performs the corresponding operation.
     *
     * @param action the action to be processed
     */
    public void processAction( String action ) {
        if ( action != null ) {
            switch ( action ) {
                case "exit": //exit
                    exitApplication();
                    break;
                case "category/all":
                    listAllCategories();
                    break;
                case "category/code":
                    listCategoryByCode();
                    break;
                case "category/add":
                    addCategory();
                    break;
                case "category/modify":
                    modifyCategory();
                    break;
                case "category/remove":
                    removeCategory();
                    break;
                case "product/all":
                    listAllProducts();
                    break;
                case "product/code":
                    listProductByCode();
                    break;
                case "product/name":
                    listProductByName();
                    break;
                case "product/stockmin":
                    listProductByStockMin();
                    break;
                case "product/add":
                    addProduct();
                    break;
                case "product/modify":
                    modifyProduct();
                    break;
                case "product/remove":
                    removeProduct();
                    break;
//                case "product/category":
//                listProductByCategory();
//                    break;
                default:
                    view.displayMessage( "Action not supported" );
                    break;
            }
        }
    }

    /**
     * asks for confirmation and, if so, exits application.
     */
    public void exitApplication() {
        String answer = view.inputString( "Sure to exit? " );
        if ( answer.equalsIgnoreCase( "yes" ) ) {
            view.close();
        }
    }

    /**
     * get all categories from database and displays them.
     */
    private void listAllCategories() {
        try {
            //retrieve all categories
            List< Category > data = model.findAllCategories();
            //display result
            if ( data != null ) {
                view.displayList( data );
            } else {
                view.displayMessage( "Null data" );
            }
        } catch ( StoreDalException ex ) {
            view.displayMessage( "Error retrieving data" );
        }

    }

    /**
     * Retrieves a category by its code and displays it.
     */
    private void listCategoryByCode() {
        String code = view.inputString( "Code: " );
        Category category = model.findCategoryByCode( code );
        if ( category != null ) {
            view.displayCategory( category );
        } else {
            view.displayMessage( "Category not found" );
        }
    }

    /**
     * asks the user to input data for the new category,
     * if correctly read, adds the category to the database,
     * preventing code duplicates, null objects, null codes.
     * and report result to user.
     */
    public void addCategory() {
        Category category = view.inputCategory();
        if ( category != null ) {
            int result = model.addCategory( category );
            if ( result == 1 ) {
                view.displayMessage( "Category successfully added" );
            } else {
                view.displayMessage( "Error adding category" );
            }
        } else {
            view.displayMessage( "Error reading data" );
        }
    }

    /**
     * Modifies a category in the system.
     */
    public void modifyCategory() {
        view.displayMessage( "Input the code of the category to modify" );
        String code = view.inputString( "Code: " );
        Category oldCategory = model.findCategoryByCode( code );
        if ( oldCategory != null ) {
            view.displayMessage( "Category to modify" );
            view.displayCategory( oldCategory );
            view.displayMessage( "Input new data" );
            Category newCategory = view.inputCategory();
            if ( newCategory != null ) {
                int result = model.modifyCategory( oldCategory, newCategory );
                String message = result == 1 ? "Category modified" : "Error modifying category";
                view.displayMessage( message );
                view.displayCategory( newCategory );
            }
        } else {
            view.displayMessage( "Category not found" );
        }
    }

    /**
     * Removes a category.
     */
    public void removeCategory() {
        view.displayMessage( "Input the code of the category to remove" );
        String code = view.inputString( "Code: " );
        Category category = model.findCategoryByCode( code );
        if ( category != null ) {
            view.displayMessage( "Category to remove" );
            view.displayCategory( category );
            int result = model.removeCategory( category );
            String message = result == 1 ? "Category removed" : "Error removing category";
            view.displayMessage( message );
        } else {
            view.displayMessage( "Category not found" );
        }
    }

    /**
     * Retrieves all products and displays the result.
     */
    public void listAllProducts() {
        try {
            //retrieve all products
            List< Product > data = model.findAllProducts();
            //display result
            if ( data != null ) {
                view.displayList( data );
            } else {
                view.displayMessage( "Null data" );
            }
        } catch ( StoreDalException ex ) {
            view.displayMessage( "Error retrieving data" );
        }
    }

    /**
     * This function lists a product by its code.
     */
    private void listProductByCode() {
        String code = view.inputString( "Code: " );
        Product product = model.findProductByCode( code );
        if ( product != null ) {
            view.displayProduct( product );
        } else {
            view.displayMessage( "Product not found" );
        }
    }

    /**
     * Retrieves a list of products by name and displays them.
     */
    private void listProductByName() {
        try {
            String name = view.inputString( "Name: " );
            List< Product > data = null;
            data = model.findProductsByName( name );
            if ( data != null ) {
                view.displayList( data );
            } else {
                view.displayMessage( "Null data" );
            }
        } catch ( StoreDalException e ) {
            view.displayMessage( "Product not found" );
        }
    }

    /**
     * Retrieves a list of products with stock quantity greater than or equal to the specified minimum stock level.
     */
    private void listProductByStockMin() {
        int stockMin = view.inputInt( "Stock min: " );
        List< Product > data = model.findProductsByStockMin( stockMin );
        if ( data != null ) {
            view.displayList( data );
        } else {
            view.displayMessage( "Null data" );
        }
    }

    /**
     * Adds a product to the system.
     */
    private void addProduct() {
        Product product = view.inputProduct();
        if ( product != null ) {
            int result = model.addProduct( product );
            if ( result == 1 ) {
                view.displayMessage( "Product successfully added" );
            } else {
                view.displayMessage( "Error adding product" );
            }
        } else {
            view.displayMessage( "Error reading data" );
        }
    }

    /**
     * Modifies a product in the system.
     */
    public void modifyProduct() {
        view.displayMessage( "Input the code of the product to modify" );
        String code = view.inputString( "Code: " );
        Product oldProduct = model.findProductByCode( code );
        if ( oldProduct != null ) {
            view.displayMessage( "Product to modify" );
            view.displayProduct( oldProduct );
            view.displayMessage( "Input new data" );
            Product newProduct = view.inputProduct();
            if ( newProduct != null ) {
                int result = model.modifyProduct( oldProduct, newProduct );
                String message = result == 1 ? "Category modified" : "Error modifying category";
                view.displayMessage( message );
                view.displayProduct( newProduct );
            }
        } else {
            view.displayMessage( "Category not found" );
        }
    }

    /**
     * Removes a product from the system.
     */
    public void removeProduct() {
        view.displayMessage( "Input the code of the product to remove" );
        String code = view.inputString( "Code: " );
        Product product = model.findProductByCode( code );
        if ( product != null ) {
            view.displayMessage( "Category to remove" );
            view.displayProduct( product );
            int result = model.removeProduct( product );
            String message = result == 1 ? "Product removed" : "Error removing product";
            view.displayMessage( message );
        } else {
            view.displayMessage( "Category not found" );
        }
    }
}

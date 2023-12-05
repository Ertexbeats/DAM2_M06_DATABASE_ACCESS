package cat.proven.categprods.views;

import cat.proven.menu.Menu;
import cat.proven.menu.Option;

/**
 * @author ProvenSoft
 */
public class MainMenu extends Menu {

    public MainMenu() {
        title = "Store main menu";
        //
        addOption( new Option( "Exit", "exit" ) );
        addOption( new Option( "List all categories", "category/all" ) );
        addOption( new Option( "Search category by code", "category/code" ) );
        addOption( new Option( "Add a new category", "category/add" ) );
        addOption( new Option( "Modify a category", "category/modify" ) );
        addOption( new Option( "remove a category", "category/remove" ) );
        //
        addOption( new Option( "List all products", "product/all" ) );
        addOption( new Option( "Search product by code", "product/code" ) );
        addOption( new Option( "Search products like name", "product/name" ) );
        addOption( new Option( "Search products by minim stock", "product/stockmin" ) );
        addOption( new Option( "Add a new product", "product/add" ) );
        addOption( new Option( "Modify a product", "product/modify" ) );
        addOption( new Option( "Remove a product", "product/remove" ) );

        //TODO add more options
        addOption( new Option( "Search products by category", "product/category" ) );
    }

}

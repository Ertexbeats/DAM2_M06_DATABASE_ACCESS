package cat.proven.categprods;

import cat.proven.categprods.utils.StoreDalException;
import cat.proven.categprods.model.Category;
import cat.proven.categprods.model.Product;
import cat.proven.categprods.model.persist.CategoryDao;
import cat.proven.categprods.model.persist.ProductDao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ProvenSoft
 */
public class Tester {

    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    public Tester() {
        categoryDao = new CategoryDao();
        productDao = new ProductDao();
    }

    public static void main( String[] args ) {
        Tester main = new Tester();
        main.runTests();
    }

    private < T > void printList( List< T > data ) {
        if ( data != null ) {
            data.forEach( System.out::println );
        }
    }


    private void runTests() {
        testRetrieveAllCategories();
        testRetrieveCategoryByCodeExists();
        testRetrieveCategoryByCodeNotExists();
        //testInsertCategoryNotExists();
        //testInsertCategoryExists();
        testRetrieveAllProducts();
    }

    private void testRetrieveAllCategories() {
        try {
            System.out.println( "Test retrieve all categories" );
            List< Category > allCategories = categoryDao.selectAll();
            printList( allCategories );
        } catch ( StoreDalException ex ) {
            Logger.getLogger( Tester.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }

    private void testRetrieveCategoryByCodeExists() {
        System.out.println( "Test retrieve category by code (exists)" );
        String code = "C03";
        Category category = categoryDao.selectWhereCode( code );
        System.out.println( category );
    }

    private void testRetrieveCategoryByCodeNotExists() {
        System.out.println( "Test retrieve category by code (not exists)" );
        String code = "C99";
        Category category = categoryDao.selectWhereCode( code );
        if ( category == null ) {
            System.out.println( "Ok: not found" );
        } else {
            System.out.println( "Test fail" );
        }
    }

    private void testInsertCategoryNotExists() {
        System.out.println( "Test insert category (not exists)" );
        Category c = new Category( 0, "C07", "category07" );
        int numRows = categoryDao.insert( c );
        String msg = ( numRows == 1 ) ? "Ok (inserted)" : "Fail (not inserted)";
        System.out.println( msg );
    }

    private void testInsertCategoryExists() {
        System.out.println( "Test insert category (exists)" );
        Category c = new Category( 0, "C02", "category08" );
        int numRows = categoryDao.insert( c );
        String msg = ( numRows == 0 ) ? "Ok (not inserted)" : "Fail (inserted)";
        System.out.println( msg );
    }

    private void testRetrieveAllProducts() {
        System.out.println( "Test retrieve all products" );
        List< Product > allProducts = productDao.selectAll();
        printList( allProducts );
    }

}

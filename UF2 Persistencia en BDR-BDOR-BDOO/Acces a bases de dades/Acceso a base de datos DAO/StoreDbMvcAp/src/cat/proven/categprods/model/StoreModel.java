package cat.proven.categprods.model;

import cat.proven.categprods.utils.StoreDalException;
import cat.proven.categprods.model.persist.CategoryDao;
import cat.proven.categprods.model.persist.ProductDao;

import java.util.List;

/**
 * @author ProvenSoft
 */
public class StoreModel {
    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    public StoreModel() {
        this.categoryDao = new CategoryDao();
        this.productDao = new ProductDao();
    }

    // ===== CATEGORIES METHODS ===== //

    /**
     * finds all categories in database
     *
     * @return list with all categories or null in case of error
     * @throws StoreDalException in case of error connecting to database
     */
    public List< Category > findAllCategories() throws StoreDalException {
        return categoryDao.selectAll();
    }

    /**
     * adds a new category to database
     * it prevents adding null categories or null codes
     * also prevents code duplicates
     *
     * @param category the category to add
     * @return 1 if successful, 0 otherwise.
     */
    public int addCategory( Category category ) {
        int result = 0;
        if ( category != null && category.getCode() != null ) {
            Category c = categoryDao.selectWhereCode( category.getCode() );
            if ( c == null ) {
                result = categoryDao.insert( category );
            }
        }
        return result;
    }

    /**
     * A method that finds a Category object based on the provided code.
     *
     * @param code The code of the Category object to find.
     * @return The Category object found, or null if not found.
     */
    public Category findCategoryByCode( String code ) {
        Category result = null;
        if ( code != null ) {
            result = categoryDao.selectWhereCode( code );
        }
        return result;
    }

    /**
     * Modifies the category by updating it with a new category.
     *
     * @param oldCategory the old category to be modified
     * @param newCategory the new category to replace the old category
     * @return the number of modifications made to the category
     */
    public int modifyCategory( Category oldCategory, Category newCategory ) {
        int result = 0;
        if ( oldCategory != null && newCategory != null ) {
            result = categoryDao.update( oldCategory, newCategory );
        }
        return result;
    }

    /**
     * Removes the specified category from the system.
     *
     * @param category the category to be removed
     * @return the number of categories removed (0 or 1)
     */
    public int removeCategory( Category category ) {
        int result = 0;
        if ( category != null && category.getCode() != null ) {
            result = categoryDao.delete( category );
        }
        return result;
    }

    // ===== PRODUCTS METHODS ===== //

    /**
     * finds all products in database
     *
     * @return list with all categories or null in case of error
     * @throws StoreDalException in case of error connecting to database
     */
    public List< Product > findAllProducts() throws StoreDalException {
        return productDao.selectAll();
    }

    /**
     * finds a product given its code
     *
     * @param code the code to search
     * @return the product with given code or null if not found or in case of error
     */
    public Product findProductByCode( String code ) {
        Product result = null;
        if ( code != null ) {
            result = productDao.selectWhereCode( code );
        }
        return result;
    }

    /**
     * Finds products by name.
     *
     * @param name the name of the product to search for
     * @return the list of products with matching names, or null if the name is null
     * @throws StoreDalException if there is an error in the data access layer
     */
    public List< Product > findProductsByName( String name ) throws StoreDalException {
        List< Product > result = null;
        if ( name != null ) {
            result = productDao.selectLikeName( name );
        }
        return result;
    }

    /**
     * Finds products based on the minimum stock value.
     *
     * @param minStock the minimum stock value to search for products
     * @return the list of products found
     */
    public List< Product > findProductsByStockMin( int minStock ) {
        List< Product > result = null;
        result = productDao.selectWhereMinStock( minStock );
        return result;
    }

    /**
     * Adds a product to the database if the product code is not already present.
     *
     * @param product the product to be added
     * @return the result of the insertion operation (0 if not inserted, 1 if inserted)
     */
    public int addProduct( Product product ) {
        int result = 0;
        if ( product != null && product.getCode() != null ) {
            Product p = productDao.selectWhereCode( product.getCode() );
            if ( p == null ) {
                result = productDao.insert( product );
            }
        }
        return result;
    }

    /**
     * Modify a product in the database.
     *
     * @param oldProduct the product to be modified
     * @param newProduct the modified product
     * @return the number of rows affected by the modification
     */
    public int modifyProduct( Product oldProduct, Product newProduct ) {
        int result = 0;
        if ( oldProduct != null && newProduct != null ) {
            result = productDao.update( oldProduct, newProduct );
        }
        return result;
    }

    /**
     * Removes the specified product from the system.
     *
     * @param product the product to be removed
     * @return the number of products removed (0 if no product was removed)
     */
    public int removeProduct( Product product ) {
        int result = 0;
        if ( product != null && product.getCode() != null ) {
            result = productDao.delete( product );
        }
        return result;
    }

}

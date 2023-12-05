package cat.proven.categprods.model.persist;

import cat.proven.categprods.utils.StoreDalException;
import cat.proven.categprods.model.Category;
import cat.proven.categprods.model.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for product
 *
 * @author ProvenSoft
 */
public class ProductDao {
    private final DbConnect dbConnect;
    private final Map< String, String > queries;

    public ProductDao() {
        this.dbConnect = new DbConnect();
        this.queries = new HashMap<>();
        initQueries();
    }

    /**
     * Converts a ResultSet object into a Product object.
     *
     * @param rs the ResultSet object containing the data to be converted
     * @return the Product object created from the ResultSet
     * @throws SQLException if there is an error retrieving data from the ResultSet
     */
    private Product fromResultSet( ResultSet rs ) throws SQLException {
        Product prod;
        long id = rs.getLong( "id" );
        String code = rs.getString( "code" );
        String name = rs.getString( "name" );
        int stock = rs.getInt( "stock" );
        double price = rs.getDouble( "price" );
        long categoryId = rs.getLong( "category_id" );
        prod = new Product( id, code, name, stock, price, new Category( categoryId ) );
        return prod;
    }

    /**
     * Retrieves a product from the database based on the given product object.
     *
     * @param product the product object containing the ID of the product to be retrieved
     * @return the retrieved product from the database, or null if not found
     */
    public Product select( Product product ) {
        Product prod = null;
        //get a connection and perform query
        try ( Connection conn = dbConnect.getConnection() ) {
            String query = getQuery( "select" );
            PreparedStatement st = conn.prepareStatement( query );
            st.setLong( 1, product.getId() );
            ResultSet rs = st.executeQuery();
            if ( rs.next() ) {
                prod = fromResultSet( rs );
            }
        } catch ( SQLException ex ) {
            Logger.getLogger( this.getClass().getName() ).log( Level.SEVERE, null, ex );
        }
        return prod;
    }

    /**
     * Retrieves all products from the database.
     *
     * @return A list of Product objects representing all products in the database.
     */
    public List< Product > selectAll() {
        List< Product > result = new ArrayList<>();
        try ( Connection conn = dbConnect.getConnection() ) {
            String query = getQuery( "sAll" );
            //execute query
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery( query );
            //fetch data
            while ( rs.next() ) {
                Product prod = fromResultSet( rs );
                result.add( prod );
            }
        } catch ( SQLException ex ) {
            Logger.getLogger( CategoryDao.class.getName() ).log( Level.SEVERE, null, ex );
        }
        return result;
    }

    /**
     * Selects products with names similar to the provided name.
     *
     * @param name the name to search for
     * @return a list of products with names similar to the provided name
     * @throws StoreDalException if there is an error in the data access layer
     */
    public List< Product > selectLikeName( String name ) throws StoreDalException {
        List< Product > result = new ArrayList<>();
        try ( Connection conn = dbConnect.getConnection() ) {
            String query = getQuery( "sName" );
            PreparedStatement st = conn.prepareStatement( query );
            st.setString( 1, "%" + name + "%" );
            ResultSet rs = st.executeQuery();
            while ( rs.next() ) {
                Product prod = fromResultSet( rs );
                result.add( prod );
            }
        } catch ( SQLException ex ) {
            Logger.getLogger( this.getClass().getName() ).log( Level.SEVERE, null, ex );
            throw new StoreDalException( "No connection to database", -10 );
        }
        return result;
    }

    /**
     * Retrieves a product from the database based on the given code.
     *
     * @param code the code of the product to retrieve
     * @return the retrieved product, or null if not found
     */
    public Product selectWhereCode( String code ) {
        Product prod = null;
        //get a connection and perform query
        try ( Connection conn = dbConnect.getConnection() ) {
            String query = getQuery( "sCode" );
            PreparedStatement st = conn.prepareStatement( query );
            st.setString( 1, code );
            ResultSet rs = st.executeQuery();
            if ( rs.next() ) {
                prod = fromResultSet( rs );
            }
        } catch ( SQLException ex ) {
            Logger.getLogger( this.getClass().getName() ).log( Level.SEVERE, null, ex );
        }
        return prod;
    }

    /**
     * Selects and returns a list of products based on the given category.
     *
     * @param category the category object used to filter the products
     * @return the list of products that match the given category
     */
    public List< Product > selectWhereCategory( Category category ) {
        List< Product > result = new ArrayList<>();
        //get a connection and perform query
        try ( Connection conn = dbConnect.getConnection() ) {
            String query = getQuery( "sCategory" );
            PreparedStatement st = conn.prepareStatement( query );
            st.setLong( 1, category.getId() );
            ResultSet rs = st.executeQuery();
            while ( rs.next() ) {
                Product prod = fromResultSet( rs );
                result.add( prod );
            }
        } catch ( SQLException ex ) {
            Logger.getLogger( this.getClass().getName() ).log( Level.SEVERE, null, ex );
        }
        return result;
    }

    /**
     * Retrieves a list of products where the stock is greater than or equal to the specified minimum stock level.
     *
     * @param minStock the minimum stock level to filter the products by
     * @return a list of products that meet the minimum stock level criteria
     */
    public List< Product > selectWhereMinStock( int minStock ) {
        List< Product > result = new ArrayList<>();
        //get a connection and perform query
        try ( Connection conn = dbConnect.getConnection() ) {
            String query = getQuery( "sMinStock" );
            PreparedStatement st = conn.prepareStatement( query );
            st.setInt( 1, minStock );
            ResultSet rs = st.executeQuery();
            while ( rs.next() ) {
                Product prod = fromResultSet( rs );
                result.add( prod );
            }
        } catch ( SQLException ex ) {
            Logger.getLogger( this.getClass().getName() ).log( Level.SEVERE, null, ex );
        }
        return result;
    }

    /**
     * Inserts a product into the database.
     *
     * @param product the product to be inserted
     * @return the number of rows affected by the insert operation
     */
    public int insert( Product product ) {
        int result = 0;
        //get a connection and perform query
        try ( Connection conn = dbConnect.getConnection() ) {
            String query = getQuery( "insert" );
            PreparedStatement st = conn.prepareStatement( query );
            st.setString( 1, product.getCode() );
            st.setString( 2, product.getName() );
            st.setInt( 3, product.getStock() );
            st.setDouble( 4, product.getPrice() );
            st.setLong( 5, product.getCategory().getId() );
            result = st.executeUpdate();
        } catch ( SQLException ex ) {
            Logger.getLogger( this.getClass().getName() ).log( Level.SEVERE, null, ex );
        }
        return result;
    }

    /**
     * Updates a product in the database.
     *
     * @param actualProduct  the actual product to be updated
     * @param updatedProduct the updated product with new information
     * @return the number of rows affected by the update
     */
    public int update( Product actualProduct, Product updatedProduct ) {
        int result = 0;
        //get a connection and perform query
        try ( Connection conn = dbConnect.getConnection() ) {
            String query = getQuery( "update" );
            PreparedStatement st = conn.prepareStatement( query );
            st.setString( 1, updatedProduct.getCode() );
            st.setString( 2, updatedProduct.getName() );
            st.setInt( 3, updatedProduct.getStock() );
            st.setDouble( 4, updatedProduct.getPrice() );
            st.setLong( 5, updatedProduct.getCategory().getId() );
            st.setLong( 6, actualProduct.getId() );
            result = st.executeUpdate();
        } catch ( SQLException ex ) {
            Logger.getLogger( this.getClass().getName() ).log( Level.SEVERE, null, ex );
            result = 0;
        }
        return result;
    }

    /**
     * Deletes a product from the database.
     *
     * @param product the product to be deleted
     * @return the number of rows affected by the delete operation
     */
    public int delete( Product product ) {
        int result = 0;
        //get a connection and perform query
        try ( Connection conn = dbConnect.getConnection() ) {
            String query = getQuery( "delete" );
            PreparedStatement st = conn.prepareStatement( query );
            st.setLong( 1, product.getId() );
            result = st.executeUpdate();
        } catch ( SQLException ex ) {
            Logger.getLogger( this.getClass().getName() ).log( Level.SEVERE, null, ex );
            result = 0;
        }
        return result;
    }

    /**
     * Initializes the queries for the class.
     */
    private void initQueries() {
        queries.put( "select", "select * from products where id=?" );
        queries.put( "sAll", "select * from products" );
        queries.put( "sName", "select * from products where name like ?" );
        queries.put( "sCode", "select * from products where code=?" );
        queries.put( "sCategory", "select * from products where category_id=?" );
        queries.put( "sMinStock", "select * from products where stock<=?" );
        queries.put( "insert", "insert into products values (null, ?, ?, ?, ?, ?)" );
        queries.put( "update", """
                update products set 
                code=?, name=?, stock=?, price=?, category_id=?
                where id=?
                """ );
        queries.put( "delete", "delete from products where id=?" );
    }

    /**
     * Retrieves the value associated with the given query key from the queries map.
     *
     * @param queryKey the key used to retrieve the value from the queries map
     * @return the value associated with the given query key, or null if the key is not present
     */
    private String getQuery( String queryKey ) {
        return queries.get( queryKey );
    }
}

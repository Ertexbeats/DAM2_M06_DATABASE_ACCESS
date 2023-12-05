package cat.proven.categprods.model.persist;

import cat.proven.categprods.utils.StoreDalException;
import cat.proven.categprods.model.Category;
import cat.proven.categprods.utils.ErrorCode;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object for category
 *
 * @author ProvenSoft
 */
public class CategoryDao {
    private final DbConnect dbConnect;
    private Map< String, String > queries;

    public CategoryDao() {
        this.dbConnect = new DbConnect();
        this.queries = new HashMap<>();
        initQueries();
    }

    /**
     * builds a category with data in current row of resultset
     *
     * @param rs the resultset to fetch data from
     * @return a category object with data in current row or null in case of error
     */
    private Category fromResultSet( ResultSet rs ) throws SQLException {
        Category cat;
        //read data from fields
        long id = rs.getLong( "id" );
        String code = rs.getString( "code" );
        String name = rs.getString( "name" );
        //instantiate a category object
        cat = new Category( id, code, name );
        return cat;
    }

    /**
     * fetch all categories from database
     *
     * @return list with all categories or null in case of error
     */
    public List< Category > selectAll() throws StoreDalException {
        List< Category > result = new ArrayList<>();
        try ( Connection conn = dbConnect.getConnection() ) {
            String query = getQuery( "sAll" );
            //execute query
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery( query );
            //fetch data
            while ( rs.next() ) {
                Category cat = fromResultSet( rs );
                result.add( cat );
            }
        } catch ( SQLException ex ) {
            Logger.getLogger( CategoryDao.class.getName() ).log( Level.SEVERE, null, ex );
            result = null;
            throw new StoreDalException( "No connection", ErrorCode.DB_NO_CONNECTION.code() );
        }
        return result;
    }

    /**
     * Selects a category from the database based on the given category object.
     *
     * @param category The category object to select from the database.
     * @return The selected category object from the database, or null if not found.
     */
    public Category select( Category category ) {
        Category cat = null;
        //get a connection and perform query
        try ( Connection conn = dbConnect.getConnection() ) {
            String query = getQuery( "select" );
            PreparedStatement st = conn.prepareStatement( query );
            st.setLong( 1, category.getId() );
            ResultSet rs = st.executeQuery();
            if ( rs.next() ) {
                cat = fromResultSet( rs );
            }
        } catch ( SQLException ex ) {
            Logger.getLogger( this.getClass().getName() ).log( Level.SEVERE, null, ex );
        }
        return cat;
    }

    /**
     * fetches category with given code
     *
     * @param code the code to search
     * @return category object with code being searched or null if not found or in case or error
     */
    public Category selectWhereCode( String code ) {
        Category result;
        try ( Connection conn = dbConnect.getConnection() ) {
            String query = getQuery( "sCode" );
            PreparedStatement st = conn.prepareStatement( query );
            st.setString( 1, code );
            ResultSet rs = st.executeQuery();
            if ( rs.next() ) {
                result = fromResultSet( rs );
            } else {  //not found
                result = null;
            }
        } catch ( SQLException ex ) {
            Logger.getLogger( CategoryDao.class.getName() ).log( Level.SEVERE, null, ex );
            result = null;
        }
        return result;
    }

    /**
     * inserts a new category in the database
     *
     * @param category the category to insert
     * @return number of rows inserted
     */
    public int insert( Category category ) {
        int result = 0;
        try ( Connection conn = dbConnect.getConnection() ) {
            String query = getQuery( "insert" );
            PreparedStatement st = conn.prepareStatement( query );
            st.setString( 1, category.getCode() );
            st.setString( 2, category.getName() );
            result = st.executeUpdate();
        } catch ( SQLException ex ) {
            Logger.getLogger( CategoryDao.class.getName() ).log( Level.SEVERE, null, ex );
            result = 0;
        }
        return result;
    }

    /**
     * Updates a category in the database.
     *
     * @param actualCategory  the actual category to be updated
     * @param updatedCategory the updated category object
     * @return the number of rows affected by the update
     */
    public int update( Category actualCategory, Category updatedCategory ) {
        int result = 0;
        //get a connection and perform query
        try ( Connection conn = dbConnect.getConnection() ) {
            String query = getQuery( "update" );
            PreparedStatement st = conn.prepareStatement( query );
            st.setString( 1, updatedCategory.getCode() );
            st.setString( 2, updatedCategory.getName() );
            st.setLong( 3, actualCategory.getId() );
            result = st.executeUpdate();
        } catch ( SQLException ex ) {
            Logger.getLogger( this.getClass().getName() ).log( Level.SEVERE, null, ex );
        }
        return result;
    }

    /**
     * Deletes a category from the database.
     *
     * @param category the category to be deleted
     * @return the number of rows affected by the delete operation
     */
    public int delete( Category category ) {
        int result = 0;
        try ( Connection conn = dbConnect.getConnection() ) {
            String query = getQuery( "delete" );
            PreparedStatement st = conn.prepareStatement( query );
            st.setLong( 1, category.getId() );
            result = st.executeUpdate();
        } catch ( SQLException ex ) {
            Logger.getLogger( CategoryDao.class.getName() ).log( Level.SEVERE, null, ex );
            result = 0;
        }
        return result;
    }

    /**
     * Initializes the queries for the categories table.
     */
    private void initQueries() {
        queries.put( "sAll", "select * from categories" );
        queries.put( "select", "select * from categories where id=?" );
        queries.put( "sCode", "select * from categories where code = ?" );
        queries.put( "insert", "insert into categories values (null, ?, ?)" );
        queries.put( "update", "update categories set code=?, name=?, where id=?" );
        queries.put( "delete", "delete from categories where id=?" );
    }

    /**
     * Retrieves the value associated with the given query key from the queries map.
     *
     * @param queryKey the key of the query
     * @return the value associated with the query key, or null if the key is not found
     */
    private String getQuery( String queryKey ) {
        return queries.get( queryKey );
    }
}

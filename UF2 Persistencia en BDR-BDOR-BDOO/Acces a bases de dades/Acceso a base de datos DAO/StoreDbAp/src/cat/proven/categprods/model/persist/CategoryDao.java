package cat.proven.categprods.model.persist;

import cat.proven.categprods.exceptions.StoreDalException;
import cat.proven.categprods.model.Category;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

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
            //TODO check that conn is not null!
            String query = getQuery( "sAll" );
            //execute query
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery( query );
            //fetch data
            while ( rs.next() ) {
                Category cat = fromResultSet( rs );
                if ( cat != null ) {
                    result.add( cat );
                }
            }
        } catch ( SQLException ex ) {
            //Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            result = null;
            throw new StoreDalException( "No connection", -10 );
        }
        return result;
    }

    public Category select( Category category ) {
        Category cat = null;
        //get a connection and perform query
        try ( Connection conn = dbConnect.getConnection() ) {
            String query = "select * from categories where id=?";
            PreparedStatement st = conn.prepareStatement( query );
            st.setLong( 1, category.getId() );
            ResultSet rs = st.executeQuery();
            if ( rs.next() ) {
                cat = fromResultSet( rs );

            } else {
                cat = null;
            }
        } catch ( SQLException ex ) {
            //Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
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
            String query = "select * from categories where code = ?";
            PreparedStatement st = conn.prepareStatement( query );
            st.setString( 1, code );
            ResultSet rs = st.executeQuery();
            if ( rs.next() ) {
                result = fromResultSet( rs );
            } else {  //not found
                result = null;
            }
        } catch ( SQLException ex ) {
            //Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
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
            String query = "insert into categories values (null, ?, ?)";
            PreparedStatement st = conn.prepareStatement( query );
            st.setString( 1, category.getCode() );
            st.setString( 2, category.getName() );
            result = st.executeUpdate();
        } catch ( SQLException ex ) {
            //Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            result = 0;
        }
        return result;
    }

    public int update( Category actualCategory, Category updatedCategory ) {
        int result = 0;
        //get a connection and perform query
        try ( Connection conn = dbConnect.getConnection() ) {
            String query = """
                    update categories set 
                    code=?, name=?  
                    where id=?
                    """;
            PreparedStatement st = conn.prepareStatement( query );
            st.setString( 1, updatedCategory.getCode() );
            st.setString( 2, updatedCategory.getName() );
            st.setLong( 3, actualCategory.getId() );
            result = st.executeUpdate();
        } catch ( SQLException ex ) {
            //Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int delete( Category category ) {
        int result = 0;
        try ( Connection conn = dbConnect.getConnection() ) {
            String query = "delete from categories where id=?";
            PreparedStatement st = conn.prepareStatement( query );
            st.setLong( 1, category.getId() );
            result = st.executeUpdate();
        } catch ( SQLException ex ) {
            //Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
            result = 0;
        }
        return result;
    }

    private void initQueries() {
        queries.put( "sAll", "select * from categories" );
        //...TODO add all queries
    }

    private String getQuery( String queryKey ) {
        return queries.get( queryKey );
    }

}

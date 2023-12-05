package org.proven.flights.model.persist;

import org.proven.flights.model.Passenger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DAO for passenger entity.
 *
 * @author ProvenSoft
 */
public class PassengerDao {

    protected final Map< String, String > queries;
    protected final DbConnect dbConnect;
    private final String TABLE_NAME;

    public PassengerDao() throws ClassNotFoundException {
        this.TABLE_NAME = "passengers";
        this.dbConnect = DbConnect.getInstance();
        this.queries = new HashMap<>();
        initQueries();
    }

    /**
     * converts resultset entry to entity object.
     *
     * @param rs resultset to get data from.
     * @return object with data in current position of resultset.
     */
    private Passenger fromResultSet( ResultSet rs ) throws SQLException {
        Passenger p = null;
        long id = rs.getLong( "id" );
        String phone = rs.getString( "phone" );
        boolean minor = rs.getBoolean( "minor" );
        p = new Passenger( id, phone, minor );
        return p;
    }

    /**
     * selects all entities from database.
     *
     * @return list of entities of null in case of error.
     */
    public List< Passenger > selectAll() {
        List< Passenger > result = new ArrayList<>();
        try {
            Connection conn = dbConnect.getConnection();
            if ( conn != null ) {
                String query = queries.get( "sAll" );
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery( query );
                while ( rs.next() ) {
                    Passenger obj = fromResultSet( rs );
                    if ( obj != null ) {
                        result.add( obj );
                    }
                }
            }
        } catch ( SQLException ex ) {
            //Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            result = null;
        }
        return result;
    }

    public Passenger selectWhereId( long passengerId ) {
        Passenger result;
        try ( Connection conn = dbConnect.getConnection() ) {
            if ( conn != null ) {
                String query = queries.get( "sId" );
                PreparedStatement st = conn.prepareStatement( query );
                st.setLong( 1, passengerId );
                ResultSet rs = st.executeQuery();
                if ( rs.next() ) {
                    result = fromResultSet( rs );
                } else {
                    result = null;
                }
            } else {
                result = null;
            }
        } catch ( SQLException ex ) {
            result = null;
        }
        return result;
    }

    /**
     * initiatizes queries to database.
     */
    private void initQueries() {
        queries.put( "sAll", String.format( "select * from %s", TABLE_NAME ) );
        queries.put( "insert", String.format( "insert into %s values (null, ?, ?)", TABLE_NAME ) );
        // TODO
        queries.put( "sId", String.format( "select * from %s where id = ?", TABLE_NAME ) );
    }

}
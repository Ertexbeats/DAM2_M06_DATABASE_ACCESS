package org.proven.flights.model.persist;

import org.proven.flights.model.FlightPassenger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DAO for flight-passsenger relationship persistence.
 *
 * @author ProvenSoft
 */
public class FlightPassengerDao {
    protected final Map< String, String > queries;
    protected final DbConnect dbConnect;
    private final String TABLE_NAME;

    public FlightPassengerDao() throws ClassNotFoundException {
        this.TABLE_NAME = "flightpassenger";
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
    private FlightPassenger fromResultSet( ResultSet rs ) throws SQLException {
        FlightPassenger p = null;
        long flightId = rs.getLong( "flight_id" );
        long passengerId = rs.getLong( "passenger_id" );
        p = new FlightPassenger( flightId, passengerId );
        return p;
    }

    /**
     * selects all entities from database.
     *
     * @return list of entities of null in case of error.
     */
    public List< FlightPassenger > selectAll() {
        List< FlightPassenger > result = new ArrayList<>();
        try {
            Connection conn = dbConnect.getConnection();
            if ( conn != null ) {
                String query = queries.get( "sAll" );
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery( query );
                while ( rs.next() ) {
                    FlightPassenger obj = fromResultSet( rs );
                    if ( obj != null ) {
                        result.add( obj );
                    }
                }
            }
        } catch ( SQLException ex ) {
            result = null;
        }
        return result;
    }

    /**
     * inserts a flightpassenger in database.
     *
     * @param flightpassenger the flightpassenger to insert.
     * @return number of rows affected.
     */
    public int insert( FlightPassenger flightpassenger ) {
        int result = 0;
        try {
            Connection conn = dbConnect.getConnection();
            if ( conn != null ) {
                String query = queries.get( "insert" );
                PreparedStatement st = conn.prepareStatement( query );
                st.setLong( 1, flightpassenger.getFlightId() );
                st.setLong( 2, flightpassenger.getPassengerId() );
                result = st.executeUpdate();
            }
        } catch ( SQLException ex ) {
            result = -1;
        }
        return result;
    }

    public List< FlightPassenger > selectWhereFlightId( long id ) {
        List< FlightPassenger > result = new ArrayList<>();
        try {
            Connection conn = dbConnect.getConnection();
            if ( conn != null ) {
                String query = queries.get( "sWhereFlightId" );
                PreparedStatement st = conn.prepareStatement( query );
                st.setLong( 1, id );
                ResultSet rs = st.executeQuery();
                while ( rs.next() ) {
                    FlightPassenger obj = fromResultSet( rs );
                    if ( obj != null ) {
                        result.add( obj );
                    }
                }
            }
        } catch ( SQLException ex ) {
            result = null;
        }
        return result;
    }

    public List< FlightPassenger > selectWherePassengerId( long id ) {
        List< FlightPassenger > result = new ArrayList<>();
        try {
            Connection conn = dbConnect.getConnection();
            if ( conn != null ) {
                String query = queries.get( "sWherePassengerId" );
                PreparedStatement st = conn.prepareStatement( query );
                st.setLong( 1, id );
                ResultSet rs = st.executeQuery( query );
                while ( rs.next() ) {
                    FlightPassenger obj = fromResultSet( rs );
                    if ( obj != null ) {
                        result.add( obj );
                    }
                }
            }
        } catch ( SQLException ex ) {
            result = null;
        }
        return result;
    }

    private void initQueries() {
        queries.put( "sAll", String.format( "select * from %s", TABLE_NAME ) );
        queries.put( "insert", String.format( "insert into %s values (?, ?)", TABLE_NAME ) );
        queries.put( "sWhereFlightId", String.format( "select * from %s where flight_id = ?", TABLE_NAME ) );
        queries.put( "sWherePassengerId", String.format( "select * from %s where passenger_id = ?", TABLE_NAME ) );
    }
}
package org.proven.flights.model.persist;

import org.proven.flights.model.Flight;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightDao {
    protected final Map< String, String > queries;
    protected final DbConnect dbConnect;
    private final String TABLE_NAME;

    public FlightDao() throws ClassNotFoundException {
        this.TABLE_NAME = "flights";
        this.dbConnect = DbConnect.getInstance();
        this.queries = new HashMap<>();
        initQueries();
    }

    private void initQueries() {
        queries.put( "sAll", String.format( "select * from %s", TABLE_NAME ) );
        queries.put( "insert", String.format( "insert into %s values (?, ?)", TABLE_NAME ) );
    }


    private Flight fromResultSet( ResultSet rs ) throws SQLException {
        Flight f = null;
        long id = rs.getLong( "id" );
        String code = rs.getString( "code" );
        int capacity = rs.getInt( "capacity" );
        LocalDate date = rs.getDate( "date" ).toLocalDate();
        LocalTime time = rs.getTime( "time" ).toLocalTime();
        f = new Flight( id, code, capacity, date, time );
        return f;
    }

    public List< Flight > selectAll() {
        List< Flight > result = new ArrayList<>();
        try ( Connection conn = dbConnect.getConnection() ) {
            if ( conn != null ) {
                String query = queries.get( "sAll" );
                // execute query
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery( query );
                //fetch data
                while ( rs.next() ) {
                    Flight obj = fromResultSet( rs );
                    if ( obj != null ) {
                        result.add( obj );
                    }
                }
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return result;
    }

    public int insert( Flight flight ) {
        return 0;
    }
}

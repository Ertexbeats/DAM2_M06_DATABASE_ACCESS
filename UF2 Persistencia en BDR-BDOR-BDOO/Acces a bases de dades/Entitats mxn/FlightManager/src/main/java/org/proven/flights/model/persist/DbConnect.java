package org.proven.flights.model.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * encapsulates data for database connection.
 *
 * @author ProvenSoft
 */
public final class DbConnect {

    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String PROTOCOL = "jdbc:mysql:";
    static final String HOST = "127.0.0.1";
    static final String BD_NAME = "flightsdb";
    static final String USER = "flightssusr";
    static final String PASSWORD = "flightspwd";
    static final String PARAMS = "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static DbConnect instance;

    DbConnect() throws ClassNotFoundException {
        loadDriver();
    }

    public static DbConnect getInstance() throws ClassNotFoundException {
        if ( instance == null ) {
            instance = new DbConnect();
        }
        return instance;
    }

    public void loadDriver() throws ClassNotFoundException {
        Class.forName( DRIVER );
    }

    /**
     * gets and returns a connection to database
     *
     * @return connection
     * @throws java.sql.SQLException
     */
    public Connection getConnection() throws SQLException {
        final String BD_URL = String.format( "%s//%s/%s?%s", PROTOCOL, HOST, BD_NAME, PARAMS );
        Connection conn = null;
        conn = DriverManager.getConnection( BD_URL, USER, PASSWORD );
        return conn;
    }
}

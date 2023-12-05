package cat.proven.categprods.model.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * encapsulates data for database connection.
 *
 * @author ProvenSoft
 */
public final class DbConnect {

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String PROTOCOL = "jdbc:mysql:";
    public static final String HOST = "127.0.0.1";
    public static final String BD_NAME = "storedb";
    public static final String USER = "storeusr";
    public static final String PASSWORD = "storepsw";
    public static String BD_URL;
    
    public static void loadDriver() throws ClassNotFoundException {
        //getConnectionProperties(); better if connection properties are read from a configuration file
        Class.forName(DRIVER);
        BD_URL = String.format("%s//%s/%s", PROTOCOL, HOST, BD_NAME);
    }
    
    /**
     * gets and returns a connection to database
     *
     * @return connection
     * @throws java.sql.SQLException
     */
    public Connection getConnection() throws SQLException {
        BD_URL = String.format("%s//%s/%s", PROTOCOL, HOST, BD_NAME);
        Connection conn;
        conn = DriverManager.getConnection(BD_URL, USER, PASSWORD);
        return conn;
    }
}
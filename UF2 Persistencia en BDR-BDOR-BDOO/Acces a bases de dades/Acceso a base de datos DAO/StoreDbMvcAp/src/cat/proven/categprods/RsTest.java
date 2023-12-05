package cat.proven.categprods;

import javax.sql.rowset.*;
import javax.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

import cat.proven.categprods.model.persist.DbConnect;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ProvenSoft
 */
public class RsTest {

    private CachedRowSet rowSet1;
    private CachedRowSet rowSet2;
    private JoinRowSet joinRowSet;

    public static void main( String[] args ) throws ClassNotFoundException {
        RsTest main = new RsTest();
        main.start();
    }

    private void start() throws ClassNotFoundException {
        try {
            DbConnect.loadDriver();
            rowSet1 = RowSetProvider.newFactory().createCachedRowSet();
            rowSet1.setUsername( DbConnect.USER );
            rowSet1.setPassword( DbConnect.PASSWORD );
            rowSet1.setUrl( DbConnect.BD_URL );
            //String query = "select c.name, p.id as prod_id from categories as c inner join products as p on p.category_id = c.id where c.id= 2";
            String query1 = "select * from categories where id = 2";
            rowSet1.setCommand( query1 );
            rowSet1.execute();
            //
            rowSet2 = RowSetProvider.newFactory().createCachedRowSet();
            rowSet2.setUsername( DbConnect.USER );
            rowSet2.setPassword( DbConnect.PASSWORD );
            rowSet2.setUrl( DbConnect.BD_URL );
            String query2 = "select * from products where category_id = 2";
            rowSet2.setCommand( query2 );
            rowSet2.execute();
            //
            joinRowSet = RowSetProvider.newFactory().createJoinRowSet();
            joinRowSet.setUsername( DbConnect.USER );
            joinRowSet.setPassword( DbConnect.PASSWORD );
            joinRowSet.setUrl( DbConnect.BD_URL );
            joinRowSet.addRowSet( rowSet1, "id" );
            joinRowSet.addRowSet( rowSet2, "category_id" );
            showRowSet( joinRowSet );
            joinRowSet.first();
            changeRs( joinRowSet );
            commitToDatabase( joinRowSet );
        } catch ( SQLException ex ) {
            Logger.getLogger( RsTest.class.getName() ).log( Level.SEVERE, null, ex );
        }

    }

    public void showRowSet( CachedRowSet rs ) throws SQLException {
        RowSetMetaData rsmd = ( RowSetMetaData ) rs.getMetaData();
        for ( int i = 1; i <= rsmd.getColumnCount(); i++ ) {
            System.out.println( rsmd.getColumnName( i ) );
        }
        StringBuilder sb = new StringBuilder();
        while ( rs.next() ) {
            sb.append( "[" );
            for ( int i = 0; i < rsmd.getColumnCount(); i++ ) {
                sb.append( "(" ).append( rs.getObject( i + 1 ) ).append( ")" );
            }
            sb.append( "]\n" );
        }
        System.out.println( sb.toString() );
    }

    private void changeRs( JoinRowSet rs ) throws SQLException {
        rs.updateString( "name", "nova" );
        rs.updateRow();
    }

    public boolean commitToDatabase( CachedRowSet rs ) {
        boolean b;
        try ( Connection conn = getConnection( rs, false ) ) {
            // propagate changes and close connection
            rs.acceptChanges( conn );
            // reload data.
            rs.execute();
            b = true;
        } catch ( SQLException se ) {
            Logger.getLogger( getClass().getName() ).log( Level.SEVERE, null, se );
            b = false;
        } catch ( Exception e ) {
            Logger.getLogger( getClass().getName() ).log( Level.SEVERE, null, e );
            b = false;
        }
        return b;
    }

    public Connection getConnection( RowSet rs, boolean autocommit ) throws SQLException {
        Connection conn = DriverManager.getConnection( rs.getUrl(), rs.getUsername(), rs.getPassword() );
        conn.setAutoCommit( autocommit );
        return conn;
    }

}

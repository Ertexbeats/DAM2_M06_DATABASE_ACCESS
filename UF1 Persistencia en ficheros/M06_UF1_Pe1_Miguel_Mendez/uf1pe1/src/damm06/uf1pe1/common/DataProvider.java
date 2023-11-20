package damm06.uf1pe1.common;

import damm06.uf1pe1.persist.DOMOfficePersist;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Miguel Mendez
 */
public class DataProvider {

    private static final int MAX_SEATS = 10;
    private static final int MAX_PHONES = 4;
    private static final int MAX_WS = 6;
    private static final int NUM_OFFICES = 5;
    private static int ofCount = 0;
    private static int wsCount = 0;

    /**
     * generates test data for a list of offices
     *
     * @return list of offices
     */
    public static List< Office > generateOfficeList() {
        List< Office > offices = new ArrayList<>();
        for ( int i = 0; i < NUM_OFFICES; i++ ) {
            offices.add( generateOffice() );
        }
        return offices;
    }

    /**
     * generates test data for a workspace
     *
     * @return a workspace
     */
    private static Workspace generateWorkspace() {
        Random rnd = new Random();
        Workspace workspace;
        String code = String.format( "code%02d", wsCount );
        int seats = rnd.nextInt( MAX_SEATS + 1 );
        int numPhones = rnd.nextInt( MAX_PHONES + 1 );
        List< String > phones = new ArrayList<>();
        for ( int i = 0; i < numPhones; i++ ) {
            phones.add( String.format( "93555%02d%02d", wsCount, i ) );
        }
        boolean enclosed = ( rnd.nextInt( 2 ) == 1 );
        workspace = new Workspace( code, seats, phones, enclosed );
        wsCount++;
        return workspace;
    }

    /**
     * generates test data for an office
     *
     * @return an office
     */
    private static Office generateOffice() {
        Random rnd = new Random();
        Office office;
        String name = String.format( "name%02d", ofCount );
        int numWorkspaces = rnd.nextInt( MAX_WS + 1 );
        List< Workspace > ws = new ArrayList<>();
        for ( int i = 0; i < numWorkspaces; i++ ) {
            ws.add( generateWorkspace() );
        }
        office = new Office( name, ws );
        ofCount++;
        return office;
    }

}

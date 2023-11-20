package damm06.uf1pe1.persist;

import damm06.uf1pe1.common.Office;
import damm06.uf1pe1.common.Workspace;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Miguel Mendez
 */
public class OfficeObjectPersist {

    /**
     * saves to file a list of offices
     *
     * @param filename the path to file to write
     * @param offices  the offices to write
     * @return the number of offices actually written to file
     * @throws //TODO add throws to exceptions
     */
    public int saveOfficesToFile( String filename, List< Office > offices ) throws IOException {
        int counter = 0;
        //TODO
        try ( ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( filename ) ) ) {
            for ( Office off : offices ) {
                List< Workspace > work = off.getWorkspaces();
                for ( Workspace w : work ) {
                    if ( w.isEnclosed() ) {
                        oos.writeObject( off );
                        counter++;
                    }
                }
            }
        }
        return counter;
    }

    /**
     * reads from file a list of offices
     *
     * @param filename the path to file to write
     * @return the list of offices read from file
     * @throws //TODO add throws to exceptions
     */
    public List< Office > readOfficesFromFile( String filename ) throws ClassNotFoundException, IOException {
        List< Office > data = new ArrayList<>();
        //TODO
        try ( ObjectInputStream ois = new ObjectInputStream( new FileInputStream( filename ) ) ) {
            while ( true ) {
                try {

                    Object obj = ois.readObject();
                    if ( obj instanceof Office ) {
                        Office off = ( Office ) obj;
                        data.add( off );
                    }
                } catch ( IOException e ) {
                    break;
                }
            }
        }
        return data;
    }

}

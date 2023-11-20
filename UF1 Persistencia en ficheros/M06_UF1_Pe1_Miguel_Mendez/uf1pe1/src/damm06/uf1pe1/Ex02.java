package damm06.uf1pe1;

import damm06.uf1pe1.common.Office;
import damm06.uf1pe1.persist.SAXOfficePersist;

import java.util.List;
import java.util.Scanner;

/**
 * @author Miguel Mendez
 */
public class Ex02 {

    public static void main( String[] args ) throws Exception {
        //TODO add catchs to exceptions
        Scanner input = new Scanner( System.in );
        //
        System.out.print( "File to read: " );
        String filename = input.next();
        //
        SAXOfficePersist persister = new SAXOfficePersist();
        List< Office > officesRead = persister.readOfficesFromXML( filename );
        //
        System.out.println( "**Read data**" );
        officesRead.forEach( System.out::println );
    }

}

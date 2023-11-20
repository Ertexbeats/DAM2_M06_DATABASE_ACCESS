package damm06.uf1pe1;

import damm06.uf1pe1.common.DataProvider;
import damm06.uf1pe1.common.Office;
import damm06.uf1pe1.persist.OfficeObjectPersist;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Miguel Mendez
 */
public class Ex01 {

    public static void main( String[] args ) throws IOException, ClassNotFoundException {
        //TODO add catchs to exceptions
        Scanner input = new Scanner( System.in );
        //
        List< Office > officeList = DataProvider.generateOfficeList();
        System.out.println( "**Initial data**" );
        officeList.forEach( System.out::println );
        //
        System.out.print( "File to write: " );
        String filename = input.next();
        //
        OfficeObjectPersist persister = new OfficeObjectPersist();
        int numOfficesWritten = persister.saveOfficesToFile( filename, officeList );
        //
        System.out.printf( "%d offices writen to file %s%n", numOfficesWritten, filename );
        //
        List< Office > officeListRead = persister.readOfficesFromFile( filename );
        System.out.println( "Offices read: " );
        officeListRead.forEach( System.out::println );
    }

}

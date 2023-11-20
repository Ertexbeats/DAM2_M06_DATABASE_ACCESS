package damm06.uf1pe1;

import damm06.uf1pe1.common.DataProvider;
import damm06.uf1pe1.common.Office;
import damm06.uf1pe1.common.Workspace;
import damm06.uf1pe1.persist.DOMOfficePersist;

import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Miguel Mendez
 */
public class Ex03 {

    public static void main( String[] args ) throws Exception {
        //TODO add catchs to exceptions
        Scanner input = new Scanner( System.in );
        //
        List< Office > officeList = DataProvider.generateOfficeList();
        System.out.println( "**Initial data**" );
        officeList.forEach( System.out::println );
        //
        List< Workspace > filteredWorkspaces = filterNoPhoneWorkspacesInOfficeList( officeList );
        //
        System.out.println( "**Workspaces with no phone**" );
        filteredWorkspaces.forEach( System.out::println );
        //
        System.out.print( "File to write: " );
        String filename = input.next();
        //
        DOMOfficePersist persister = new DOMOfficePersist();
        boolean result = persister.saveWorkspacesToFile( filename, filteredWorkspaces );
        System.out.println( "Workspaces saved?: " + result );
    }

    /**
     * filters workspaces with no phone from list of offices
     *
     * @param offices the office list
     * @return list of workspaces no phone
     */
    private static List< Workspace > filterNoPhoneWorkspacesInOfficeList( List< Office > offices ) {
        List< Workspace > filteredWorkspaces = new ArrayList<>();
        offices.forEach( ( t ) -> {
            filteredWorkspaces.addAll( t.getWorkspaces()
                    .stream()
                    .filter( ( u ) -> u.getPhones().isEmpty() )
                    .collect( Collectors.toList() ) );
        } );
        return filteredWorkspaces;
    }

}

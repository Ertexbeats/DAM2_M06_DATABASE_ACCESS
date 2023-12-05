package org.proven.flights.views;

import org.proven.flights.controllers.MainController;
import org.proven.flights.model.Flight;

import java.util.List;
import java.util.Scanner;

/**
 * Main views for flights application.
 *
 * @author ProvenSoft
 */
public class MainView {
    private final MainController controller;
    private final MainMenu mainMenu;
    private boolean exit; //flag to exit application.

    public MainView( MainController controller ) {
        this.controller = controller;
        this.mainMenu = new MainMenu();
    }

    /**
     * makes the views visible and starts interacting with user.
     */
    public void show() {
        exit = false;
        // control loop for user interaction.
        do {
            mainMenu.show();
            String action = mainMenu.getSelectedOptionActionCommand();
            if ( action != null ) {
                controller.processAction( action );
            }
        } while ( !exit );
    }

    /**
     * displays a message to user.
     */
    public void showMessage( String message ) {
        System.out.println( message );
    }

    /**
     * displays a message and gets user's answer.
     *
     * @param message the message to display.
     * @return the user's answer or null in case of error.
     */
    public String inputString( String message ) {
        System.out.print( message );
        Scanner scan = new Scanner( System.in );
        return scan.next();
    }

    /**
     * activates views closing.
     */
    public void close() {
        this.exit = true;
    }

    /**
     * displays a list.
     *
     * @param data the list to display.
     */
    public void showFlightList( List< Flight > data ) {
        if ( data != null ) {
            for ( Flight elem : data ) {
                System.out.println( elem.toString() );
            }
            System.out.format( "%d elements displayed\n", data.size() );
        } else {
            showMessage( "Null data" );
        }
    }

}

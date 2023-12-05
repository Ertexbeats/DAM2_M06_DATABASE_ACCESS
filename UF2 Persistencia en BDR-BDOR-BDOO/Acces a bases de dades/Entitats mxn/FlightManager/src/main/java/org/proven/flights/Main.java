package org.proven.flights;

import org.proven.flights.controllers.MainController;
import org.proven.flights.model.Model;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class for Flights application.
 *
 * @author ProvenSoft
 */
public class Main {

    public static void main( String[] args ) {
        try {
            Model model = new Model();
            MainController controller = new MainController( model );
            controller.start();
        } catch ( ClassNotFoundException ex ) {
            Logger.getLogger( Main.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }

}
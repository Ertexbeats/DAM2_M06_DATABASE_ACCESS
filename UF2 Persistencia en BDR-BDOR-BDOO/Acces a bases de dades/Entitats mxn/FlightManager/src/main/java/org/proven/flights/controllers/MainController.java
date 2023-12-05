package org.proven.flights.controllers;

import org.proven.flights.model.Flight;
import org.proven.flights.model.Model;
import org.proven.flights.views.MainView;

import java.util.List;

/**
 * Main controllers for flights application.
 *
 * @author ProvenSoft
 */
public class MainController {

    private final Model model;
    private final MainView view;

    public MainController( Model model ) {
        this.model = model;
        this.view = new MainView( this );
    }

    public Model getModel() {
        return model;
    }

    /**
     * starts running controllers.
     */
    public void start() {
        view.show();
    }

    /**
     * processes actions received from views.
     *
     * @param action the action to process.
     */
    public void processAction( String action ) {
        if ( action != null ) {
            switch ( action ) {
                case "exit": //exit application.
                    exitApplication();
                    break;
                case "listallflights": //list all flights.
                    doListAllFlights();
                    break;
                default:
                    view.showMessage( "Unknown option" );
                    break;
            }
        }
    }

    /**
     * exits application.
     */
    private void exitApplication() {
        String answer = view.inputString( "Exit. Are you sure (Y/N): " );
        if ( answer != null && answer.equalsIgnoreCase( "y" ) ) {
            view.close();
        }
    }

    /**
     * lists all flights from the database.
     */
    private void doListAllFlights() {
        //retrieve data from model.
        List< Flight > result = model.searchAllFlights();
        //display data to user.
        if ( result != null ) { //successfull retrieval of data.
            view.showFlightList( result );
        } else { //error retrieving data.
            view.showMessage( "Error retrieving data" );
        }
    }

}
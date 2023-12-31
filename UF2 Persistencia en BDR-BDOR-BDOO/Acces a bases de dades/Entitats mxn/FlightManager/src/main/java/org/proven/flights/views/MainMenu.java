package org.proven.flights.views;

import org.proven.menu.Menu;
import org.proven.menu.Option;

/**
 * Main menu for flights application.
 *
 * @author ProvenSoft
 */
public class MainMenu extends Menu {
    public MainMenu() {
        super( "Flight application main menu" );
        //
        addOption( new Option( "Exit application", "exit" ) );
        //
        addOption( new Option( "List all flights", "listallflights" ) );
        addOption( new Option( "Add flight", "addflight" ) );
        addOption( new Option( "Modify flight", "modifyflight" ) );
        addOption( new Option( "Remove flight", "removeflight" ) );
        //
        addOption( new Option( "List all passengers", "listallpassengers" ) );
        addOption( new Option( "Add passenger", "addpassenger" ) );
        addOption( new Option( "Modify passenger", "modifypassenger" ) );
        addOption( new Option( "Remove passenger", "removepassenger" ) );
        //
        addOption( new Option( "List passengers by flight", "listpassengersbyflight" ) );
        addOption( new Option( "Register passenger to flight", "registerpassengertoflight" ) );
        addOption( new Option( "Unregister passenger from flight", "unregisterpassengerfromflight" ) );
    }
}
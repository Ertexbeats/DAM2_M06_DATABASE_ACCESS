package cat.proven.game;

import cat.proven.game.model.Game;
import cat.proven.game.model.persist.XmlSaxGame;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

/**
 * Program that retrieves data for a game and displays to user
 *
 * @author ProvenSoft
 */

public class GameApp {
    private boolean exit;
    private String gamesXmlFilename = "resources/games.xml";


    public static void main( String[] args ) throws IOException {
        GameApp myApp = new GameApp();
        myApp.run();
    }

    /**
     * Application main method.
     * Show the main menu and execute control methods.
     */
    private void run() throws IOException {
        loadConfig();
        exit = false;
        do {
            int op = printMenu();
            switch ( op ) {
                case 0 -> exit = true;
                case 1 -> retrieveGame();
                default -> {
                }
            }
        } while ( !exit );
    }

    /**
     * prints main menu an gets user's option.
     *
     * @return user's option.
     */
    private int printMenu() {
        final String[] opts = { "Exit", "Retrieve Game" };
        for ( int i = 0; i < opts.length; i++ ) {
            System.out.format( "[%d]. %s\n", i, opts[ i ] );
        }
        Scanner sc = new Scanner( System.in );
        System.out.print( "Select an option: " );
        int option = -1;
        try {
            option = Integer.parseInt( sc.next() );
        } catch ( NumberFormatException e ) {
            option = -1;

        }
        return option;
    }

    /**
     * asks the user to input the game id and
     * displays the game with its players
     */
    private void retrieveGame() {
        Scanner scan = new Scanner( System.in );
        //prompt to user
        System.out.print( "Input game id: " );
        //read game id from user
        String gameId = scan.next();
        //instantiate a xml helper to get game data
        XmlSaxGame xmlSaxGame = new XmlSaxGame();
        xmlSaxGame.setXML_FILENAME( gamesXmlFilename );
        Game found = xmlSaxGame.load( gameId );
        if ( found != null ) {
            displayGame( found );
        } else {
            System.out.format( "Error retrieving game or game id '%s' not found\n", gameId );
        }
    }

    /**
     * displays game info.
     *
     * @param game the game to display
     */
    private void displayGame( Game game ) {
        System.out.println( "Displaying game..." );
        //TODO: display game id and its list of players

        System.out.println( "Game id: " + game.getId() );
        System.out.println( "Players:" );
        for ( int i = 0; i < game.getPlayers().size(); i++ ) {
            System.out.printf( "Player id: %s\n", game.getPlayers().get( i ).getId() );
            System.out.printf( "Player name: %s\n", game.getPlayers().get( i ).getName() );
            System.out.printf( "Player level: %s\n", game.getPlayers().get( i ).getLevel() );
            System.out.printf( "Player score: %s\n", game.getPlayers().get( i ).getScore() );
        }


    }

    private void loadConfig() throws IOException {
        Properties props = new Properties();
        props.load( new FileReader( "/resources/gamesconfig.properties" ) );
        //gamesXmlFilename = props.getProperty( "gamesXmlFilename" );
    }

}

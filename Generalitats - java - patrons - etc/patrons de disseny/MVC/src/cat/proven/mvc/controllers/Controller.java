package cat.proven.mvc.controllers;

import cat.proven.mvc.model.DataService;
import cat.proven.mvc.views.Views;

public class Controller {
    private static Views view;
    private DataService dataService;

    public Controller( DataService dataService ) {
        this.dataService = dataService;
        this.view = new Views( dataService, this );
        view.show();

    }

    public void processAction( String action ) {
        switch ( action ) {
            case "exit":
                doExit();
                break;
            case "greet":
                doGreet();
                break;
            case "farewell":
                farewell();
                break;
            default:
                throw new AssertionError();
        }
    }

    private void doGreet() {
        String user = dataService.getName();
        view.showMessage( "Hello " + user );
    }

    private void farewell() {
        String user = dataService.getName();
        view.showMessage( "Goodbye " + user );
    }

    private void doExit() {
//        System.exit( 0 );
        view.setExit( true );
    }
}

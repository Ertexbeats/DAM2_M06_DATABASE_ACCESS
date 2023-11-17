package cat.proven.mvc;

import cat.proven.mvc.controllers.Controller;
import cat.proven.mvc.model.DataService;

public class Main {
    public static void main( String[] args ) {
        DataService dataService = new DataService();
        dataService.setName( "Miguel" );
        Controller controller = new Controller( dataService );
    }
}

package cat.proven.mvc.views;

import cat.proven.mvc.controllers.Controller;
import cat.proven.mvc.model.DataService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Views {
    private DataService dataService;
    private Controller controller;
    private MvcMenu menu;
    private boolean exit;

    public Views( DataService dataService, Controller controller ) {
        this.dataService = dataService;
        this.controller = controller;
        this.menu = new MvcMenu();
    }

    public void show() {
        exit = false;
        do {
            menu.show();
            String action = menu.getSelectedOptionActionCommand();
            controller.processAction( action );
        } while ( !exit );
    }

    public void showMessage( String message ) {
        System.out.println( message );
    }
}

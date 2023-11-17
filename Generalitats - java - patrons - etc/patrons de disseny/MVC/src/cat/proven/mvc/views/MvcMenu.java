package cat.proven.mvc.views;

public class MvcMenu extends Menu {
    public MvcMenu() {
        title = "MVC example main menu";
        addOption( new Option( "Exit apllication", "exit" ) );
        addOption( new Option( "Saludar", "greet" ) );
        addOption( new Option( "Despedirse", "farewell" ) );
    }
}

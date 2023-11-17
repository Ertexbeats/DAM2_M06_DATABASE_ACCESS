package schooldomappt;

import schooldomappt.controller.Controller;
import schooldomappt.model.Model;

// Miguel Mendez
public class SchoolXmlDomApp {
    public static void main( String[] args ) {
        Model model = new Model();
        Controller controller = new Controller( model );
    }
}

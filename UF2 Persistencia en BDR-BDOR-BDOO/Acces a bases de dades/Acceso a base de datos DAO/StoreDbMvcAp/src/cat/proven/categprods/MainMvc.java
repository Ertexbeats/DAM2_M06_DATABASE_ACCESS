package cat.proven.categprods;

import cat.proven.categprods.controllers.StoreController;
import cat.proven.categprods.model.StoreModel;

/**
 * @author ProvenSoft
 */
public class MainMvc {

    public static void main( String[] args ) {
        //instantiate model
        StoreModel model = new StoreModel();
        //instantiate controller
        StoreController controller = new StoreController( model );
        //start
        controller.start();
    }
}

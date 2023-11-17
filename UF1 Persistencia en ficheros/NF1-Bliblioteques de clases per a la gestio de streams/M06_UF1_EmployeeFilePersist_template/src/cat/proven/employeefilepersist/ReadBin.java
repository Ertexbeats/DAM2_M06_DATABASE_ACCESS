package cat.proven.employeefilepersist;

import cat.proven.employeefilepersist.model.Employee;
import cat.proven.employeefilepersist.model.persist.EmployeeBinFilePersist;
import java.util.List;
import cat.proven.employeefilepersist.model.persist.FilePersistInterface;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ProvenSoft
 */
public class ReadBin {

    public static void main(String[] args) {
        try {
            String filename = "mystaffbin.txt"; //TODO read this from arguments
            //TODO treat all errors.

            FilePersistInterface persister = new EmployeeBinFilePersist();
            List<Employee> data = persister.readList(filename);
            System.out.format("TODO: Show data"); //TODO
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadBin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadBin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

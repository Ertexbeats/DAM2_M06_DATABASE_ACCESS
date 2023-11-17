package cat.proven.employeefilepersist;

import cat.proven.employeefilepersist.model.Employee;
import cat.proven.employeefilepersist.model.Staff;
import cat.proven.employeefilepersist.model.persist.EmployeeObjFilePersist;
import java.util.List;
import cat.proven.employeefilepersist.model.persist.FilePersistInterface;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ProvenSoft
 */
public class WriteObj {

    public static void main(String[] args) {
        String filename = "mystaffobj.txt"; //TODO read this from arguments
        Staff model = new Staff();
        List<Employee> data = model.generateTestData();
        try {
            //TODO treat all errors.
            FilePersistInterface persister = new EmployeeObjFilePersist();
            int written = persister.writeList(filename, data);
            System.out.format("%d employees written\n", written);
        } catch (IOException ex) {
            Logger.getLogger(WriteObj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

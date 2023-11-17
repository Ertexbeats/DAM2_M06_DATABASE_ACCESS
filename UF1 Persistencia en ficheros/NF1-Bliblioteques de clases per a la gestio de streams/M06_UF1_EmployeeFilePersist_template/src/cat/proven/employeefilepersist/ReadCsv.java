package cat.proven.employeefilepersist;

import cat.proven.employeefilepersist.model.Employee;
import cat.proven.employeefilepersist.model.persist.EmployeeCsvFilePersist;
import java.util.List;
import cat.proven.employeefilepersist.model.persist.FilePersistInterface;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ProvenSoft
 */
public class ReadCsv {

    public static void main(String[] args) {
        try {
            String filename = "mystaffcsv.txt"; //TODO read this from arguments
            String delimiter = ":";
            //TODO treat all errors.
            EmployeeCsvFilePersist persister = new EmployeeCsvFilePersist();
            ((EmployeeCsvFilePersist) persister).setDelimiter(delimiter);
            List<Employee> data = persister.readList(filename);
            System.out.format("TODO: Show data"); //TODO
            for (Employee emp : data) {
                System.out.println("DATA"+data);
            }
        } catch (ClassNotFoundException ex) {
            System.out.format("ClassNotFoundException"); //TODO
            Logger.getLogger(ReadCsv.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.format("IOException"); //TODO
            Logger.getLogger(ReadCsv.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

package cat.proven.employeefilepersist.model.persist;

import cat.proven.employeefilepersist.model.Employee;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ProvenSoft
 */
public class EmployeeObjFilePersist implements FilePersistInterface<Employee> {

    @Override
    public int writeList(String filename, List<Employee> data) throws IOException {
        System.out.println("TODO: writeList: Object"); //TODO
        int counter = 0;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Employee p : data) {
                oos.writeObject(p);
                counter++;
            }
        }
        return counter;
    }

    @Override
    public List<Employee> readList(String filename) throws ClassNotFoundException, IOException {
        System.out.println("TODO: readList: Object"); //TODO
        List<Employee> data = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    Object obj = ois.readObject();
                    if (obj instanceof Employee) {
                        Employee p = (Employee) obj;
                        data.add(p);
                    }
                } catch (EOFException e) {
                    break;
                }
            }
        }

        return data;
    }

}

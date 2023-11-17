package cat.proven.employeefilepersist.model.persist;

import cat.proven.employeefilepersist.model.Address;
import cat.proven.employeefilepersist.model.Employee;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * File persistence implementationn for lists of employees in CSV format
 *
 * @author ProvenSoft
 */
public class EmployeeCsvFilePersist implements FilePersistInterface<Employee> {

    /**
     * delimiter to be used in csv format
     */
    private String delimiter = ",";

    /**
     * get delimiter
     *
     * @return delimiter
     */
    public String getDelimiter() {
        return delimiter;
    }

    /**
     * set delimiter
     *
     * @param delimiter new value for delimiter
     */
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * writes a list of employees to a file in binary format
     */
    @Override
    public int writeList(String filename, List<Employee> data) throws IOException {
        System.out.println("TODO: writeList: CSV"); //TODO
        int counter = 0;
        try (PrintStream out = new PrintStream(filename)) {
            for (Employee elem : data) {
                out.println(data);
                counter++;
            }
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return counter;
    }

    /**
     * reads a list of employees from a file in binary format
     */
    @Override
    public List<Employee> readList(String filename) throws ClassNotFoundException, IOException {
        System.out.println("TODO: readList: CSV"); //TODO
        List<Employee> data = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            System.out.println("BufferedReader"); //TODO

            String line;
            while ((line = in.readLine()) != null) {
                String[] parts = line.split(";");
                System.out.println("while" +line); //TODO
                 System.out.println("delimiter" +delimiter); //TODO
                if (parts.length == 6) {
                    System.out.println("length "+parts.length); //TODO
                    
                    String phone = parts[0];
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    boolean senior = Boolean.parseBoolean(parts[3]);
                    double salary = Double.parseDouble(parts[4]);
                    Employee employee = new Employee(phone, name, age, senior, salary);
                    data.add(employee);
                }
            }

        } catch (FileNotFoundException ex) {
             System.out.println("FileNotFoundException"); //TODO
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
             System.out.println("IOException"); //TODO
        }
        return data;
    }

}

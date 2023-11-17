package cat.proven.employeefilepersist.model.persist;

import cat.proven.employeefilepersist.model.Address;
import cat.proven.employeefilepersist.model.Employee;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * File persistence implementationn for lists of employees in binary format
 *
 * @author ProvenSoft
 */
public class EmployeeBinFilePersist implements FilePersistInterface<Employee> {

    /**
     * writes a list of employees to a file in binary format
     */
    @Override
    public int writeList(String filename, List<Employee> data) throws IOException {
        System.out.println("TODO: writeList BIN");; //TODO
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(filename))) {
            for (Employee employee : data) {
                // Escribir cada atributo del empleado en el archivo binario
                out.writeUTF(employee.getPhone());
                out.writeUTF(employee.getName());
                out.writeInt(employee.getAge());
                out.writeBoolean(employee.isSenior());
                out.writeDouble(employee.getSalary());
                // Escribir la dirección
                out.writeUTF(employee.getAddress().getStreetName());
                out.writeInt(employee.getAddress().getStreetNumber());
                out.writeUTF(employee.getAddress().getZipCode());
            }
            return data.size();
        } catch (IOException ex) {
            // Manejar la excepción
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * reads a list of employees from a file in binary format
     */
    @Override
    public List<Employee> readList(String filename) throws ClassNotFoundException, IOException {
        System.out.println("TODO: readList BIN");
        List<Employee> data = new ArrayList<>();
        try (DataInputStream in = new DataInputStream(new FileInputStream(filename))) {
            while (true) {
                // Leer cada atributo del empleado del archivo binario
                String phone = in.readUTF();
                String name = in.readUTF();
                int age = in.readInt();
                boolean senior = in.readBoolean();
                double salary = in.readDouble();
                // Leer la dirección
                String streetName = in.readUTF();
                int streetNumber = in.readInt();
                String zipCode = in.readUTF();
                Address address = new Address(streetName, streetNumber, zipCode);
                Employee employee = new Employee(phone, name, age, senior, salary, address);
                data.add(employee);
            }
        } catch (EOFException e) {
            // EOFException se lanza al llegar al final del archivo, lo cual es normal.
            // No hay más datos que leer.
        } catch (FileNotFoundException ex) {
            // Manejar la excepción
            ex.printStackTrace();
            throw ex;
        } catch (IOException ex) {
            // Manejar la excepción
            ex.printStackTrace();
            throw ex;
        }
        return data;
    }

}

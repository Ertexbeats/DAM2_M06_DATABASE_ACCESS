package cat.proven.employeefilepersist.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ProvenSoft
 */
public class Staff {

    /**
     * generates test data for the application
     *
     * @return list of employees
     */
    public List<Employee> generateTestData() {
        List<Employee> data = new ArrayList<>();
        //List<
        data.add(new Employee("phone001", "name001", 11, false, 1001.0, new Address("Main Street", 123, "12345")));
        data.add(new Employee("phone002", "name002", 12, false, 1001.0, new Address("Oak Avenue", 456, "67890")));
        data.add(new Employee("phone003", "name003", 13, true, 1001.0, new Address("Maple Lane", 789, "54321")));
        data.add(new Employee("phone004", "name004", 14, false, 1001.0, new Address("Elm Street", 101, "98765")));
        data.add(new Employee("phone005", "name005", 15, true, 1001.0, new Address("Cedar Road", 222, "23456")));
        return data;
    }
}

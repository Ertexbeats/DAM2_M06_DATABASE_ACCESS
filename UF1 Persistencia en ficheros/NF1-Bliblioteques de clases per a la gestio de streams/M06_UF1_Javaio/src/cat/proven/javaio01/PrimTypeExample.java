package cat.proven.javaio01;

import cat.proven.javaio01.persist.PrimitiveFileUser;
import cat.proven.javaio01.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Example: save to disk primitive data from list of objects
 *
 * @author Jose
 */
public class PrimTypeExample {

    public static void main(String[] args) {
        //instantiate list of objects
        List<User> users = new ArrayList<>();
        //populate list with objects
        createUsers(users);
        //display list
        System.out.println("Initial data:");
        displayUsers(users);
        //define working file name
        String filename = "users.txt"; //this value should be asked to user
        //instantiate an object that provides persistence in file
        PrimitiveFileUser fs = new PrimitiveFileUser();
        //save part of object data to file
        int written = fs.saveUsersDataToFile(users, filename);
        System.out.format("%d users written to file %s\n", written, filename);
        //read data stored in file
        List<User> users2 = fs.readUsersDataFromFile(filename);
        //display list with read data
        System.out.println("Read data:");
        displayUsers(users2);
    }

    /**
     * create some users and store them in list
     *
     * @param users the list to populate
     */
    private static void createUsers(List<User> data) {
        data.add(new User("Peter", "1234", 21));
        data.add(new User("John", "1235", 22));
        data.add(new User("Martha", "1236", 31));
        data.add(new User("Helen", "1237", 25));
        data.add(new User("Mary", "1238", 19));
    }

    /**
     * displays the list
     *
     * @param users the list to be displayed
     */
    private static void displayUsers(List<User> data) {
        for (User elem : data) {
            System.out.println(elem);
        }
    }

}

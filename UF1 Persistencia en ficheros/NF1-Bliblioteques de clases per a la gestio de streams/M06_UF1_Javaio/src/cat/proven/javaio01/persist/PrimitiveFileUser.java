package cat.proven.javaio01.persist;



import cat.proven.javaio01.model.User;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose
 */
public class PrimitiveFileUser {

    /**
     * saves to file list of users (password is not stored)
     *
     * @param users the list of users
     * @param filename the file name
     * @return the number of elements actually saved
     */
    public int saveUsersDataToFile(List<User> data, String filename) {
        int counter = 0;
        try (
                 DataOutputStream dos = new DataOutputStream(
                        new FileOutputStream(filename, false))) {
            for (User user : data) {
                //write name
                dos.writeUTF(user.getName());
                //write age
                dos.writeInt(user.getAge());
                counter++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return counter;
    }

    /**
     * reads data stored in file
     *
     * @param filename the name of the file to read
     * @return list of users with data read from file
     */
    public List<User> readUsersDataFromFile(String filename) {
        List<User> data = new ArrayList<>();
        try (
                 DataInputStream dis = new DataInputStream(
                        new FileInputStream(filename)
                )) {
            while (dis.available() > 0) {
                //read name
                String name = dis.readUTF();
                //read age
                int age = dis.readInt();
                //
                User u = new User(name, "", age);
                data.add(u);
            }
        } catch (EOFException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
}

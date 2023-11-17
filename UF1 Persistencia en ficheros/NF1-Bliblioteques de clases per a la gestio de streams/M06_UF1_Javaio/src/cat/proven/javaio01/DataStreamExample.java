package cat.proven.javaio01;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Example of DataInputStream and DataOutputStream.
 *
 * @author Jose
 */
public class DataStreamExample {

    public static void main(String[] args) {
        // write information to file.
        writeInfo();
        // read information from file.
        readAndShowInfo();
        // write array to file
        writeList();
        // read array from file
        readAndShowList();
    }

    /**
     * write some stuff to file
     */
    private static void writeInfo() {
        //some data to write
        int age = 30;
        double salary = 1000.0;
        String name = "Peter";
        System.out.println("Writing to file ...");
        try {
            DataOutputStream dos = new DataOutputStream(
                    new FileOutputStream("myData.txt")
            );
            // write data to the file.
            dos.writeInt(age);
            dos.writeDouble(salary);
            dos.writeUTF(name);
            // close the file.
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();   //TODO treat the exception
        }
    }

    /**
     * read data from file and display it
     */
    private static void readAndShowInfo() {
        System.out.println("Reading from file ...");
        try {
            DataInputStream dis = new DataInputStream(
                    new FileInputStream("myData.txt")
            );
            // read data from file.
            int age = dis.readInt();
            double salary = dis.readDouble();
            String name = dis.readUTF();
            // show data to console.
            System.out.format("age=%d\n", age);
            System.out.format("salary=%f\n", salary);
            System.out.format("name=%s\n", name);
        } catch (IOException e) {
            e.printStackTrace();   //TODO treat the exception
        }
    }

    /**
     * generate list of data and write it to file
     */
    private static void writeList() {
        //generate test data
        List<Double> data = Stream
                .of(1.0, 2.0, 3.0, 4.0, 5.0)
                .toList();
        //write data
        try (DataOutputStream dos = new DataOutputStream(
                new FileOutputStream("myData2.txt"))) {
            for (int i = 0; i < data.size(); i++) {
                dos.writeDouble(data.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * read a list of data from file and display it
     */
    private static void readAndShowList() {
        List<Double> data = new ArrayList<>();
        //read data
        try (DataInputStream dis = new DataInputStream(
                new FileInputStream("myData2.txt"))) {
            while (dis.available() > 0) {
                double d = dis.readDouble();
                data.add(d);
            }
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //display read data
        for (Double elem : data) {
            System.out.println(elem);
        }
    }

}

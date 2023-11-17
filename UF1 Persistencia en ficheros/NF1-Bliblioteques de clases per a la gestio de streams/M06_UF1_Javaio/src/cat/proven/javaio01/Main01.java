package cat.proven.javaio01;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import cat.proven.javaio01.persist.FilePersist;

public class Main01 {

    private FilePersist persister;

    public Main01() {
        persister = new FilePersist();
    }

    public static void main(String[] args) {
        Main01 ap = new Main01();

        ap.run();
    }

    private void run() {
        writeBytesTest();
        //writeCharsTest();
    }

    private void writeBytesTest() {
        //FilePersist persister = new FilePersist();
//define filename
        String filename = "bytes.txt";
//define data to be written
        List<Byte> data = List.of((byte) 10, (byte) 15, (byte) 25, (byte) 30, (byte) 45);
        try {
            System.out.println("Writing data");
            persister.writeBytesToFile(filename, data);
            System.out.println("Reading data");
            List<Byte> data2 = persister.readBytesToFile(filename);
            System.out.println("Data read");
            System.out.println(data2);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Input or output problem related to this operation");
        }
    }

    private void writeCharsTest() {
        //FilePersist persister = new FilePersist();
        String filename = "chars.txt";
        List<Character> data = List.of('a', 'e', 'i', 'o', 'u');
        try {
            System.out.println("Writing data");
            persister.writeCharsToFile(filename, data);
            System.out.println("Reading data");
            List<Character> data2 = persister.readCharsToFile(filename);
            System.out.println("Data read");
            System.out.println(data2);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Input or output problem related to this operation");
        }
    }

}

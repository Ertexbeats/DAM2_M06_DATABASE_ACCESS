package cat.proven.javaio01.persist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* 
This calss persist data in files
@author Miguel Mendez
 */
public class FilePersist {

    /**
     * reads a list of byte from file
     *
     * @param filename the name of the file to read from
     * @return list of bytes read
     * @throws FileNotFoundException if file not found
     * @throws IOException in case an input/output error occurs
     */
    public List<Byte> readBytesToFile(String filename) throws FileNotFoundException, IOException {
        File f = new File(filename);
        List<Byte> data = new ArrayList<>();
        try (FileInputStream fs = new FileInputStream(f)) {
            int x;  //variable to store each byte read
            while ((x = fs.read()) != -1) { //while not end of file,  keep reading
                data.add((byte) x);
            }
        }
        return data;
    }

    /**
     * writes a list of byte to file
     *
     * @param filename the name of the file to write to
     * @param data the list of byte to write
     * @throws FileNotFoundException if file not found
     * @throws IOException in case an input/output error occurs
     */
    public void writeBytesToFile(String filename, List<Byte> data) throws FileNotFoundException, IOException {
        File f = new File(filename);
        try (FileOutputStream fs = new FileOutputStream(f)) {
            for (Byte b : data) {
                fs.write(b);
            }
        }
    }

    /**
     * writes a list of characters to file
     *
     * @param filename the name of the file to write to
     * @param data the list of characters to write
     * @throws FileNotFoundException if file not found
     * @throws IOException in case an input/output error occurs
     */
    public void writeCharsToFile(String filename, List<Character> data) throws FileNotFoundException, IOException {
        File f = new File(filename);
        try (FileWriter fs = new FileWriter(f)) {
            for (Character b : data) {
                fs.write(b);
            }
        }
    }

    /**
     * reads a list of characters from file
     *
     * @param filename the name of the file to read from
     * @return list of characters read
     * @throws FileNotFoundException if file not found
     * @throws IOException in case an input/output error occurs
     */
    public List<Character> readCharsToFile(String filename) throws FileNotFoundException, IOException {
        File f = new File(filename);
        List<Character> data = new ArrayList<>();
        try (FileReader fs = new FileReader(f)) {
            int x;  //variable to store each char read
            while ((x = fs.read()) != -1) { //while not end of file,  keep reading
                data.add((char) x);
            }
        }
        return data;
    }
}

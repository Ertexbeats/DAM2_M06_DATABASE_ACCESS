package cat.proven.javaio01.persist;

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
 * Class to provide persistence of string texts in file line by line
 *
 * @author Jose
 */
public class LinesFile {

    /**
     * saves array of String into a file, each element in a line
     *
     * @param data the array of String to be saved
     * @param filename the name of the file
     * @return the number of lines actually written
     */
    public int saveLinesToFile(List<String> data, String filename) {
        int counter = 0;
        try (PrintStream out = new PrintStream(filename)) {
            for (String elem : data) {
                out.println(elem);
                counter++;
            }
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return counter;
    }

    /**
     * reads array of String from a file, each element from a line
     *
     * @param filename the name of the file
     * @return the list of String
     */
    public List<String> readLinesFromFile(String filename) {
        List<String> data = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            String elem;
            while ((elem = in.readLine()) != null) {
                data.add(elem);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

}

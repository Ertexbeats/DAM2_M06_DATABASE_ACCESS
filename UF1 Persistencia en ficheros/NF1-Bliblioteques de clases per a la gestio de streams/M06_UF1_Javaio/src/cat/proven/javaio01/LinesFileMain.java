package cat.proven.javaio01;

import cat.proven.javaio01.persist.LinesFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jose
 */
public class LinesFileMain {

    public static void main(String[] args) {
        //create lines
        List<String> lines = createLines();
        //display lines
        System.out.println("Initial data:");
        displayList(lines);
        //ask for name of file to save lines to
        Scanner sc = new Scanner(System.in);
        System.out.print("File name: ");
        String filename = sc.next();
        //instantiate LineFile object
        LinesFile linesFile = new LinesFile();
        //save lines to file
        linesFile.saveLinesToFile(lines, filename);
        //read lines from file
        List<String> lines2 = linesFile.readLinesFromFile(filename);
        //display read lines
        System.out.println("Read data:");
        displayList(lines2);
    }

    private static List<String> createLines() {
        List<String> data = new ArrayList<>();
        data.add("Dabale arroz a la zorra el abad");
        data.add("En un lugar de la Mancha");
        data.add("de cuyo nombre no quiero acordarme");
        data.add("No dejes para mañana lo que puedas hacer hoy");
        return data;
    }

    private static void displayList(List<String> data) {
        for (String elem : data) {
            System.out.println(elem);
        }
    }

}

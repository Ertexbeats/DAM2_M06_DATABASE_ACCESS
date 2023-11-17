package cat.proven.javaio01;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Open a file in append mode
 *
 * @author Jose
 */
public class CharAppend {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Input file name: ");
        String filename = scan.next();
        System.out.print("Input character: ");
        String sCharacter = scan.next();
        char character = sCharacter.charAt(0);
        //write to file
        try (FileWriter f = new FileWriter(filename, true)) {
            f.write(character);
        } catch (IOException ex) {
            System.out.println("Error writing");
        }
        //read from file and display content
        try (FileReader f = new FileReader(filename)) {
            int c;
            while ((c = f.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println("Error reading");
        }
    }
}

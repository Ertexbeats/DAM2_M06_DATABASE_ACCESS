package cat.proven.texteditor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Text persistence in file
 *
 * @author Jose
 */
public class TextFile {

    /**
     * reads text from a file
     *
     * @param file the file to read from
     * @return text read from file
     * @throws FileNotFoundException if path does not exists
     * @throws IOException in case of input/output error
     */
    public static String readFromFile( File file )
            throws FileNotFoundException, IOException {
        StringBuilder sb = new StringBuilder();
        try ( FileReader in = new FileReader( file ) ) {
            int c;
            while ( ( c = in.read() ) != -1 ) {
                sb.append( ( char ) c );
            }
        }
        return sb.toString();
    }

    /**
     * writes a text to a file
     *
     * @param file the file to write to
     * @param text the text to write to file
     * @throws IOException in case of input/output error
     */
    public static void writeToFile( File file, String text )
            throws IOException {
        try ( FileWriter out = new FileWriter( file ) ) {
            int length = text.length();
            for ( int i = 0; i < length; i++ ) {
                out.write( text.charAt( i ) );
            }
        }
    }

}

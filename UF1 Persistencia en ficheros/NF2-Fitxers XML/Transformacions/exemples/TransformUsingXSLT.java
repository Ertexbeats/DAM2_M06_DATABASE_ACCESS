
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Source;
import javax.xml.transform.Result;
import javax.xml.transform.OutputKeys;

public class TransformUsingXSLT {
    
    public static void main(String[] args) {
        TransformUsingXSLT ap = new TransformUsingXSLT();
        ap.run();
    }  
    
    private void run() {

        try {
            // Read file names from user.;
            String xmlInputFilename = inputString("Enter source XML file name: ");
            String xslInputFilename = inputString("Enter source XSL file name: ");
            String xmlOutputFilename = inputString("Enter destination XML file name: ");
            Source input  = new StreamSource(xmlInputFilename);
            Source xsl    = new StreamSource(xslInputFilename);
            Result output = new StreamResult(xmlOutputFilename);
            // Perform transformation.
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(xsl);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(input, output);
        } catch (TransformerException ex) {
            Logger.getLogger(TransformUsingXSLT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TransformUsingXSLT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String inputString(String message) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(message);
        return bf.readLine();
    }    
    
}

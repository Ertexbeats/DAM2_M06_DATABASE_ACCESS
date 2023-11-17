
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Jose Moreno
 */
public class BankSaxParser {

    public static void main(String[] args) {
        //XML filename
        String filename = "bank.xml";
        //create a "parser factory" for creating SAX parsers
        SAXParserFactory spfac = SAXParserFactory.newInstance();
        //use the parser factory to create a SAXParser object
        SAXParser sp;
        try {
            sp = spfac.newSAXParser();
            //instantiate the content handler
            BankContentHandler handler = new BankContentHandler();
                //parse the input using proper handler
                sp.parse(filename, handler);
                //get data read from handler
                List<Account> data = handler.getData();
                //display data
                data.forEach(System.out::println);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(BankSaxParser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(BankSaxParser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BankSaxParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }

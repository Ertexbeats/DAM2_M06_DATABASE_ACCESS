
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Content handler for SAX parsing of bank accounts
 */
public class BankContentHandler extends DefaultHandler {

    private Account acct;
    private String temp;
    private List<Account> accList;

    /**
     * When the parser encounters plain text (not XML elements), it calls(this
     * method, which accumulates them in a string buffer
     */
    @Override
    public void characters(char[] buffer, int start, int length) {
        temp = new String(buffer, start, length);
    }

    /**
     * Every time the parser encounters the beginning of a new element, it calls
     * this method, which resets the string buffer
     */
    @Override
    public void startElement(String uri, String localName,
            String qName, Attributes attributes) throws SAXException {
        temp = "";
        switch (qName.toLowerCase()) {
            case "bank" -> //create list
                accList = new ArrayList<>();
            case "account" -> {
                //create a new account object
                acct = new Account();
                //get attribute and set to object
                acct.setType(attributes.getValue("type"));
            }
        }
    }

    /**
     * When the parser encounters the end of an element, it calls this method
     */
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        switch (qName.toLowerCase()) {
            case "account" -> //add account to list
                accList.add(acct);
            case "name" -> //set attribute to object
                acct.setName(temp);
            case "id" -> //set attribute to object
                acct.setId(Integer.parseInt(temp));
            //TODO: consider catching conversion exception
            case "amt" -> //set attribute to object
                acct.setAmt(Double.parseDouble(temp));
            //TODO: consider catching conversion exception
        }
    }

    public List<Account> getData() {
        return this.accList;
    }

}

package cat.proven.realestatedomgui.model.persist;

import cat.proven.realestatedomgui.model.Address;
import cat.proven.realestatedomgui.model.Estate;
import cat.proven.realestatedomgui.model.RealEstate;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * DOM XML file reader for real estate.
 * @author ProvenSoft
 */
public class DomXmlPersist {
    /**
     * loads realestate data from xml file.
     * @param filename the filename to read.
     * @return a realestate or null in case of error.
     */
    public RealEstate load(String filename) {
        RealEstate data = new RealEstate();
        try {  
            File fXmlFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setValidating(true);
            dbFactory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.normalize();
            //get realestate name.
            NodeList nameNodeList = doc.getElementsByTagName("name");
            Node nameNode = nameNodeList.item(0);
            String name = nameNode.getChildNodes().item(0).getNodeValue();
            data.setName(name);
            //get estates.
            NodeList estateNodeList = doc.getElementsByTagName("estate");
            for (int i=0; i<estateNodeList.getLength(); i++) {
                Element estateElement = (Element) estateNodeList.item(i);
                //read estate.
                Estate estate = estateFromNode(estateElement);
                data.getEstates().add(estate);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DomXmlPersist.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DomXmlPersist.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DomXmlPersist.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    private Estate estateFromNode(Element element) {
        Estate estate = null;
        try {
            String id = element.getAttribute("id");
            String type = element.getAttribute("type");
            Element child;
            child = (Element) element.getElementsByTagName("surface").item(0);
            double surface = Double.parseDouble(getElementTextString(child));
            child = (Element) element.getElementsByTagName("price").item(0);
            double price = Double.parseDouble(getElementTextString(child));
            child = (Element) element.getElementsByTagName("address").item(0);
            Address address = addressFromNode(child);
            estate = new Estate(id, type, surface, address, price);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return estate;
    }
    
    private Address addressFromNode(Element element) {
        Address address = null;
        try {
            Element child;
            child = (Element) element.getElementsByTagName("street").item(0);
            String street = getElementTextString(child);
            child = (Element) element.getElementsByTagName("number").item(0);
            int number = Integer.parseInt(getElementTextString(child));
            child = (Element) element.getElementsByTagName("floor").item(0);
            int floor = Integer.parseInt(getElementTextString(child));
            child = (Element) element.getElementsByTagName("door").item(0);
            int door = Integer.parseInt(getElementTextString(child));
            address = new Address(street, number, floor, door);           
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return address;
    }
    
    private String getElementTextString(Element element) {
        String text = element.getChildNodes().item(0).getNodeValue();
        //System.out.format("%s: %s\n", element.getNodeName(), text);
        return text;
    }
    
}


import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author ProvenSoft
 */
public class StaffDom {

    private final String filename = "staff.xml";

    public static void main(String[] args) {
        StaffDom ap = new StaffDom();
        ap.run();
    }

    private void run() {
        try {
            File fXmlFile = new File(filename);

            // get Document Builder
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setValidating(true);
            dbFactory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            //parse file.
            Document doc = dBuilder.parse(fXmlFile);
            doc.normalize();
            //normalize document format.
            doc.getDocumentElement().normalize();           
            //display document information.
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            System.out.println("-----------------------");

            // Recursive version
            Node rootNode = doc.getDocumentElement();
            showNode(rootNode);

//            // iterative version
//            //iterate over staff elements.
//             NodeList nList = doc.getElementsByTagName("staff");
//             for (int i = 0; i < nList.getLength(); i++) {
//                  //get each node.
//                  Node node = nList.item(i);
//                  //show node content.
//                  showNode(node);
//             }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(StaffDom.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * For type text node, it prints node data (name, value, parent name). For
     * type attribute node, it prints node data (name, value). For type element
     * node, it shows element name and calls itself.
     */
    private void showNode(Node node) {
        //String ignorableFirstCharacter = "\n"; //choose this to avoid newlines to be read as text nodes.
        switch (node.getNodeType()) {
            case Node.ELEMENT_NODE -> {
                Element element = (Element) node;
                //show element name.
                System.out.println(element.getNodeName());
                //show attributes.
                NamedNodeMap atts = element.getAttributes();
                for (int i = 0; i < atts.getLength(); i++) {
                    Node at = atts.item(i);
                    showNode(at);
                }
                //show child nodes.
                NodeList elemChilds = element.getChildNodes();
                for (int i = 0; i < elemChilds.getLength(); i++) {
                    showNode(elemChilds.item(i));
                }
            }
            case Node.ATTRIBUTE_NODE ->
                System.out.format("\tAttr.name: %s. Attr. value: %s\n",
                        node.getNodeName(), node.getNodeValue());
            case Node.TEXT_NODE -> 
                //if (!ignorableFirstCharacter.contains(String.valueOf(node.getNodeValue().charAt(0)))) {
                System.out.format("\t%s: %s (%s)\n",
                        node.getNodeName(), node.getNodeValue(), node.getParentNode().getNodeName());
                //}
            default -> {
            }
        }
    }

}

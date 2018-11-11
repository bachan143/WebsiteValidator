import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XMLUtility {

    /**
     * Connects to the given xmlUrl and gets all the urls and dates
     * @param xmlUrl the url where the xml file can be accessed
     * @return List of SortableURL
     */
    public static List<SortableURL> getURLsFromXML(String xmlUrl) {
        Document doc;
        List<SortableURL> sortableURLS = new ArrayList<>();

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(new URL(xmlUrl).openStream());

        } catch (ParserConfigurationException | IOException | SAXException e) {
            return null; //exception handling is not really necessary because this class is just used for unit tests
        }

        if (null == doc)
            return null;

        NodeList nList = doc.getElementsByTagName("url");

            for(int i = 0; i < nList.getLength(); i++) {
                Node n =  nList.item(i);

                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) n;

                    //parse and store date and url
                    Date date = parseDate(e.getElementsByTagName("news:publication_date").item(0).getTextContent());
                    sortableURLS.add(new SortableURL(e.getElementsByTagName("loc").item(0).getTextContent(),
                            date));
                }
            }

        return sortableURLS;
    }

    /**
     * Helper class for parsing the date from the given format into a Java date object
     * @param dateStr date as string
     * @return date as object
     */
    private static Date parseDate(String dateStr) {
        Date date;
        DateFormat format = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss");

        try {
            date = format.parse(dateStr);
        } catch (ParseException x) {
            date = null; //again the exception handling is not implemented
        }
        return date;
    }

}

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLUtility {

    /**
     * Counts all open p-Tags and returns it
     * @param document the HTML document
     * @return integer - count of open p-Tags
     */
    public static int countOpenPTags(Document document) {
        int count = 0;

        for (Element e: document.select("p")) {
            count += countWithRegex("<p[^>]*>", e.outerHtml().toLowerCase());
        }

        return count;
    }

    /**
     * Counts all closed p-Tags and returns it
     * @param document the HTML document
     * @return integer - count of closed p-Tags
     */
    public static int countClosedPTags(Document document) {
        int count = 0;

        for (Element e: document.select("p")) {
            count += countWithRegex("<\\/p[^>]*>", e.outerHtml().toLowerCase());
        }

        return count;
    }

    /**
     * Counts all open h1-Tags and returns it
     * @param document the HTML document
     * @return integer - count of open h1-Tags
     */
    public static int countOpenH1Tags(Document document) {
        int count = 0;

        for (Element e: document.select("h1")) {
            count += countWithRegex("<h1[^>]*>", e.outerHtml().toLowerCase());
        }

        return count;
    }

    /**
     * Counts all closed h1-Tags and returns it
     * @param document the HTML document
     * @return integer - count of closed h1-Tags
     */
    public static int countClosedH1Tags(Document document) {
        int count = 0;

        for (Element e: document.select("h1")) {
            count += countWithRegex("<\\/h1[^>]*>", e.outerHtml().toLowerCase());
        }

        return count;
    }

    /**
     * Checks the given HTML document for specific disallowed Tags
     * @param document the HTML document
     * @return boolean - true if the document is ok
     */
    public static boolean checkForDisallowedTags(Document document) {

        for (Element e: document.select("p")) {
            if (countWithRegex("<img[^>]*>", e.html()) > 0)
                return false;
            if (countWithRegex("<div[^>]*>", e.html()) > 0)
                return false;
            if (countWithRegex("<table[^>]*>", e.html()) > 0)
                return false;
        }
        return true;
    }


    /**
     * Helper class for counting occurrences found in a string using regular expressions
     * @param regex the regular expression as string
     * @param str the text which should be searched
     * @return integer - count of occurrences
     */
    private static int countWithRegex(String regex, String str) {
        int count = 0;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        while(matcher.find())
            count++;

        return count;
    }

}

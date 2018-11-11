import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(Parameterized.class)
public class NewsArticlesTestMorgenpost {

    private SortableURL sUrl;
    private Document doc;

    public NewsArticlesTestMorgenpost(SortableURL url) {
        this.sUrl = url;
    }

    @Parameterized.Parameters
    public static Collection URLS() {
        //Sort urls and only get the first 50
        List<SortableURL> sortableURLS = XMLUtility.getURLsFromXML("https://www.morgenpost.de/sitemaps/news.xml");
        Comparator comp = Collections.reverseOrder();
        Collections.sort(sortableURLS, comp);
        return sortableURLS.stream().limit(50).collect(Collectors.toList());
    }

    @Before
    public void connect() throws IOException {
        doc = Jsoup.connect(sUrl.getUrl()).get();
        //System.out.println(sUrl.getUrl()); //this is just to see what url is tested (used for debugging)
    }

    @Test
    public void testH1Tags() {
        Assert.assertEquals(1, HTMLUtility.countOpenH1Tags(doc));
        Assert.assertEquals(1, HTMLUtility.countClosedH1Tags(doc));
    }

    @Test
    public void testPTags() {
        Assert.assertEquals(HTMLUtility.countOpenPTags(doc), HTMLUtility.countClosedPTags(doc));
    }

    @Test
    public void testDisallowedTags() {
        Assert.assertEquals(true, HTMLUtility.checkForDisallowedTags(doc));
    }
}

import java.util.Date;

public class SortableURL implements Comparable<SortableURL> {

    private String url;
    private Date dateTime;

    /**
     * Constructor - initialises the object with given url and date
     * @param url url as string
     * @param dateTime date as object
     */
    public SortableURL(String url, Date dateTime){
        this.url = url;
        this.dateTime = dateTime;
    }

    /**
     * Get the Date
     * @return Date - date
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * Get the url
     * @return String - url
     */
    public String getUrl() {
        return url;
    }

    /**
     * compare method so you can sort the urls by their date
     * @param o SortableURL - object
     * @return integer
     */
    @Override
    public int compareTo(SortableURL o) {
        if (getDateTime() == null || o.getDateTime() == null)
            return 0;
        return getDateTime().compareTo(o.getDateTime());
    }
}

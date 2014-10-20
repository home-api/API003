package groupId;

import org.jsoup.Jsoup;

/**
 * Hello world!
 */
public class App {

    public static final String URL = "url";
    public static final String CLIENT =
            "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";

    public static void main(String[] args) {
        while (true) {
            Jsoup.connect(URL)
                    .userAgent(CLIENT);
        }
    }
}

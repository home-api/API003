package korobitsin.http;

import org.apache.http.HttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 10/21/2014
 * Time: 5:44 AM
 */
public class Authenticator {

    private static String lowRes;
    private static String wKey;
    private static String loginKey;

    public static HttpResponse auth(String login, String password) throws IOException {
        HttpResponse firstResponse = HttpClientWrapper.executeGet(HttpClientWrapper.FIELDS_URL);
        String loginPage = EntityUtils.toString(firstResponse.getEntity());

        initializeRequestParams(loginPage);

        return HttpClientWrapper.executePost(
                HttpClientWrapper.FIELDS_URL,
                new BasicNameValuePair("w", wKey),
                new BasicNameValuePair("lowRes", lowRes),
                new BasicNameValuePair("login", loginKey),
                new BasicNameValuePair("name", login),
                new BasicNameValuePair("password", password)
        );
    }

    private static void initializeRequestParams(String loginPage) {
        Document document = Jsoup.parse(loginPage);
        Elements formElements = document.getElementsByTag("form");
        for (Element formElement : formElements) {
            if (formElement.attr("name").equals("login")) {
                Elements inputFields = formElement.getElementsByTag("input");
                for (Element inputField : inputFields) {
                    String s = inputField.attr("name");
                    if (s.equals("lowRes")) {
                        lowRes = inputField.attr("value");
                    } else if (s.equals("w")) {
                        wKey = inputField.attr("value");
                    } else if (s.equals("login")) {
                        loginKey = inputField.attr("value");
                    }
                }
            }
        }
    }

}

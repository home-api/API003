package groupId.http;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 10/21/2014
 * Time: 5:44 AM
 */
public class Authenticator {

    public static HttpResponse auth() throws IOException {
        HttpResponse firstResponse = HttpClientWrapper.executeGet(HttpClientWrapper.FIELDS_URL);
        String loginPage = EntityUtils.toString(firstResponse.getEntity());

        String lowRes = "";
        String wKey = "";
        String loginKey = "";

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

        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("w", wKey));
        params.add(new BasicNameValuePair("lowRes", lowRes));
        params.add(new BasicNameValuePair("login", loginKey));
        params.add(new BasicNameValuePair("name", "login"));
        params.add(new BasicNameValuePair("password", "password"));

        return HttpClientWrapper.executePost(HttpClientWrapper.FIELDS_URL, params);
    }

}

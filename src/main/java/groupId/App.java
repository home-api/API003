package groupId;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Hello world!
 */
public class App {

    public static final String URL = "";
    public static final String CLIENT =
            "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";

    private static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
    private static CloseableHttpClient client = HttpClients.custom().setConnectionManager(cm).build();

    public static void main(String[] args) throws IOException {
        //while (true) {

        HttpGet request1 = new HttpGet(URL);

        request1.setHeader("User-Agent", CLIENT);
        request1.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        request1.setHeader("Accept-Language", "en-US,en;q=0.5");

        CloseableHttpResponse response = client.execute(request1);

        HttpPost request = new HttpPost (URL);

        Connection.Response res = Jsoup.connect(URL)
                .userAgent(CLIENT)
                .data("name", "babushka", "password", "yecgaa")
                .method(Connection.Method.POST)
                .execute();

        res = Jsoup.connect(URL)
                .userAgent(CLIENT)
                .data("name", "babushka", "password", "yecgaa")
                .method(Connection.Method.POST)
                .cookies(res.cookies())
                .execute();


        request.setHeader("User-Agent", CLIENT);
        request.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        request.setHeader("Accept-Language", "en-US,en;q=0.5");
        request.setHeader("Cookie", response.getFirstHeader("Set-Cookie").toString());
        request.setHeader("Connection", "keep-alive");
        request.setHeader("Referrer", URL);
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");

        ArrayList<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("name", "babushka"));
        parameters.add(new BasicNameValuePair("password", "yecgaa"));

        request.setEntity(new UrlEncodedFormEntity(parameters));

        HttpResponse response2 = client.execute(request);

        StringBuffer result = new StringBuffer();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(response2.getEntity().getContent()))) {
            for (String line; (line = br.readLine()) != null;) {
                result.append(line);
            }
            br.close();
        }

        System.out.println(11);

            Connection res2 = Jsoup.connect("")
                    .followRedirects(true)
                    .userAgent(CLIENT)
                    .method(Connection.Method.GET)
                    .cookies(res.cookies());

            Document doc = res2.get();
            System.out.println(res2);

        //}
    }
}

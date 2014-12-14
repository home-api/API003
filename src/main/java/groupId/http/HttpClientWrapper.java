package groupId.http;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 10/21/2014
 * Time: 5:29 AM
 */
public class HttpClientWrapper {

    public static final String BASE_URL = "http://tx3.travian.ru/";
    public static final String FIELDS_URL = "dorf1.php";
    public static final String VILLAGE_URL = "dorf2.php";

    private static final String CLIENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) "
                    + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.111 Safari/537.36";

    private final static CloseableHttpClient httpClient = HttpClients.createDefault();

    public static HttpResponse executeGet(String subUrl) {
        HttpGet getRequest = new HttpGet(BASE_URL + subUrl);
        return execute(getRequest);
    }

    public static HttpResponse executePost(String subUrl, List<NameValuePair> params)
            throws UnsupportedEncodingException {
        HttpPost postRequest = new HttpPost(BASE_URL + subUrl);
        postRequest.setEntity(new UrlEncodedFormEntity(params));
        return execute(postRequest);
    }

    private static HttpResponse execute(HttpRequestBase request) {
        request.addHeader("User-Agent", HttpClientWrapper.CLIENT);
        request.setHeader("Connection", "keep-alive");

        HttpResponse response = null;
        try {
            response =  httpClient.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}

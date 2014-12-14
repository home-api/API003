package groupId.command.impl;

import groupId.command.Command;
import groupId.http.HttpClientWrapper;
import groupId.model.Village;
import groupId.util.http.HttpUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 10/31/2014
 * Time: 4:33 PM
 */
public class BuildCommand extends Command {

    @Override
    public void execute(Village village, String... params) throws IOException {
        String link = village.getBuildings().get(Integer.parseInt(params[1]) - 1).getLink();

        HttpResponse response = HttpClientWrapper.executeGet(link);
        String html = HttpUtil.getHtmlFromResponse(response);
        Elements buttons = Jsoup.parse(html)
                .getElementById("contract")
                .getElementsByClass("contractLink").get(0)
                .getElementsByTag("button");

        if (!buttons.isEmpty()) {
            if (buttons.get(0).attr("class").equals("green build")) {
                String buildLinkAction = buttons.get(0).attr("onclick");
                String buildLink = buildLinkAction.substring(
                        buildLinkAction.indexOf("dorf1"),
                        buildLinkAction.indexOf("';"));
                HttpClientWrapper.executeGet(buildLink);
            }
        }
    }

}

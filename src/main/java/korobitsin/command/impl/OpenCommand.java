package korobitsin.command.impl;

import korobitsin.command.Command;
import korobitsin.http.HttpClientWrapper;
import korobitsin.model.Village;
import korobitsin.util.convert.VillageConvertUtil;
import korobitsin.util.http.HttpUtil;
import korobitsin.util.print.PrintUtil;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 10/21/2014
 * Time: 5:23 AM
 */
public class OpenCommand extends Command {

    @Override
    public void execute(Village village, String... params) throws IOException {
        String villageName = params[1];
        String villageRef = "/";
        for (Map.Entry<String, String> villageEntry : village.getVillages().entrySet()) {
            if (villageEntry.getKey().equals(villageName)) {
                villageRef += villageEntry.getValue();
            }
        }

        HttpResponse response = HttpClientWrapper.executeGet(
                HttpClientWrapper.FIELDS_URL + villageRef);
        String html = HttpUtil.getHtmlFromResponse(response);
        PrintUtil.printVillageInformation(VillageConvertUtil.convertFromHtml(html));
    }
}

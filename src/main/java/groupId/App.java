package groupId;

import groupId.command.Command;
import groupId.http.Authenticator;
import groupId.model.Village;
import groupId.util.convert.VillageConvertUtil;
import groupId.util.http.HttpUtil;
import groupId.util.print.PrintUtil;
import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

public class App {

    private final static Logger LOG = Logger.getLogger(App.class);

    private static final Scanner CONSOLE = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        HttpResponse authResponse = Authenticator.auth();
        String html = HttpUtil.getHtmlFromResponse(authResponse);
        Village village = VillageConvertUtil.convertFromHtml(html);

        PrintUtil.printVillageInformation(village);

        while (true) {
            String command = CONSOLE.nextLine();
            Command.executeCommand(village, command.split(" "));
        }

    }

}

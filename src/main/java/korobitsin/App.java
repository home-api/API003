package korobitsin;

import korobitsin.command.Command;
import korobitsin.http.Authenticator;
import korobitsin.model.Village;
import korobitsin.util.convert.VillageConvertUtil;
import korobitsin.util.http.HttpUtil;
import korobitsin.util.print.PrintUtil;
import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

public class App {

    private final static Logger LOG = Logger.getLogger(App.class);

    private static final Scanner CONSOLE = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.print("Логинь: ");
        String login = CONSOLE.next();
        System.out.print("Пароль: ");
        String password = CONSOLE.next();;

        HttpResponse authResponse = Authenticator.auth(login, password);

        String html = HttpUtil.getHtmlFromResponse(authResponse);
        Village village = VillageConvertUtil.convertFromHtml(html);

        PrintUtil.printVillageInformation(village);

        while (true) {
            String command = CONSOLE.nextLine();
            Command.executeCommand(village, command.split(" "));
        }

    }

}

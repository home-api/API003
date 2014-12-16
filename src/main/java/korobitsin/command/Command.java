package korobitsin.command;

import korobitsin.command.impl.BuildCommand;
import korobitsin.command.impl.OpenCommand;
import korobitsin.model.Village;
import korobitsin.util.print.PrintUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 10/21/2014
 * Time: 5:23 AM
 */
public abstract class Command {

    private static Map<String, Command> commands = new HashMap<String, Command>();

    static {
        commands.put("open", new OpenCommand());
        commands.put("build", new BuildCommand());
    }

    public abstract void execute(Village village, String... params) throws IOException;

    public static void executeCommand(Village village, String... params) throws IOException {
        Command command = commands.get(params[0]);

        if (command == null) {
            PrintUtil.printLine("Неправильая команда.");
            return;
        }

        command.execute(village, params);
    }
}

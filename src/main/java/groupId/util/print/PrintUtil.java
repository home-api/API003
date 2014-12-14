package groupId.util.print;

import groupId.model.Building;
import groupId.model.Village;
import org.apache.log4j.Logger;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 10/21/2014
 * Time: 6:00 AM
 */
public class PrintUtil {

    private static final Logger LOG = Logger.getLogger(PrintUtil.class);

    public static void printVillageInformation(Village village) {
        printLine("Активная деревня aa : " + village.getActiveVillage());

        printLine("Деревни: " + village.getVillages().keySet());

        printLine(
                "Склад: " + village.getWarehouseContent() + "/" + village.getWarehouseCapacity(),
                "Амбар: " + village.getCropAmount() + "/" + village.getGranaryCapacity());

        printLine("------------------------------------------------------------------------------");
        printLine("Добыча ресурсов:");
        printLine("Дерево: " + village.getLumberAmount(),
                "Глина: " + village.getClayAmount(),
                "Железо: " + village.getIronAmount(),
                "Зерно: " + village.getCropAmount());
        printLine("------------------------------------------------------------------------------");
        List<String> messages = new ArrayList<String>();
        for (int i = 1; i <= village.getBuildings().size(); i++) {
            Building building = village.getBuildings().get(i - 1);

            String buildingOrder = String.format("%1$2s", i);
            String buildingName = String.format("%1$-20s", building.getName());
            String buildingLevel = String.format("%1$-4s", "(" + building.getLevel().toString() + ")");

            messages.add(buildingOrder + ". " + buildingName + buildingLevel);

            if (i % 4 == 0 || i == village.getBuildings().size()) {
                printLine(messages);
                messages = new ArrayList<String>();
            }
        }
        printLine("------------------------------------------------------------------------------");
    }

    public static void printLine(Object... parts) {
        String line = "";
        for (Object part : parts) {
            line += !line.isEmpty() ? "    " : "";
            line += part;
        }
        PrintStream ps = null;
        try {
            ps = new PrintStream(System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ps.println(line);
        //LOG.info(line);
    }

}

package korobitsin.util.convert;

import korobitsin.model.Building;
import korobitsin.model.Village;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 10/21/2014
 * Time: 12:25 AM
 */
public class VillageConvertUtil {

    public static final String WAREHOUSE_ELEMENT_ID = "stockBarWarehouse";
    public static final String GRANARY_ELEMENT_ID = "stockBarGranary";
    public static final String LUMBER_AMOUNT_ELEMENT_ID = "l1";
    public static final String CLAY_ELEMENT_ID = "l2";
    public static final String IRON_ELEMENT_ID = "l3";
    public static final String CROP_ELEMENT_ID = "l4";
    public static final String VILLAGE_BOX_ID = "sidebarBoxVillagelist";

    public static Village convertFromHtml(String html) {
        Document page = Jsoup.parse(html);

        Integer warehouseCapacity = Integer.parseInt(
                page.getElementById(WAREHOUSE_ELEMENT_ID).text());
        Integer granaryCapacity = Integer.parseInt(
                page.getElementById(GRANARY_ELEMENT_ID).text());
        Integer lumberAmount = Integer.parseInt(
                page.getElementById(LUMBER_AMOUNT_ELEMENT_ID).text());
        Integer clayAmount = Integer.parseInt(
                page.getElementById(CLAY_ELEMENT_ID).text());
        Integer ironAmount = Integer.parseInt(
                page.getElementById(IRON_ELEMENT_ID).text());
        Integer cropAmount = Integer.parseInt(
                page.getElementById(CROP_ELEMENT_ID).text());

        ArrayList<Building> buildings = new ArrayList<Building>();
        Elements pageBuildings = page.getElementById("content").getElementsByTag("area");
        for (Element pageBuilding : pageBuildings) {
            String link = pageBuilding.attr("href");
            if (link.equals("dorf2.php")) {
                continue;
            }

            String buildingAltText = pageBuilding.attr("alt");
            String[] buildingAttr = buildingAltText.split(" ");
            Integer level = Integer.parseInt(buildingAttr[buildingAttr.length - 1]);
            String name = buildingAttr[0] + (buildingAttr.length == 4 ? " " + buildingAttr[1] : "");
            //TODO move formatting to  print method
            buildings.add(new Building(level, name, link));
        }

        List<Building> activeBuildings = extractActiveBuildings(page);

        Village village = new Village(
                new Date(), warehouseCapacity, granaryCapacity,
                lumberAmount, clayAmount, ironAmount, cropAmount,
                0, 0, 0, 0,
                buildings, activeBuildings);

        fillVillages(page, village);

        return village;

    }

    static List<Building> extractActiveBuildings(Document page) {
        List<Building> activeBuildings = new ArrayList<Building>();
        Elements activeBuildingsElements = page.getElementsByClass("buildingList").get(0)
                .getElementsByTag("ul").get(0)
                .getElementsByTag("li");
        for (Element activeBuildingElement : activeBuildingsElements) {
            String name = activeBuildingElement.getElementsByClass("name").text();
            Integer lvl = 0;
            Integer buildTime = 0;
            Building activeBuilding = new Building(lvl, name, 0);
            activeBuildings.add(activeBuilding);
        }
        return activeBuildings;
    }

    private static void fillVillages(Document page, Village villageModel) {
        Map<String, String> villages = new HashMap<String, String>();
        String activeVillage = "";
        Element villageBox = page.getElementById(VILLAGE_BOX_ID);
        for (Element village : villageBox.getElementsByTag("li")) {
            Element villageBlock = village.getElementsByTag("a").get(0);

            String villageName = villageBlock.getElementsByTag("div").get(0).text();
            String villageRef = villageBlock.attr("href");
            villages.put(villageName, villageRef);

            if (villageBlock.attr("class").equals("active")) {
                activeVillage = villageName;
            }
        }
        villageModel.setActiveVillage(activeVillage);
        villageModel.setVillages(villages);
    }

}

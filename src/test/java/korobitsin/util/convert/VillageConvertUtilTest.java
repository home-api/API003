package korobitsin.util.convert;

import korobitsin.model.Building;
import junit.framework.TestCase;
import org.jsoup.nodes.Document;

import java.util.Arrays;
import java.util.List;

public class VillageConvertUtilTest extends TestCase {

    public void testExtractActiveBuildings() {
        // Given
        String html =
                "<div class=\"boxes buildingList\">\n"
                        + "<div class=\"boxes-contents cf\"><h5>Строительство:</h5>\n"
                        + "<ul>\n"
                        + "<li>\n"
                        + "<a href=\"?d=1680835&amp;a=0&amp;c=c78304\">\n"
                        + "<img src=\"img/x.gif\" class=\"del\" alt=\"Отменить\">\n"
                        + "</a>\n"
                        + "<div class=\"name\">\n"
                        + "Лесопилка<span class=\"lvl\">Уровень 4</span>\n"
                        + "</div>\n"
                        + "<div class=\"buildDuration\">\n"
                        + "<span id=\"timer1\">0:10:06</span> ч. Готово в 21:34\n"
                        + "</div>\n" +
                        "<div class=\"clear\"></div>\n"
                        + "</li>\n"
                        + "<li>\n"
                        + "<a href=\"?d=1680996&amp;a=0&amp;c=c78304\">\n"
                        + "<img src=\"img/x.gif\" class=\"del\" alt=\"Отменить\">\n"
                        + "</a>\n"
                        + "<div class=\"name\">\n"
                        + "Ферма<span class=\"lvl\">Уровень 1</span>\n"
                        + "</div>\n"
                        + "<div class=\"buildDuration\">\n"
                        + "<span id=\"timer2\">0:10:56</span> ч. Готово в 21:35\n"
                        + "</div>\n"
                        + "<div class=\"clear\"></div>\n"
                        + "</li>\n"
                        + "</ul>\n"
                        + "</div>\n"
                        + "</div>";

        // When
        List<Building> activeBuildings = VillageConvertUtil.extractActiveBuildings(
                new Document(html));

        // Then
        List<Building> expectedBuildings = Arrays.asList(
                new Building(4, "Лесопилка", 606), new Building(1, "Ферма", 656));
        assertEquals(expectedBuildings, activeBuildings);
    }

}
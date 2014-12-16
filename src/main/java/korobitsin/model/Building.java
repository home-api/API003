package korobitsin.model;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 10/22/2014
 * Time: 2:30 PM
 */
public class Building {

    private Integer level;
    private String name;
    private String link;
    private Integer buildTime;

    public Building(Integer level, String name, String link) {
        this.level = level;
        this.name = name;
        this.link = link;
    }

    public Building(Integer level, String name, Integer buildTime) {
        this(level, name, "");
        this.buildTime = buildTime;
    }

    public Integer getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }
}

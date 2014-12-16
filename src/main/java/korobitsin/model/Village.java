package korobitsin.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: web
 * Date: 10/21/2014
 * Time: 12:18 AM
 */
public class Village {

    private Date loadDate;

    private Integer warehouseCapacity;
    private Integer granaryCapacity;

    private Integer lumberAmount;
    private Integer clayAmount;
    private Integer ironAmount;
    private Integer cropAmount;

    private Integer lumberProduction;
    private Integer clayProduction;
    private Integer ironProduction;
    private Integer cropProduction;

    private String activeVillage;
    private Map<String, String> villages;
    private Boolean isAttacked;

    private List<Building> buildings;

    private List<Building> activeBuildings;

    public Village() {
    }

    public Village(
            Date loadDate, Integer warehouseCapacity, Integer granaryCapacity,
            Integer lumberAmount, Integer clayAmount,
            Integer ironAmount, Integer cropAmount,
            Integer lumberProduction, Integer clayProduction,
            Integer ironProduction, Integer cropProduction, List<Building> buildings,
            List<Building> activeBuildings) {
        this.loadDate = loadDate;
        this.warehouseCapacity = warehouseCapacity;
        this.granaryCapacity = granaryCapacity;
        this.lumberAmount = lumberAmount;
        this.clayAmount = clayAmount;
        this.ironAmount = ironAmount;
        this.cropAmount = cropAmount;
        this.lumberProduction = lumberProduction;
        this.clayProduction = clayProduction;
        this.ironProduction = ironProduction;
        this.cropProduction = cropProduction;
        this.buildings = buildings;
        this.activeBuildings = activeBuildings;
    }

    public Integer getWarehouseContent() {
        return lumberAmount + clayAmount + ironAmount;
    }

    public Date getLoadDate() {
        return loadDate;
    }

    public Integer getWarehouseCapacity() {
        return warehouseCapacity;
    }

    public Integer getGranaryCapacity() {
        return granaryCapacity;
    }

    public Integer getLumberAmount() {
        return lumberAmount;
    }

    public Integer getClayAmount() {
        return clayAmount;
    }

    public Integer getIronAmount() {
        return ironAmount;
    }

    public Integer getCropAmount() {
        return cropAmount;
    }

    public Integer getLumberProduction() {
        return lumberProduction;
    }

    public Integer getClayProduction() {
        return clayProduction;
    }

    public Integer getIronProduction() {
        return ironProduction;
    }

    public Integer getCropProduction() {
        return cropProduction;
    }

    public String getActiveVillage() {
        return activeVillage;
    }

    public void setActiveVillage(String activeVillage) {
        this.activeVillage = activeVillage;
    }

    public Map<String, String> getVillages() {
        return villages;
    }

    public void setVillages(Map<String, String> villages) {
        this.villages = villages;
    }

    public Boolean getIsAttacked() {
        return isAttacked;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public List<Building> getActiveBuildings() {
        return activeBuildings;
    }
}

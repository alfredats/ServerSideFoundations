package dev.alfredang.weather.weatherApp.model;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

public class Weather {
    private String icon;
    private String main;
    private String description;

    /**
     * @return String return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return String return the main
     */
    public String getMain() {
        return main;
    }

    /**
     * @param main the main to set
     */
    public void setMain(String main) {
        this.main = main;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Weather[icon="+this.icon +", main=" + this.main + ", description=" + this.description + "]";
    }

    public static Weather create(String apiResp) throws PathNotFoundException {
        Weather w = new Weather();
        w.setIcon(JsonPath.read(apiResp, "$.weather[0].icon"));
        w.setMain(JsonPath.read(apiResp, "$.weather[0].main"));
        w.setDescription(JsonPath.read(apiResp, "$.weather[0].description"));
        System.out.println(w.toString());
        return w;
    }

}

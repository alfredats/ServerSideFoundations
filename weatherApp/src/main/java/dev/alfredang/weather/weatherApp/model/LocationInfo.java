package dev.alfredang.weather.weatherApp.model;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash
public class LocationInfo {
    private String id;
    private String name;
    private Weather weather;
    private Temperature temperature;
    private long timeUpdated;

    @TimeToLive
    private long time = 300;

    /**
     * @return String return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return Weather return the weather
     */
    public Weather getWeather() {
        return weather;
    }

    /**
     * @param weather the weather to set
     */
    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    /**
     * @return Temperature return the temperature
     */
    public Temperature getTemperature() {
        return temperature;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    /**
     * @return long return the timeUpdated
     */
    public long getTimeUpdated() {
        return timeUpdated;
    }

    /**
     * @param timeUpdated the timeUpdated to set
     */
    public void setTimeUpdated(long timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public static LocationInfo create(String id, String apiResp) throws PathNotFoundException {
        LocationInfo locInfo = new LocationInfo();

        locInfo.setId(id);
        locInfo.setName(JsonPath.parse(apiResp).read("$.name"));
        locInfo.setWeather(Weather.create(apiResp));
        locInfo.setTemperature(Temperature.create(apiResp));
        locInfo.setTimeUpdated(System.currentTimeMillis());

        return locInfo;
    }



}

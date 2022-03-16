package dev.alfredang.weather.weatherApp.model;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

public class Temperature {
    private double temp;
    private double min;
    private double max;
    private int humidity;
    private double feelsLike;

    /**
     * @return double return the temp
     */
    public double getTemp() {
        return temp;
    }

    /**
     * @param temp the temp to set
     */
    public void setTemp(double temp) {
        this.temp = temp;
    }

    /**
     * @return double return the min
     */
    public double getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(double min) {
        this.min = min;
    }

    /**
     * @return double return the max
     */
    public double getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(double max) {
        this.max = max;
    }

    /**
     * @return double return the feelsLike
     */
    public double getFeelsLike() {
        return feelsLike;
    }

    /**
     * @param feelsLike the feelsLike to set
     */
    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    /**
     * @return int return the humidity
     */
    public int getHumidity() {
        return humidity;
    }

    /**
     * @param humidity the humidity to set
     */
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }


    public static Temperature create(String apiResp) throws PathNotFoundException{
        Temperature t = new Temperature();
        t.setTemp(JsonPath.parse(apiResp).read("$.main.temp",Double.class));
        t.setMin(JsonPath.parse(apiResp).read("$.main.temp_min", Double.class));
        t.setMax(JsonPath.parse(apiResp).read("$.main.temp_max", Double.class));
        t.setHumidity(JsonPath.parse(apiResp).read("$.main.humidity", Integer.class));
        t.setFeelsLike(JsonPath.parse(apiResp).read("$.main.feels_like", Double.class));
        return t;
    }


}

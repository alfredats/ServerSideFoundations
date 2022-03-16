package dev.alfredang.weather.weatherApp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

public class JSONutil {
    public void run() {
        try {
            JsonReader reader = Json.createReader(new FileInputStream("country-by-capital-city.json"));
            JsonArray cityCountry = reader.readArray();

            JsonObjectBuilder cityBuilder= Json.createObjectBuilder();
            cityCountry.stream()
                .map(v -> v.asJsonObject())
                .forEach(v -> {
                    cityBuilder.add(v.getString("city"), "null");
                });
            JsonObject cities = cityBuilder.build();
            System.out.print(cities.toString());
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
            System.exit(1);
        }
    }
    
}

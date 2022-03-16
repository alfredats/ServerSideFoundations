package dev.alfredang.weather.weatherApp.service;

import java.util.Optional;

import javax.annotation.PostConstruct;

import com.jayway.jsonpath.PathNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import dev.alfredang.weather.weatherApp.model.LocationInfo;
import dev.alfredang.weather.weatherApp.repository.LocationInfoRepository;

@Service
public class WeatherService {
    Logger logger = LoggerFactory.getLogger(WeatherService.class);

    @Autowired
    private LocationInfoRepository liRepo;

    private static final String OPENWEATHER_URL = "http://api.openweathermap.org/";
    private String OPENWEATHER_KEY;

    @PostConstruct
    public void init() {
        this.OPENWEATHER_KEY = System.getenv("OPENWEATHER_KEY");
    }

    public Optional<LocationInfo> getWeatherByCity(String city) {
        String endpoint = "data/2.5/weather";
        LocationInfo locInfo = null;

        if (liRepo.existsById(city)) {
            logger.info(">>> " + city + " exists in cache, retrieving...");
            locInfo = liRepo.findById(city).orElse(null);
        } else {
            logger.info(">>> creating new record for " + city);
            String url = UriComponentsBuilder.fromUriString(OPENWEATHER_URL + endpoint)
                            .queryParam("q", city)
                            .queryParam("units","metric")
                            .queryParam("appid", OPENWEATHER_KEY)
                            .toUriString();
            RestTemplate restTemplate = new RestTemplate();

            try {
                ResponseEntity<String> resp = restTemplate.getForEntity(url, String.class);
                if (resp.getStatusCodeValue() == 200) {
                    locInfo = LocationInfo.create(city, resp.getBody());
                    liRepo.save(locInfo);
                    logger.info(">>> cached record for " + city + " at " + locInfo.getTimeUpdated());
                } else {
                    logger.info(">>> resp status: " + resp.getStatusCodeValue());
                    logger.debug(">>> resp body:" + resp.getBody());
                }
            } catch (RestClientException e) {
                logger.info(">>> Error: no such location \'" + city + "\'");
            } catch (PathNotFoundException e) {
                logger.info(">>> Error: JsonPath encountered exception due to \'" + e.getLocalizedMessage() + "\'");
                logger.debug(">>> Error details:" + e.getStackTrace().toString());
            }
        }

        return Optional.ofNullable(locInfo);
    }

    
}

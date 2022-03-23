package sg.edu.nus.iss.currencyconverter.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonException;
import jakarta.json.JsonObject;
import jakarta.json.JsonStructure;
import sg.edu.nus.iss.currencyconverter.model.Conversion;
import sg.edu.nus.iss.currencyconverter.model.Currency;

@Service
public class CurrencyConverterService {
    private static final Logger logger = LoggerFactory.getLogger(CurrencyConverterService.class);

    private static final String API_SERVER = "https://free.currconv.com/api/v7/";
    private String API_KEY = null;
    public Map<String, Currency> COUNTRY_CURRENCY_MAP;
    public List<String> COUNTRY_CURRENCY_MAP_KEYSET_SORTED;

    @PostConstruct
    private void init() {
        API_KEY = System.getenv("CURRCONV_KEY");
        COUNTRY_CURRENCY_MAP = initCCMap();
        COUNTRY_CURRENCY_MAP_KEYSET_SORTED = new ArrayList<String>(COUNTRY_CURRENCY_MAP.keySet());
        Collections.sort(COUNTRY_CURRENCY_MAP_KEYSET_SORTED);
    }

    private Map<String,Currency> initCCMap() {
        Map<String, Currency> ccMap = new HashMap<>();
        String url = UriComponentsBuilder.fromUriString(API_SERVER + "countries")
                        .queryParam("apiKey", this.API_KEY)
                        .toUriString();

        JsonStructure resp = invoke(url);
        JsonObject countryCurrCol = resp.asJsonObject().getJsonObject("results");

        for (String key : countryCurrCol.keySet()) {
            JsonObject countryData = countryCurrCol.getJsonObject(key);
            Currency currObj = Currency.create(countryData);
            String currID = countryData.getString("currencyId");  
            ccMap.put(currID, currObj);
        }

        if (ccMap.size() <= 0) {
            logger.error("Error: COUNTRY_CURRENCY_MAP not populated.");
            throw new IllegalArgumentException("COUNTRY_CURRENCY_MAP cannot be empty");
        }

        logger.info(">>> ccMap populated. Size: " + ccMap.size());
        return ccMap;
    }

    public Optional<Conversion> convertCurrency(String from, String to, String amount) {
        String fromToParam = from + "_" + to;
        String url = UriComponentsBuilder.fromUriString(API_SERVER + "convert")
                        .queryParam("q", fromToParam)
                        .queryParam("compact", "ultra")
                        .queryParam("apiKey", API_KEY)
                        .toUriString();
        
        logger.info("url: " + url);
        
        try {
            Double parsedAmount = Double.parseDouble(amount);
            JsonObject resp = invoke(url).asJsonObject();
            Conversion result = 
                Conversion.create(
                    COUNTRY_CURRENCY_MAP.get(from), 
                    COUNTRY_CURRENCY_MAP.get(to), 
                    parsedAmount, 
                    resp.getJsonNumber(fromToParam).doubleValue());
                logger.info("Conversion success: " + result.toString());
            return Optional.of(result);
        } catch (NumberFormatException e) {
            logger.error("Parser Error: " + e.getClass().getName());
            logger.error(e.getMessage());
        } catch (RestClientException e) {
            logger.error("Json Error: " + e.getClass().getName());
            logger.error(e.getMessage());
        } catch (JsonException e) {
            logger.error("Json Error: " + e.getClass().getName());
            logger.error(e.getMessage());
        } 
        return Optional.empty();
    }

    private static JsonStructure invoke(String url) throws RestClientException, JsonException, IllegalArgumentException {
        RestTemplate rt = new RestTemplate();
        RequestEntity<Void> req = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<String> resp = rt.exchange(req, String.class);
        if (resp.getStatusCodeValue() != 200) {
            logger.error(resp.getBody());
            throw new IllegalArgumentException("HTTP error: code " + resp.getStatusCodeValue());
        }
        return Json.createReader(new StringReader(resp.getBody())).read();

    }
    
}
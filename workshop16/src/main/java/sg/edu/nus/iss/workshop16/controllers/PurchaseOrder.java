package sg.edu.nus.iss.workshop16.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.json.Json;
import jakarta.json.JsonException;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

@Controller
@RequestMapping(path="/po")
public class PurchaseOrder {

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postPO(
        @RequestBody MultiValueMap<String, String> form
    ) {
        // data is the name of the <input type="text" name="data">
        final String data = form.getFirst("data");
        System.out.println(">>> POST data: " + data);
        try {
            InputStream is = new ByteArrayInputStream(data.getBytes());
            JsonReader reader = Json.createReader(is);
            // JsonArray json = reader.readArray();
            JsonObject json = reader.readObject();

            json.get("name");
            is.close();
        } catch (Exception ex) {

        }         
        return "index";
    }


    @GetMapping
    public String getPO() {
        JsonObjectBuilder objBuilder = Json.createObjectBuilder()
            .add("name", "fred");
        
        JsonObject obj = objBuilder.build();
        System.out.println(">>> obj: " + obj);
        
        return "index";
    }
    
}

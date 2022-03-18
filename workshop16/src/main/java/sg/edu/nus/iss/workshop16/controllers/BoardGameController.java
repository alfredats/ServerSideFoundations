package sg.edu.nus.iss.workshop16.controllers;

import java.io.StringReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.workshop16.services.BoardGameService;

@RestController
@RequestMapping(value="/api", produces=MediaType.APPLICATION_JSON_VALUE)
public class BoardGameController {
    Logger logger = LoggerFactory.getLogger(BoardGameController.class);
    
    @Autowired
    private BoardGameService bgSvc;
    
    @PostMapping(value = "/boardgame")
    public RequestEntity<String> insertBoardgame(@RequestBody String jsonString) {
        // i should probably sanitize the string
        JsonObject bgObj = Json.createReader(new StringReader(jsonString)).readObject();
        

        return null;
    }
    
}

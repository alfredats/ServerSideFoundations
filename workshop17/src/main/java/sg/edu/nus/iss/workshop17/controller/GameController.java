package sg.edu.nus.iss.workshop17.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.workshop17.repository.GameRepository;
import sg.edu.nus.iss.workshop17.service.GameService;

@RestController
@RequestMapping(path="/search", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameController {
    Logger logger = LoggerFactory.getLogger(GameController.class);

    @Autowired
    GameService gameSvc;

    @Autowired
    GameRepository gameRepo;

    @GetMapping("{gid}")
    public ResponseEntity<String> getGID(@PathVariable String gid) {
        Optional<String> hasObj = Optional.ofNullable(gameRepo.findByKey(gid));
        if (!hasObj.isEmpty()) {
            return ResponseEntity.ok()
                    .body(hasObj.get());
        } 
        JsonObject result = Json.createObjectBuilder()
                                .add("error", "gid{%s} not found".formatted(gid))
                                .build();
        // return ResponseEntity.noContent().build();
        return ResponseEntity.status(404).body(result.toString());
    }

    @GetMapping
    public ResponseEntity<String> searchPattern(@RequestParam String pattern) {
        Set<String> keySet = gameRepo.findKeys("*" + pattern + "*");
        logger.info(">>> keyset length: " + keySet.size());
        
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        keySet.stream()
            .sorted()
            .map(gid -> {
                return "/game/%s".formatted(gid);
            })
            .forEach(url -> {
                arrBuilder.add(url);
            });

        return ResponseEntity.ok().body(arrBuilder.build().toString());
    }
}

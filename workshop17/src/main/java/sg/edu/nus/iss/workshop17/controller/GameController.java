package sg.edu.nus.iss.workshop17.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.workshop17.repository.GameRepository;
import sg.edu.nus.iss.workshop17.service.GameService;

@RestController
@RequestMapping(path="/search", consumes = MediaType.APPLICATION_JSON_VALUE)
public class GameController {
    
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
        // return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<String> searchPattern(@RequestParam String pattern) {
        Set<String> keySet = gameRepo.findKeys("*" + pattern + "*");
        List<String> objArray = new ArrayList<>();
        for (String key : keySet) {
            objArray.add(gameRepo.findByKey(key));
        }
        return ResponseEntity.ok().body(objArray.toString());
    }
}

package sg.edu.nus.iss.deckOfCards.controller;

import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.deckOfCards.model.Deck;
import sg.edu.nus.iss.deckOfCards.service.DoCService;

@Controller
@RequestMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
public class DoCController {
    private static final Logger logger = 
        LoggerFactory.getLogger(DoCController.class);

    @Autowired
    private DoCService docSvc;

    @GetMapping
    public String indexResource() {
        return "index";
    }

    @PostMapping(path = "/deck")
    public String generate(Model model) {
        Deck d = docSvc.createDeck().orElse(new Deck());
        logger.info(">>> " + d.toString());
        model.addAttribute("deck", d);
        model.addAttribute("drawOptions", IntStream.rangeClosed(1, d.getRemaining()).toArray());
        return "DoC";
    }

    @PostMapping("/deck/{deckID}")
    public String drawCards(
        @PathVariable String deckID,
        @RequestBody MultiValueMap<String,String> reqBody,
        Model model
    ) {
        logger.info(">>> deck id: " + deckID);
        logger.info(">>> req body: " + reqBody);
        List<String> respCount = reqBody.get("number");

        if (respCount.size() != 1) {
            return "error";
        }
        Integer cnt = Integer.parseInt(respCount.get(0));
        
        Deck d = docSvc.drawCards(deckID, cnt).orElse(new Deck());
        model.addAttribute("deck", d);
        model.addAttribute("drawOptions", IntStream.rangeClosed(1, d.getRemaining()).toArray());
        return "DoC";

    }

    

    
}

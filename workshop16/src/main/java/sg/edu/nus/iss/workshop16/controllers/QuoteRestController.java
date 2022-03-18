package sg.edu.nus.iss.workshop16.controllers;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.workshop16.services.QuoteService;

@RestController
// does not conflict due to content negotiation 
// (because QuoteController produces text/html, this produces application/json)
@RequestMapping(path="/quote", produces=MediaType.APPLICATION_JSON_VALUE)
public class QuoteRestController {

    @Autowired
    private QuoteService quoteSvc;

    @GetMapping
    public ResponseEntity<String> getQuote(
        @RequestHeader(name="X-ID", required = false) String id,
        @RequestParam(name="count", defaultValue="1") Integer count
    ) {
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();        
        Collection<String> quotes = quoteSvc.getQuotes(count);

        quotes.stream()
            // .filter((String q) -> {
            //     return q.length() > 20;
            // })
            .map((String q) -> {
                return Json.createObjectBuilder()
                    .add("quote", q)
                    .add("timestamp", System.currentTimeMillis()) // from 1st January 1970, 12am
                    .build(); 
            })
            .forEach((JsonObject o) -> {
                arrBuilder.add(o);
            });
    
    //     for (String q: quotes) {
    //         JsonObject result = Json.createObjectBuilder()
    //             .add("quote", q)
    //             .add("timestamp", System.currentTimeMillis()) // from 1st January 1970, 12am
    //             .build(); 
    //         arrBuilder.add(result);
    //     }
        JsonArray quoteArray = arrBuilder.build();

        return ResponseEntity
                .ok()
                .header("X-ID", id)
                .header("X-My-Header", "Powered by SpringBoot")
                .body(quoteArray.toString());
    }

    // @GetMapping(path="/{numQuotes}")
    // public ResponseEntity<String> getQuote(@PathVariable String numQuotes) {
    //     Integer n = Integer.parseInt(numQuotes);
    //     JsonArrayBuilder quotes = Json.createArrayBuilder();
    //     for (int i = 0; i < n; i++) {
    //         quotes.add(i, quoteSvc.getQuote());
    //     }
    //     JsonObject result = Json.createObjectBuilder()
    //         .add("quotes", quotes.build())
    //         .build();

    //     return ResponseEntity
    //             .ok()
    //             .header("X-Number-Quotes", "" + n)
    //             .body(result.toString());
    // }
}

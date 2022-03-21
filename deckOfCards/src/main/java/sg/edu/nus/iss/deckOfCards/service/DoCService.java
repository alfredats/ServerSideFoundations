package sg.edu.nus.iss.deckOfCards.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sg.edu.nus.iss.deckOfCards.model.Deck;

@Service
public class DoCService {
    private static final String API_ROOT = "https://deckofcardsapi.com/api/deck/";
    private static final Logger logger = LoggerFactory.getLogger(DoCService.class);
    
    public Optional<Deck> createDeck() {
        String endpoint = UriComponentsBuilder
                            .fromUriString(API_ROOT + "new/shuffle/")
                            .queryParam("deck_count", 1)
                            .toUriString();
        try {
            return Optional.of(invoke(endpoint));
        } catch (Exception e) {
            logger.error(">>> Error: " + e.getClass());
            logger.error(">>> Stack trace:\n" + e.getMessage());
            logger.error(e.getStackTrace().toString());
            return Optional.empty();
        }
    }

    public Optional<Deck> drawCards(
        String deckId,
        Integer count
    ) {
        String endpoint = UriComponentsBuilder
                           .fromUriString(API_ROOT + deckId + "/draw/?count=" + count)
                           .toUriString();
        try {
            return Optional.of(invoke(endpoint));
        } catch (Exception e) {
            logger.error(">>> Error: " + e.getClass());
            logger.error(">>> Stack trace:\n" + e.getMessage());
            logger.error(e.getStackTrace().toString());
            return Optional.empty();
        }
    }

    private Deck invoke(String url) 
        throws Exception {
            logger.info(">>> Sending request to: " +url);
            RequestEntity<Void> req = RequestEntity.get(url)
                .accept(MediaType.APPLICATION_JSON)
                .build();

            RestTemplate template = new RestTemplate();
            ResponseEntity<String> resp = template.exchange(req, String.class);

            return Deck.create(resp.getBody());
    }

    
}

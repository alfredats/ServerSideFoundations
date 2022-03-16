package sg.edu.nus.iss.pokeApp.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sg.edu.nus.iss.pokeApp.model.Pokemon;

@Service
public class PokemonService {
    private Logger logger = LoggerFactory.getLogger(PokemonService.class);
    
    final private static String API_URL= "https://pokeapi.co/api/v2/";

    public Optional<Pokemon> getByName(String name) {
        Pokemon pkm = null; 

        String searchURI = UriComponentsBuilder
            .fromUriString(API_URL+"pokemon/"+name).toUriString();
        RestTemplate template = new RestTemplate();
        try {
            ResponseEntity<String> resp = template.getForEntity(searchURI, String.class);
            logger.info(">>> resp: " + resp.getStatusCodeValue());

            if (resp.getStatusCodeValue() == 200) {
                String respBody = resp.getBody();
                pkm = Pokemon.createPokemon(respBody);
            }
        } catch (RestClientException e) {
            logger.info(">>> error: " + e.getMessage());
            return Optional.empty();
        }

        return Optional.ofNullable(pkm);
    }
}

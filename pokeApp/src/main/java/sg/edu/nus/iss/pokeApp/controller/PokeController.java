package sg.edu.nus.iss.pokeApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sg.edu.nus.iss.pokeApp.model.Pokemon;
import sg.edu.nus.iss.pokeApp.service.PokemonService;

@Controller
@RequestMapping("/")
public class PokeController {
    // private Logger logger = LoggerFactory.getLogger(PokeController.class);

    @Autowired
    private PokemonService pkmSvc;

    @GetMapping("/search")
    public String searchResource(
        @RequestParam(name = "pokemon_name") String name,
        Model model
    ) {
        Pokemon pkm = pkmSvc.getByName(name).orElse(new Pokemon());

        model.addAttribute("name",name);
        model.addAttribute("sprites", pkm.getSpriteURLs());
        return "search";
    }
}

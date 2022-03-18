package sg.edu.nus.iss.workshop16.controllers;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.workshop16.services.QuoteService;

@Controller
@RequestMapping(path="/quote", produces=MediaType.TEXT_HTML_VALUE)
public class QuoteController {

    @Autowired
    private QuoteService quoteSvc;    

    @GetMapping
    public String getQuote(Model model) {
        model.addAttribute("quote", quoteSvc.getQuote());
        return "quote";
    }





    
}

package sg.edu.nus.iss.app.controller;


import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;
import java.util.logging.Level;



@Controller
@RequestMapping(path="/demo", produces=MediaType.TEXT_HTML_VALUE)
public class IndexController {
    public Logger logger = Logger.getLogger(IndexController.class.getName());

    @GetMapping(path="/currentTime", produces="text/html")
    public String currentTimeResource(Model model) {
        logger.log(Level.INFO, "currentTime called");
        model.addAttribute("currTime", (new Date()).toString());
        return "currentTime";
    }

    
}

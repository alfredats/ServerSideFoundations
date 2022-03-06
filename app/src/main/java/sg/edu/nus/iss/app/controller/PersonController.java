package sg.edu.nus.iss.app.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.logging.Level;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.app.model.Person;

@Controller
@RequestMapping(path="/demo/person", produces= MediaType.TEXT_HTML_VALUE)
public class PersonController {
    public static Logger logger = Logger.getLogger(PersonController.class.getName());
    public static HashMap<String,Person> allPeople = new HashMap<>();

    @GetMapping(path="/{id}", produces="text/html")
    public String personResource(@PathVariable(name="id",required=true) String id, Person person, Model model) {
        Person p;

        logger.log(Level.INFO, "directed to personResource");
        if (allPeople.containsKey(id)) {
            logger.log(Level.FINE, "person exists, assigning");
            p = allPeople.get(id);
        } else {
            logger.log(Level.FINE, "no such person, creating John Doe");
            p = new Person();
            p.set_id(Integer.parseInt(id));
            p.setFirstName("John");
            p.setLastName("Doe");
            p.setGender('m');

            allPeople.put(id, p);
        }
        
        model.addAttribute("currTime", (new Date()).toString());
        model.addAttribute("person", p);


        return "person";
    }

}

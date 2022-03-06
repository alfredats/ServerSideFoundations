package sg.edu.nus.iss.workshop13.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import sg.edu.nus.iss.workshop13.model.Contact;
import sg.edu.nus.iss.workshop13.util.Contacts;

@Controller
public class AddressBookController {
    private static final Logger logger = Logger.getLogger(AddressBookController.class.getName());
    @Autowired
    private ApplicationArguments appArgs;

    @GetMapping(path={"/","index.html"})
    public String contactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @GetMapping(value="/getContact/{contactId}")
    public String getContact(@PathVariable(value="contactId") String contactId, Model model) {
        logger.log(Level.INFO, "contactId > " + contactId);
        Contacts ct = new Contacts();
        ct.getContactById(model, contactId, appArgs);
        return "showContact";
    }

    @PostMapping("/contact")
    public String contactSubmit(@ModelAttribute Contact contact, Model model) {
        long startTime = System.currentTimeMillis();
        logger.log(Level.INFO, "[111] Name > " + contact.getName());
        logger.log(Level.INFO, "[111] Email > " + contact.getEmail());
        logger.log(Level.INFO, "[111] Phone Number > " + contact.getPhoneNumber());
        Contacts ct = new Contacts();
        ct.saveContact(contact, model, appArgs);
        long endTime = System.currentTimeMillis();
        logger.info("Elapsed timing -> contactSubmit " + (endTime - startTime));
        return "showContact";
    }

}

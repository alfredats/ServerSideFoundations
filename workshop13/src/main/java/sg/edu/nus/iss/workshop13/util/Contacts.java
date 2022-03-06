package sg.edu.nus.iss.workshop13.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.InvalidPathException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.ApplicationArguments;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

import sg.edu.nus.iss.workshop13.model.Contact;

public class Contacts {
    private static final Logger logger = Logger.getLogger(Contacts.class.getName());

    public void saveContact(Contact contact, Model model, ApplicationArguments appArgs) {
        String dataFilename = contact.getId();

        List<String> dirArr = appArgs.getOptionValues("dataDir");
        Path dataDir = Paths.get(dirArr.get(0));
        PrintWriter printWriter = null;

        try {
            logger.log(Level.INFO, "TRY BLOCK");

            Path outFilePath = dataDir.resolve(dataFilename);
            logger.log(Level.INFO, "beep boop i'm still working");

            FileWriter fileWriter = 
                new FileWriter(outFilePath.toFile(), Charset.forName("utf-8"));
            logger.log(Level.INFO,"filewriter!");

            printWriter = new PrintWriter(fileWriter);
            logger.log(Level.INFO,"printwriter!");

            printWriter.println(contact.getName());
            printWriter.println(contact.getEmail());
            printWriter.println(contact.getPhoneNumber());
        } catch (IOException e) {
            logger.log(Level.WARNING , e.getMessage());
        } catch (InvalidPathException e) {
            logger.log(Level.WARNING, e.getMessage());
            System.exit(1);
        } 
        finally {
                printWriter.close();
        }
        model.addAttribute("contact", new Contact(contact.getId(), contact.getName(), contact.getEmail(), contact.getPhoneNumber()));
}

    public void getContactById(Model model, String contactId, ApplicationArguments appArgs) {
        List<String> dirArr = appArgs.getOptionValues("dataDir");
        Path dataDir = Paths.get(dirArr.get(0));
        Contact cResp = new Contact();

        try {
            Path filePath = dataDir.resolve(contactId);
            Charset cs = Charset.forName("utf-8");
            List<String> strList = Files.readAllLines(filePath, cs);
            cResp.setId(contactId);
            cResp.setName(strList.get(0));
            cResp.setEmail(strList.get(1));
            cResp.setPhoneNumber(strList.get(2));
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found", e);
        }
        model.addAttribute("contact", cResp);
    }
}

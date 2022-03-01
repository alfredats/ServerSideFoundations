package sg.edu.nus.iss.workshop12.controller;


import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import sg.edu.nus.iss.workshop12.RandomNumberGenerator.RandomNumberGenerator;
import sg.edu.nus.iss.workshop12.exception.InvalidRangeArgumentException;
import sg.edu.nus.iss.workshop12.model.Generate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class GeneratorController {
	private static final Logger logger = LoggerFactory.getLogger(GeneratorController.class);

    @GetMapping(path={"/", "/index.html"}, produces = {"text/html"})
    public String indexPage(Model model) {
        Generate generate = new Generate();
        model.addAttribute("generateObj", generate);
        return "index";
    }

    @GetMapping(path="/generate")
    public String generateNumbers(@ModelAttribute Generate generate, Model model) {
        // the solution uses a try catch here; why not an if-else?
        try {
            logger.info("[FORM] numberVal > " + generate.getNumberVal());
            
            int numGen = generate.getNumberVal();


            if (numGen < 1 || numGen > 30) {
                throw new InvalidRangeArgumentException();
            } 
            RandomNumberGenerator randGen = new RandomNumberGenerator(numGen);
            ArrayList<String> imgs = new ArrayList<>();
            // obtain your images!
            for (Integer num : randGen.nonDupNumbers) {
                logger.info("currentElem > " + num);
                imgs.add("number" + num + ".jpg");
            }        

            model.addAttribute("numInputByUser", numGen);
            model.addAttribute("randNumsResult", imgs.toArray());
        } catch (InvalidRangeArgumentException e) {
            logger.error("[ERROR] InvalidRangeArgument:" + e);
            model.addAttribute("errorMessage", "Invalid Input! Use only a number between 1 and 30!");

            return "error";
        }

        return "generated";
    }
}

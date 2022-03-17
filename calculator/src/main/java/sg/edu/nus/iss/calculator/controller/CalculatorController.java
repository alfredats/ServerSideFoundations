package sg.edu.nus.iss.calculator.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.calculator.model.CalculateResult;
import sg.edu.nus.iss.calculator.service.CalculatorService;

@RestController
@RequestMapping(path="/calculate", produces=MediaType.APPLICATION_JSON_VALUE)
public class CalculatorController {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    @Autowired
    private CalculatorService cSvc;

    @PostMapping
    public ResponseEntity<String> calculateResource(
        @RequestHeader("User-Agent") String agent,
        @RequestBody String reqStr
    ) {
        logger.info(">>> agent: " + agent);
        logger.info(">>> body: " + reqStr);
        Optional<CalculateResult> cr = cSvc.calculateResponse(agent, reqStr);

        if (cr.isEmpty()) {
            return ResponseEntity.status(400).body(new CalculateResult().toString());
        } 

        return ResponseEntity.ok().body(cr.get().toString());
    }
    
}

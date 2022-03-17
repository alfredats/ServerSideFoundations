package sg.edu.nus.iss.calculator.service;

import java.io.StringReader;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.json.JsonException;
import sg.edu.nus.iss.calculator.model.CalculateResult;

@Service
public class CalculatorService {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    public Optional<CalculateResult> calculateResponse(String agent, String reqStr) {
        CalculateResult cr = null;
        try {
            cr = CalculateResult.create(agent, reqStr);
        } catch (JsonException e) {
            logger.error(">>> JsonException: " + e.getMessage());
        } catch (Exception e) {
            logger.error(">>> Exception:" + e.getMessage());
        }
        
        return Optional.ofNullable(cr);
    }
    
}

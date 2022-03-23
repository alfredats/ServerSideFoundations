package sg.edu.nus.iss.currencyconverter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.currencyconverter.model.Conversion;
import sg.edu.nus.iss.currencyconverter.service.CurrencyConverterService;

@Controller
@RequestMapping(path={"/", "index.html"}, produces=MediaType.TEXT_HTML_VALUE)
public class CurrencyConverterController {
    private static final Logger logger = 
        LoggerFactory.getLogger(CurrencyConverterController.class);
    @Autowired
    private CurrencyConverterService ccSvc;


    @GetMapping
    String indexResource(Model model) {
        model.addAttribute("sortedKeySet", ccSvc.COUNTRY_CURRENCY_MAP_KEYSET_SORTED);
        model.addAttribute("ccMap", ccSvc.COUNTRY_CURRENCY_MAP);
        return "index";
    }

    @GetMapping("/convert")
    String convertResource(
        @RequestParam String from,
        @RequestParam String to,
        @RequestParam String amount,
        Model model
    ) {
        logger.debug(">>> convertResource: %s, %s, %s".formatted(from, to, amount));
        Conversion ccResult = ccSvc.convertCurrency(from, to, amount).orElse(null);
        if (ccResult == null) {
            return "error";
        }
        model.addAttribute("sortedKeySet", ccSvc.COUNTRY_CURRENCY_MAP_KEYSET_SORTED);
        model.addAttribute("ccMap", ccSvc.COUNTRY_CURRENCY_MAP);
        model.addAttribute("ccResult", ccResult);

        return "index";
    }
}

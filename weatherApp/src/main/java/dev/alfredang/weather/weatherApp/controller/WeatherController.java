package dev.alfredang.weather.weatherApp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.alfredang.weather.weatherApp.model.LocationInfo;
import dev.alfredang.weather.weatherApp.service.WeatherService;

@Controller
@RequestMapping(path="/", produces = MediaType.TEXT_HTML_VALUE)
public class WeatherController {
    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private WeatherService wSvc;

    @GetMapping(path={"/", "index.html"})
    String indexResource(Model model) {
        return cityResource("singapore", model);
    }

    @GetMapping("/{city}")
    String cityResource(
        @PathVariable String city,
        Model model
    ) {
        LocationInfo lInfo = wSvc.getWeatherByCity(city).orElse(new LocationInfo());
        model.addAttribute("lInfo", lInfo);
        return "index";
    }

    @PostMapping(path={"/"}, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE) 
    public String forwardLanguage(@RequestParam String city) {
        String[] strArr = city.toLowerCase().split(" ");
        if (strArr.length > 1 && strArr[1].charAt(0) == '[') {
            String[] newStr = {strArr[0]};
            strArr = newStr;
        }
        String cityProcessed = String.join("+", strArr);

        logger.info(">>> redirecting to /" + cityProcessed);
        return "redirect:/" + cityProcessed;
    }

    
}

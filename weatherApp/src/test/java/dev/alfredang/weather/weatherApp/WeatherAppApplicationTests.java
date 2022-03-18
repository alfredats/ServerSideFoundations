package dev.alfredang.weather.weatherApp;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import dev.alfredang.weather.weatherApp.model.LocationInfo;
import dev.alfredang.weather.weatherApp.service.WeatherService;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherAppApplicationTests {

	@Autowired
	private WeatherService wSvc;

	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() {
		Optional<LocationInfo> opt = wSvc.getWeatherByCity("singapore");
		Assertions.assertTrue(opt.isPresent());
	}

	@Test
	void shouldReturnWeatherOfSingapore() throws Exception {
		// GET /city
		RequestBuilder req = MockMvcRequestBuilders.get("/singapore")
										.accept(MediaType.TEXT_HTML);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);

	}

}

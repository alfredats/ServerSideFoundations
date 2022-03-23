package sg.edu.nus.iss.currencyconverter;


import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.currencyconverter.model.Conversion;
import sg.edu.nus.iss.currencyconverter.model.Currency;
import sg.edu.nus.iss.currencyconverter.service.CurrencyConverterService;

@SpringBootTest
class CurrencyconverterApplicationTests {

	@Autowired
	private CurrencyConverterService ccSvc;

	@Test
	void contextLoads() {
		JsonObject t1Json = Json.createObjectBuilder()
								.add("currencyId", "test1")
								.add("currencyName", "test2")
								.add("currencySymbol", "T1.")
								.build();
		Currency t1Currency = Currency.create(t1Json);
		Assertions.assertTrue(t1Currency.getId() == "test1");

		Optional<Conversion> testConv = ccSvc.convertCurrency("USD", "SGD", "100");
		Assertions.assertTrue(testConv.isPresent());
	}

}

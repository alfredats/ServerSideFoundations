package sg.edu.nus.iss.workshop16;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.iss.workshop16.controllers.QuoteRestController;
import sg.edu.nus.iss.workshop16.services.QuoteService;

@SpringBootTest
class Workshop16ApplicationTests {

	@Autowired
	private QuoteService quoteSvc;

	@Autowired
	private QuoteRestController quoteRestCtrl;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(quoteSvc, "QuoteService is null");
		Assertions.assertNotNull(quoteRestCtrl, "QuoteRestController is null");
	}

	@Test
	void getQuotesShouldBeEqual() {
		int count = 4;
		Collection<String> result = quoteSvc.getQuotes(count);
		Assertions.assertEquals(count, result.size(), "getQuotes does not return expected count");
	}

}

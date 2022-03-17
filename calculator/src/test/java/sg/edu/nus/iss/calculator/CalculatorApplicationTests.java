package sg.edu.nus.iss.calculator;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.calculator.controller.CalculatorController;
import sg.edu.nus.iss.calculator.model.CalculateResult;
import sg.edu.nus.iss.calculator.service.CalculatorService;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorApplicationTests {

	@Autowired
	private CalculatorController cCtrl;

	@Autowired
	private CalculatorService cSvc;

	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() {
		String mockAgent = "Test Agent";
		String mockBody = "{\"oper1\":1,\"oper2\":1,\"ops\":\"plus\"}";
		Optional<ResponseEntity<String>> ctrl = Optional.ofNullable(cCtrl.calculateResource(mockAgent, mockBody));
		Optional<CalculateResult> opt = cSvc.calculateResponse(mockAgent, mockBody);
		Assertions.assertTrue(ctrl.isPresent());
		Assertions.assertTrue(opt.isPresent());
	}

	@Test
	void plus() throws Exception {
		JsonObject reqParams = Json.createObjectBuilder()
								.add("oper1", 1)
								.add("oper2", 2)
								.add("ops", "plus")
								.build();
		RequestBuilder req = MockMvcRequestBuilders.post("/calculate")
										.contentType(MediaType.APPLICATION_JSON_VALUE)
										.header("User-Agent", "MockMVC TEST")
										.content(reqParams.toString())
										.accept(MediaType.APPLICATION_JSON_VALUE);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	@Test
	void minus() throws Exception {
		JsonObject reqParams = Json.createObjectBuilder()
								.add("oper1", 1)
								.add("oper2", 2)
								.add("ops", "minus")
								.build();
		RequestBuilder req = MockMvcRequestBuilders.post("/calculate")
										.contentType(MediaType.APPLICATION_JSON_VALUE)
										.header("User-Agent", "MockMVC TEST")
										.content(reqParams.toString())
										.accept(MediaType.APPLICATION_JSON_VALUE);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	@Test
	void times() throws Exception {
		JsonObject reqParams = Json.createObjectBuilder()
								.add("oper1", 1)
								.add("oper2", 2)
								.add("ops", "multiply")
								.build();
		RequestBuilder req = MockMvcRequestBuilders.post("/calculate")
										.contentType(MediaType.APPLICATION_JSON_VALUE)
										.header("User-Agent", "MockMVC TEST")
										.content(reqParams.toString())
										.accept(MediaType.APPLICATION_JSON_VALUE);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}

	@Test
	void divide() throws Exception {
		JsonObject reqParams = Json.createObjectBuilder()
								.add("oper1", 1)
								.add("oper2", 2)
								.add("ops", "divide")
								.build();
		RequestBuilder req = MockMvcRequestBuilders.post("/calculate")
										.contentType(MediaType.APPLICATION_JSON_VALUE)
										.header("User-Agent", "MockMVC TEST")
										.content(reqParams.toString())
										.accept(MediaType.APPLICATION_JSON_VALUE);

		MvcResult result = mvc.perform(req).andReturn();
		int status = result.getResponse().getStatus();

		Assertions.assertEquals(200, status);
	}
}

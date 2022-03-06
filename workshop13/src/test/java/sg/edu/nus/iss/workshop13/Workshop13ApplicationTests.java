package sg.edu.nus.iss.workshop13;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.iss.workshop13.controller.AddressBookController;
import sg.edu.nus.iss.workshop13.model.Contact;

@SpringBootTest
class Workshop13ApplicationTests {
	@Autowired
	private AddressBookController controller;

	@Test
	void contextLoads() {
	}

	@Test
	public void testContact() throws Exception {
		Contact c = new Contact();
		c.setName("Testname");
		c.setEmail("test@test.com");
		c.setPhoneNumber("123456");
		Assertions.assertEquals(c.getEmail(), "test@test.com");
	}

}

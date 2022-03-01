package sg.edu.nus.iss.workshop11;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class Workshop11Application {
	private static final Logger logger = LoggerFactory.getLogger(Workshop11Application.class);
	private static final String DEFAULT_PORT = "3000";

	public static void main(String[] args) {
		String portNumber;

		logger.info("Workshop 11");
		logger.debug("Workshop 11 -- D");

		SpringApplication app = new SpringApplication(Workshop11Application.class);

		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);
		List optVals = appArgs.getOptionValues("port");

		logger.info("optVals > " + optVals);
		if (optVals == null || optVals.get(0) == null) {
			// portNumber = System.getProperty("system.port", DEFAULT_PORT);
			portNumber = (System.getenv("PORT") == null) ? DEFAULT_PORT : System.getenv("PORT");
		} else {
			portNumber = (String)optVals.get(0);
		}


		if (portNumber != null) {
			app.setDefaultProperties(Collections.singletonMap("server.port", portNumber));
		}

		app.run(args);
	}

}

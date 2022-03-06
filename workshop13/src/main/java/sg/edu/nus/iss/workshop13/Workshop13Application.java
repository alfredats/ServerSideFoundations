package sg.edu.nus.iss.workshop13;

import java.io.IOException;
import java.util.List;

import sg.edu.nus.iss.workshop13.util.IOUtil;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;
import java.util.logging.Level;

@SpringBootApplication
public class Workshop13Application {
	static Logger logger = Logger.getLogger(Workshop13Application.class.getName());

	public static void main(String[] args) {
		DefaultApplicationArguments appArgs = 
			new DefaultApplicationArguments(args);

		SpringApplication app = 
			new SpringApplication(Workshop13Application.class);

		try {
			List<String> dirArr = appArgs.getOptionValues("dataDir");
			if (dirArr == null || dirArr.size() != 1) {
				throw new IOException("Please specify a single output directory for --dataDir");
			} 
			IOUtil.createDir(dirArr.get(0));

		} catch (IOException e) {
			logger.log(Level.SEVERE, e.toString());
			System.exit(1);
		}
		
		app.run(args);
	}

}

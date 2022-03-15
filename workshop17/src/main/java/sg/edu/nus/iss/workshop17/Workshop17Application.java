package sg.edu.nus.iss.workshop17;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.workshop17.repository.GameRepository;
import sg.edu.nus.iss.workshop17.service.GameService;

@SpringBootApplication
public class Workshop17Application implements CommandLineRunner {
	Logger logger = LoggerFactory.getLogger(Workshop17Application.class);

	@Autowired
	private GameService gameSvc;

	@Autowired 
	private GameRepository gameRepo;

	public static void main(String[] args) {
		SpringApplication.run(Workshop17Application.class, args);
	}

	@Override
	public void run(String... args) {
		if (args.length <= 0) {
			System.err.println("Please pass in a JSON file to parse");
			System.exit(-1);
		}

		JsonArray games = null;
		try (FileInputStream fis = new FileInputStream(args[0])) {
			games = gameSvc.loadData(fis);
			System.out.println("size: " + games.size());
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}

		games.stream()
			.map(v -> (JsonObject)v)
			.forEach((JsonObject v) -> {
				gameRepo.save(v);
			});

		System.out.println("completed!");

		Set<String> keys = gameRepo.findKeys("*12*");
		for (String k: keys)
			System.out.printf(">> %s\n", k);

		// JsonReader siReader = Json.createReader(new InputStreamReader(System.in));
		// JsonArray items = null;
		// try {
		// 	items = siReader.readArray();
		// 	siReader.close();
		// } catch (JsonException e) {
		// 	logger.error(">>> ERROR : " + e.toString());
		// }

		// items.stream()
		// 	.filter((JsonValue o) -> {
		// 		return (instanceof o JsonObject);
		// 	})
		// 	.map((JsonObject x) -> {
		// 		gameRepo.save(x);
		// 	});

		// items.stream()
		// 	.map((x) -> {
		// 		return (JsonObject)x;
		// 	})
		// 	.forEach((JsonObject x) -> {
		// 		gameRepo.save(x);
		// 	});
	}

}

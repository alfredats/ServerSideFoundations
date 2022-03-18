package sg.edu.nus.iss.workshop16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonException;
import jakarta.json.JsonReader;
import jakarta.json.stream.JsonParsingException;

@SpringBootApplication
public class Workshop16Application implements CommandLineRunner {
	Logger logger = LoggerFactory.getLogger(Workshop16Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Workshop16Application.class, args);
	}

	@Override
	public void run(String... args) {
		JsonReader siReader = Json.createReader(new InputStreamReader(System.in));
		try {
			JsonArray items = siReader.readArray();
			siReader.close();
		} catch (JsonException e) {
			logger.error(">>> ERROR : " + e.toString());
		}
	}

}

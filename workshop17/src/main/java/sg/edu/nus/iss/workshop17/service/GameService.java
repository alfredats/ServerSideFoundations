package sg.edu.nus.iss.workshop17.service;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;

@Service
public class GameService {

	public JsonArray loadData(InputStream is) {
		JsonReader reader = Json.createReader(is);
		return reader.readArray();
	}


}
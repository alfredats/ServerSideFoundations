package sg.edu.nus.iss.workshop17.repository;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonObject;

@Repository
public class GameRepository {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

	public void save(JsonObject obj) {
		Integer gid = obj.getInt("gid");
		redisTemplate.opsForHash().put(
			"%d".formatted(gid), 
			"rec", 
			obj.toString());
	}

	public Set<String> findKeys(String pattern) {
        Set<String> keySet = new HashSet<>();
        ScanOptions options = ScanOptions.scanOptions().match(pattern).count(10).build();
        RedisConnection redisConnection = null;
        try {
            redisConnection = redisTemplate.getConnectionFactory().getConnection();
            Cursor c = redisConnection.scan(options);

            while (c.hasNext()) {
                keySet.add(new String((byte[]) c.next()));
            }
        } finally {
            redisConnection.close();
        }

		return keySet;
	}

    public String findByKey(String key) {
        return (String)redisTemplate.opsForHash().get(key, "rec");
    }
    
}

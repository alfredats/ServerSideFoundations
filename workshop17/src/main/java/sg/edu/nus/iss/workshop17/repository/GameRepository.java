package sg.edu.nus.iss.workshop17.repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonObject;

@Repository
public class GameRepository {
    Logger logger = LoggerFactory.getLogger(GameRepository.class);

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
		return redisTemplate.keys(pattern);
	}

	// public Set<String> findKeys(String pattern, Optional<Integer> skip, Optional<Integer> count)) {
    //     Integer startItem = skip.orElse(0);
    //     Integer countItems = count.orElse(10);

    //     Set<String> keySet = new HashSet<>();
    //     ScanOptions options = ScanOptions.scanOptions().match(pattern).count(10).build();
    //     RedisConnection redisConnection = null;

    //     try {
    //         redisConnection = redisTemplate.getConnectionFactory().getConnection();
    //         Cursor c = redisConnection.scan(options);

    //         // c.stream()
    //         //     .limit(countItems)
    //         //     .map((byte[] v) -> {
    //         //         String key = new String((byte[]) v);
    //         //         keySet.add(key);
    //         //     });
    //     } finally {
    //         redisConnection.close();
    //     }
    //     logger.info(">>> keyset size: " + keySet.size());
	// 	return keySet;
	// }

    public Optional<String> findByKey(String key) {
        Object result = redisTemplate.opsForHash().get(key, "rec");
        return (result == null) ? Optional.empty() : Optional.of((String)result);
    }
    
}

package dev.alfredang.weather.weatherApp.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    // @Value("${spring.redis.database}")
    // private int database;

    private String password;

    @PostConstruct
    public void init() {
        this.password = System.getenv("REDIS_PASSWORD");
    }


   @Bean
   JedisConnectionFactory jedisConnectionFactory() {
       RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(host);
        config.setPort(port);
        // config.setDatabase(database);
        config.setPassword(RedisPassword.of(password));
   
        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().build();
        return new JedisConnectionFactory(config, jedisClientConfiguration); 
   }

   @Bean
   public RedisTemplate<String,Object> redisTemplate() {
       RedisTemplate<String,Object> template = new RedisTemplate<>();
       template.setConnectionFactory(this.jedisConnectionFactory());
       return template;

   }

}
package sg.edu.nus.iss.mockSSF.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.mockSSF.model.Book;

@Repository
public class LibraryRepo {
    private static final String BOOK_ENTITY = "ALL_BOOKS";

    @Autowired
    private RedisTemplate<String,Book> redisTemplate;

    public List<Book> findAllBooks() {
        return null;
    }

    public Set<Book> findByAuthor(String author) {
        String authorProcessed = author.toLowerCase().strip();
        Set<Book> redisRet = redisTemplate.opsForSet()
            .members(authorProcessed);
        return redisRet;
    }

    public Book findByTitle(String title) {
        String titleProcessed = title.toLowerCase().strip();
        Book redisRet = (Book)redisTemplate.opsForHash()
            .get(BOOK_ENTITY, titleProcessed);
        return redisRet; 
    }
}

package sg.edu.nus.iss.mockSSF.model;

import java.util.List;
import java.util.UUID;

import org.springframework.data.redis.core.RedisHash;

@RedisHash(value="Query", timeToLive = 600L)
public class Query {
    private String id;
    private String author;
    private String title;
    private List<Book> results;

    public Query() { this.id = this.generateId();}

    private synchronized String generateId() {
        return UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public List<Book> getResults() {
        return results;
    }
    public void setResults(List<Book> results) {
        this.results = results;
    }

    public String printQuery() {
        String maybeTitle = (getTitle()==null) ? "<no title>" : getTitle();
        String maybeAuthor = (getAuthor()==null) ? "<no author>" : getAuthor();
        return "Query{%s, %s}".formatted(maybeTitle, maybeAuthor);
    }
}

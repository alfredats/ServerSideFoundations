package sg.edu.nus.iss.mockSSF.model;

import java.util.List;

public class Query {
    private String author;
    private String title;
    private List<Book> results;

    public Query() {}
    public Query(String author, String title) { this.author=author; this.title=title;}

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
}

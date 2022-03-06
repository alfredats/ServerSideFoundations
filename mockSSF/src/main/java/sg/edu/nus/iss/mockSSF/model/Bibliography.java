package sg.edu.nus.iss.mockSSF.model;

import java.util.HashSet;


public class Bibliography {
    private String Author;
    private HashSet<Book> published;

    public String getAuthor() {
        return Author;
    }
    public HashSet<Book> getPublished() {
        return published;
    }
    public void setPublished(HashSet<Book> published) {
        this.published = published;
    }
    public void setAuthor(String author) {
        this.Author = author;
    } 
}

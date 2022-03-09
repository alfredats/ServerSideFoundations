package sg.edu.nus.iss.mockSSF.model;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Book")
public class Book {
    private String id;
    private String thumbnailURI;
    private String title;
    private String author;

    public Book(String thumbnailURI, String title, String author) {
        this.id = title;
        this.thumbnailURI = thumbnailURI;
        this.title = title;
        this.author = author;
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
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getThumbnailURI() {
        return thumbnailURI;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnailURI = thumbnail;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book)) return false;
        Book bk = (Book) o;
        return (bk.title == this.title) && (bk.author == this.author);
    }

}

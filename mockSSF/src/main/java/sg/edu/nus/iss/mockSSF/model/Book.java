package sg.edu.nus.iss.mockSSF.model;

import java.util.Comparator;

public class Book {
    private String thumbnailURI;
    private String title;
    private String author;

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

    public static final Comparator<Book> TITLE_COMPARATOR = new Comparator<Book>() {
        public int compare(Book b1, Book b2) {
            return b1.title.compareToIgnoreCase(b2.title);
        }
    };

    public static final Comparator<Book> AUTHOR_COMPARATOR = new Comparator<Book>() {
        public int compare(Book b1, Book b2) {
            return b1.author.compareToIgnoreCase(b2.author);
        }
    };
}

package sg.edu.nus.iss.mockSSF.model;

import java.util.Comparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookUtil {
    Logger logger = LoggerFactory.getLogger(Book.class);

    public int querySimilarity(Book b, Query q) {
        if (b.getTitle() == null || b.getAuthor() == null) {
            logger.error("Book has missing attributes");
            throw new IllegalArgumentException();
        }
        if (q == null) {
            logger.error("Empty query used for comparison");
            throw new NullPointerException();
        }
        int diff = 0;
        if (q.getTitle() != null) {
            diff += q.getTitle().compareTo(b.getTitle());
        }
        if (q.getAuthor() != null) {
            diff += q.getAuthor().compareTo(b.getAuthor());
        }
        return diff;
    }

    public static final Comparator<Book> TITLE_COMPARATOR = new Comparator<Book>() {
        public int compare(Book b1, Book b2) {
            return b1.getTitle().compareToIgnoreCase(b2.getTitle());
        }
    };

    public static final Comparator<Book> AUTHOR_COMPARATOR = new Comparator<Book>() {
        public int compare(Book b1, Book b2) {
            return b1.getAuthor().compareToIgnoreCase(b2.getAuthor());
        }
    };
}

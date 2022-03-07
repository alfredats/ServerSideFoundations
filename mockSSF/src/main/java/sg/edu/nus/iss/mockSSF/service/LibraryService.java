package sg.edu.nus.iss.mockSSF.service;

import java.util.List;

import sg.edu.nus.iss.mockSSF.model.Book;
import sg.edu.nus.iss.mockSSF.model.Query;

public interface LibraryService {
    void saveBook(Book book);
    List<Book> findByQuery(Query q);
}

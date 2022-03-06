package sg.edu.nus.iss.mockSSF.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.mockSSF.model.Book;
import sg.edu.nus.iss.mockSSF.model.Query;

@Service
public class LibraryService {
    private static final Logger logger = LoggerFactory.getLogger(LibraryService.class);
    @Autowired
    private LibraryRepo library;

    public List<Book> findByQuery(Query query) {
        Set<Book> singleton = null; 
        Set<Book> bib = new HashSet<>();

        if (query.getAuthor() != null) {
            bib = library.findByAuthor(query.getAuthor());
            logger.info("redis has result for author " + query.getAuthor() + " > " + !bib.isEmpty());
        }
        if (query.getTitle() != null) {
            singleton = Collections.singleton(library.findByTitle(query.getTitle()));
            logger.info("redis has result for title " + query.getTitle() + " > " + (singleton != null));
        }
        
        ArrayList<Book> bookList = new ArrayList<Book>();        

        if (singleton != null) {
            bookList.addAll(singleton);
            bib.removeAll(singleton);
        }
        bookList.addAll(bib);

        return bookList;
    }

}

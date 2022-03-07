package sg.edu.nus.iss.mockSSF.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.mockSSF.model.Book;
import sg.edu.nus.iss.mockSSF.model.Query;

@Service
public class LibraryServiceImpl implements LibraryService {
    Logger logger = LoggerFactory.getLogger(LibraryServiceImpl.class);
    public static final int PAGE_SIZE = 10;

    @Autowired
    private LibraryRepo libRepo;

    @Override
    public void saveBook(Book book) {
        // empty
    }

    @Override
    public List<Book> findByQuery(Query query) {
        Set<Book> singleton = null; 
        Set<Book> bib = new HashSet<>();

        if (query.getAuthor() != null) {
            bib = libRepo.findByAuthor(query.getAuthor());
            logger.info("redis has result for author " + query.getAuthor() + " > " + !bib.isEmpty());
        }
        if (query.getTitle() != null) {
            singleton = Collections.singleton(libRepo.findByTitle(query.getTitle()));
            logger.info("redis has result for title " + query.getTitle() + " > " + (singleton != null));
        }
        
        List<Book> bookList = Collections.emptyList(); 

        if (singleton != null) {
            bookList.addAll(singleton);
            bib.removeAll(singleton);
        }
        bookList.addAll(bib);

        return bookList;
    }

    // public Page<Book> findPaginated(Pageable pageable, Query q, String sortField, String sortDirection) {
    public Page<Book> findPaginated(Pageable pageable, Query q) {
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * PAGE_SIZE;

        if (q.getResults() == null) {
            q.setResults(this.findByQuery(q));
        } 
        int toIndex = Math.min(startItem + PAGE_SIZE, q.getResults().size());
        List<Book> list = q.getResults().subList(startItem, toIndex);

        Page<Book> bookPage = new PageImpl<Book>(list, PageRequest.of(currentPage, PAGE_SIZE), q.getResults().size());

        return bookPage;
    }
    
}

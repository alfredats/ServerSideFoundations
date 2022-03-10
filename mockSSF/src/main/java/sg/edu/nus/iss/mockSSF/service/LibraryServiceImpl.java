package sg.edu.nus.iss.mockSSF.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
        libRepo.save(book);
    }

    public List<Book> findAllBooks() {
        Iterable<Book> bks = libRepo.findAll();
        Stream<Book> stream = StreamSupport.stream(bks.spliterator(), false);
        
        return stream.filter(Book.class::isInstance).toList();
    }

    public Set<Book> findByAuthor(String author) {
        Stream<Book> stream = StreamSupport.stream(libRepo.findAll().spliterator(), false);
        Set<Book> redisRet = stream.filter(x -> x.getAuthor().equals(author)).collect(Collectors.toSet());
        return redisRet;
    }

    public Book findByTitle(String title) {
        Optional<Book> bk = libRepo.findById(title);
        return bk.orElse(null);
    }

    @Override
    public List<Book> findByQuery(Query query) {
        Book singleton = null; 
        Set<Book> bib = new HashSet<>();
        List<Book> bookList = new ArrayList<Book>(); 

        if (query.getAuthor() != null) {
            bib = this.findByAuthor(query.getAuthor());
            logger.info("redis has result for author " + query.getAuthor() + " > " + !bib.isEmpty());
        }
        if (query.getTitle() != null) {
            singleton = this.findByTitle(query.getTitle());
            logger.info("redis has result for title " + query.getTitle() + " > " + (singleton != null));
        }
        if (singleton != null) {
            Set<Book> temp = Collections.singleton(singleton);
            bookList.addAll(temp);
            bib.removeAll(temp);
        }
        bookList.addAll(bib);

        return bookList;
    }

    // public Page<Book> findPaginated(Pageable pageable, Query q, String sortField, String sortDirection) {
    public Page<Book> findPaginated(Pageable pageable, Query q) {
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * PAGE_SIZE;

        if (q.getTitle() == null && q.getAuthor() == null) {
            logger.info("Populating initial pageload with all books");
            q.setResults(this.findAllBooks());
        }
        else {
        // if (q.getResults() == null) { 
            logger.info("Adding query results for " + q.printQuery());
            q.setResults(this.findByQuery(q));
        } 
        int toIndex = Math.min(startItem + PAGE_SIZE, q.getResults().size());
        List<Book> list = q.getResults().subList(startItem, toIndex);

        Page<Book> bookPage = new PageImpl<Book>(list, PageRequest.of(currentPage, PAGE_SIZE), q.getResults().size());

        return bookPage;
    }
    
}

package sg.edu.nus.iss.mockSSF.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.mockSSF.model.Book;
import sg.edu.nus.iss.mockSSF.model.Query;
import sg.edu.nus.iss.mockSSF.service.LibraryService;

@Controller
public class LibraryController {
    private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

    @Autowired
    private LibraryService library;

    @GetMapping(path={"/", "/index.html"})
    public String landingResource(Model model) {
        Query q = new Query();
        model.addAttribute("query", q);
        return "index";
    }

    @GetMapping(value="/search")
    public String search(
        @RequestParam Optional<String> author, 
        @RequestParam Optional<String> title, 
        @RequestParam Optional<Boolean> sortByAuthor,
        @RequestParam Optional<Boolean> sortByTitle,
        Model model) 
    {
        Query q = new Query();
        if (author.isPresent()) {
            logger.info("search:author > " + author);
            q.setAuthor(author.get());
        }
        if (title.isPresent()) {
            logger.info("search:title  > " + title);
            q.setTitle(title.get());
        }

        List<Book> bib =  library.findByQuery(q);

        if (sortByAuthor.get() != null) {
            Collections.sort(bib, Book.AUTHOR_COMPARATOR);
            if (sortByAuthor.get() == false)  {
                Collections.reverse(bib);
            }
        }

        if (sortByTitle.get() != null) {
            Collections.sort(bib, Book.TITLE_COMPARATOR);
            if (sortByTitle.get() == false)  {
                Collections.reverse(bib);
            }
        }
        model.addAttribute("query", q);
        model.addAttribute("biblio", bib);
        return "result";
    }
}

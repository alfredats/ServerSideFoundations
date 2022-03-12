package sg.edu.nus.iss.mockSSF.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.mockSSF.model.Book;
import sg.edu.nus.iss.mockSSF.model.Query;
import sg.edu.nus.iss.mockSSF.service.LibraryServiceImpl;

@Controller
public class LibraryController {
    private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);
    private static final Integer[] sizeOptions = {3,5,10};
    private static final Integer PAGE_SIZE = 10;

    @Autowired
    private LibraryServiceImpl library;

    @GetMapping(path={"/", "/index.html"})
    public String viewIndex(Model model) {
        Query q = new Query();
        library.saveQuery(q);

        return search(model, q, q.getId(), Optional.empty(), Optional.empty(), Optional.empty());
    }

    @RequestMapping(value="/search/{queryId}")
    public String search(
        Model model, Query q,
        @PathVariable("queryId") String queryId,
        @RequestParam("page") Optional<Integer> page,
        @RequestParam("size") Optional<Integer> size,
        @RequestParam("sortBy") Optional<String> sortBy
    ) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(PAGE_SIZE);
        final String sortStr = sortBy.orElse("none");
        Query existing = library.findQuery(queryId).orElse(null);

        if (existing != null) {
            if (q.getTitle() == null && q.getAuthor() == null) {
                logger.info("Changing page characteristics...");
            } else {
                logger.info("Database has existing query, overwriting existing...");
                existing.setTitle(q.getTitle());
                existing.setAuthor(q.getAuthor());
            }
        } else {
            logger.info("Database does not have existing query, creating new...");
            q.setId(queryId);
            existing = q;
        }
        logger.info("query: " + existing.printQuery());

        
        Page<Book> bookPage = library.findPaginated(existing, currentPage-1, pageSize, sortStr);

        logger.info("Page Size requested: " + pageSize);
        logger.info("Sort by requested: " + sortStr);
        logger.info("Current page: " + currentPage);
        logger.info("Total pages available: " + bookPage.getTotalPages());

        model.addAttribute("query", existing);
        model.addAttribute("sizeOptions", sizeOptions);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("sortBy", sortStr);
        model.addAttribute("bookPage", bookPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", bookPage.getTotalPages());
        logger.info(" ");

        return "index";
    }
}

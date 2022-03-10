package sg.edu.nus.iss.mockSSF.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

        // final int currentPage = page.orElse(1);
        // final int pageSize = size.orElse(PAGE_SIZE);

        // int currentPage = 1;
        // int pageSize = PAGE_SIZE;
        // Page<Book> bookPage = library.findPaginated(PageRequest.of(currentPage-1, pageSize), q);

        // logger.info("Current page: " + currentPage);
        // logger.info("Total pages available: " + bookPage.getTotalPages());

        // model.addAttribute("bookPage", bookPage);
        // model.addAttribute("pageSize", pageSize);
        // model.addAttribute("currentPage", currentPage);
        // model.addAttribute("totalPages", bookPage.getTotalPages());

        return search(model, q, q.getId(), Optional.empty(), Optional.empty(), Optional.empty());
    }

    @RequestMapping(value="/search/{queryId}")
    public String search(
        Model model, Query q,
        @PathVariable("queryId") String queryId,
        @RequestParam("page") Optional<Integer> page,
        @RequestParam("size") Optional<Integer> size,
        @RequestParam("sortByAuthor") Optional<String> sortBy
    ) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(PAGE_SIZE);
        final String sortDir = sortBy.orElse("");

        Query existing = library.findQuery(queryId);
        if (existing != null) {
            existing.setTitle(q.getTitle());
            existing.setAuthor(q.getAuthor());
            q = existing;
            logger.info("Database has existing query, discarding created");
        } else {
            return "error";
        }


        Page<Book> bookPage = library.findPaginated(PageRequest.of(currentPage-1, pageSize), q);

        logger.info("Page Size requested: " + pageSize);
        logger.info("Sort by requested: " + sortDir);
        logger.info("Current page: " + currentPage);
        logger.info("Total pages available: " + bookPage.getTotalPages());

        model.addAttribute("query", q);
        model.addAttribute("sizeOptions", sizeOptions);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("bookPage", bookPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", bookPage.getTotalPages());

        return "index";
    }
}

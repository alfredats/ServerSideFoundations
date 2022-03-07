package sg.edu.nus.iss.mockSSF.controller;

import java.util.Collections;
import java.util.Comparator;
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
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.mockSSF.model.Book;
import sg.edu.nus.iss.mockSSF.model.Query;
import sg.edu.nus.iss.mockSSF.service.LibraryServiceImpl;
import static sg.edu.nus.iss.mockSSF.service.LibraryServiceImpl.PAGE_SIZE;

@Controller
public class LibraryController {
    private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

    @Autowired
    private LibraryServiceImpl library;

    @GetMapping(path={"/", "/index.html"})
    public String landingResource(Model model) {
        Query q = new Query();
        model.addAttribute("query", q);
        return "index";
    }

    @GetMapping(value="/search")
    public String search(
        @RequestParam("author") Optional<String> author, 
        @RequestParam("title") Optional<String> title, 
        @RequestParam("page") Optional<Integer> page,
        @RequestParam("size") Optional<Integer> size,
        @RequestParam("sortByAuthor") Optional<Boolean> sortByAuthor,
        @RequestParam("sortByTitle") Optional<Boolean> sortByTitle,
        Model model) 
    {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(PAGE_SIZE);
        Query q = new Query();
        if (author.isPresent()) {
            logger.info("search:author > " + author);
            q.setAuthor(author.get());
        }
        if (title.isPresent()) {
            logger.info("search:title  > " + title);
            q.setTitle(title.get());
        }
        model.addAttribute("query", q);

        Page<Book> bookPage = library.findPaginated(PageRequest.of(currentPage-1, pageSize), q);
        model.addAttribute("bookPage", bookPage);

        int totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "result";
    }
}

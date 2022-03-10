package sg.edu.nus.iss.mockSSF.controller;

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

        // final int currentPage = page.orElse(1);
        // final int pageSize = size.orElse(PAGE_SIZE);

        int currentPage = 1;
        int pageSize = PAGE_SIZE;
        Page<Book> bookPage = library.findPaginated(PageRequest.of(currentPage-1, pageSize), q);
        model.addAttribute("bookPage", bookPage);

        int totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "index";
    }

    @PostMapping(value="/search/{queryId}")
    public String search(
        @PathVariable("queryId") String queryId,
        @RequestParam("page") Optional<Integer> page,
        @RequestParam("size") Optional<Integer> size,
        @RequestParam("sortByAuthor") Optional<String> sortBy,
        Model model, Query q) 
    {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(PAGE_SIZE);

        Page<Book> bookPage = library.findPaginated(PageRequest.of(currentPage-1, pageSize), q);
        model.addAttribute("bookPage", bookPage);

        // int totalPages = bookPage.getTotalPages();
        // if (totalPages > 0) {
        //     List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
        //         .boxed()
        //         .collect(Collectors.toList());
        //     model.addAttribute("pageNumbers", pageNumbers);
        // }

        return "result";
    }
}

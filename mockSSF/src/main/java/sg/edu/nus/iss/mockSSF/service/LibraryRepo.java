package sg.edu.nus.iss.mockSSF.service;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.mockSSF.model.Book;

@Repository
public interface LibraryRepo extends PagingAndSortingRepository<Book, String>{
}

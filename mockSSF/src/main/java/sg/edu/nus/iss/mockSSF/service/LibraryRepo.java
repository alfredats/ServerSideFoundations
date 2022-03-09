package sg.edu.nus.iss.mockSSF.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.mockSSF.model.Book;

@Repository
public interface LibraryRepo extends CrudRepository<Book, String>{
}

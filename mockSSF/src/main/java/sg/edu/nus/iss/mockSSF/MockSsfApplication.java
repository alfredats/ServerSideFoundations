package sg.edu.nus.iss.mockSSF;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.edu.nus.iss.mockSSF.model.Book;
import sg.edu.nus.iss.mockSSF.service.LibraryRepo;

@SpringBootApplication
public class MockSsfApplication {
	private static final Logger logger = LoggerFactory.getLogger(MockSsfApplication.class);
	
	@Autowired
	private LibraryRepo libRepo;

	public static void main(String[] args) {
		SpringApplication.run(MockSsfApplication.class, args);
	}

	@Bean
	CommandLineRunner run() {
		return (args) -> {
			Book b00 = new Book("no_book_cover.jpg","The Paris Apartment", "Lucy Foley");
			Book b01 = new Book("no_book_cover.jpg","Dune", "Frank Herbert");
			Book b02 = new Book("no_book_cover.jpg","Foundation", "Isaac Asimov");
			Book b03 = new Book("no_book_cover.jpg","Foundation and Empire", "Isaac Asimov");
			Book b04 = new Book("no_book_cover.jpg","Ender's Game", "Orson Scott Card");
			Book b05 = new Book("harry-potter-p-stone.jpg","Harry Potter and the Philosopher's Stone", "J.K. Rowling");
			Book b06 = new Book("no_book_cover.jpg","Harry Potter and the Chamber of Secrets", "J.K. Rowling");
			Book b07 = new Book("no_book_cover.jpg","Harry Potter and the Prisoner of Azkaban", "J.K. Rowling");
			Book b08 = new Book("no_book_cover.jpg","Harry Potter and the Goblet of Fire", "J.K. Rowling");
			Book b09 = new Book("no_book_cover.jpg","Harry Potter and the Order of the Phoenix", "J.K. Rowling");
			Book b10 = new Book("no_book_cover.jpg","Harry Potter and the Half-Blood Prince", "J.K. Rowling");
			Book b11 = new Book("ella_the_rose_fairy.jpg","Ella the Rose Fairy", "Daisy Meadows");
			Book b12 = new Book("the_haunted_tower.jpg","The Haunted Tower", "Susannah Leigh");
			Book b13 = new Book("no_book_cover.jpg", "The Alchemist", "Palo Coelho");
			Book b14 = new Book("no_book_cover.jpg", "How to win friends and influence people", "Dale Carnegie");
			Book b15 = new Book("no_book_cover.jpg", "Da Vinci Code", "Dan Brown");
			Book b16 = new Book("no_book_cover.jpg", "Angles & Demons", "Dan Brown");
			Book b17 = new Book("no_book_cover.jpg", "The Lost Symbol", "Dan Brown");
			Book b18 = new Book("no_book_cover.jpg", "Digital Fortress", "Dan Brown");
			Book b19 = new Book("no_book_cover.jpg", "Inferno", "Dan Brown");
			Book b20 = new Book("no_book_cover.jpg", "Origin", "Dan Brown");
			
			libRepo.saveAll(Arrays.asList(
				b00,b01,b02,b03,b04,b05,b06,b07,b08,b09,b10,
				b11,b12,b13,b14,b15,b16,b17,b18,b19,b20
			));

			logger.info("pre-populated redis database with books");
		};
	}
}

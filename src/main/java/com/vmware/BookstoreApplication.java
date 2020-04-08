package com.vmware;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.vmware.models.Book;
import com.vmware.repos.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	
	
	@Bean
    CommandLineRunner init (BookRepository bookRepo){
		
		Book b1 = new Book(1, "book-1", "Awaken the Giant Within", "Anthony Robbins");
		Book b2 = new Book(2, "book-2", "The Power of Positive Thinking", "Norman Vincent Peale");
        return args -> {
            List<Book> books = Arrays.asList(b1,b2);
            books.forEach(book -> bookRepo.save(book));
            
        };
    }

}

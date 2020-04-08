package com.vmware.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vmware.models.Book;
import com.vmware.models.InsertBook;
import com.vmware.services.BookService;

@RestController
@RequestMapping("/api")
public class BookApiController {
	Logger log = LoggerFactory.getLogger(getClass().getName());
	

	@Autowired
	private BookService bookService;
	
	@GetMapping("/appstatus")
	public String appStatus() {
		return "App is running";
	}
	
	@GetMapping("/books")
	public List<Book> getBooks() {
		return bookService.getBooks();
	}
	
	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@Valid @RequestBody InsertBook book) {
		log.info(book.toString());
		Book b1 = bookService.addBook(book);
		return ResponseEntity.ok().body(b1);
	}
	
	@GetMapping("/books/{bookId}")
	public ResponseEntity<Book> getBook(@PathVariable String bookId )  {
		Optional<Book> b1 =  bookService.getBook(bookId);
		if (b1.isPresent()) {
			
			return ResponseEntity.ok().body(b1.get());
		}
		else {
			return  ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/books/{bookId}")
	public ResponseEntity<Book> updBook(@PathVariable String bookId, @Valid @RequestBody InsertBook book ) {
		
		Optional<Book> b1 =  bookService.updBook(bookId, book);
		if(b1.isPresent()) {
			return ResponseEntity.ok().body(b1.get());
		} else {
			return  ResponseEntity.notFound().build();
		}
		
	}
	
	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<String> deleteBook(@PathVariable String bookId ) {
		Optional<Book> b1 = bookService.deleteBook(bookId);
		HttpHeaders headers = new HttpHeaders();
		if(b1.isPresent()) {
			return new ResponseEntity<String>("delete succeeded",headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("delete failed", headers, HttpStatus.NOT_FOUND);
		}
		
	}
	

}

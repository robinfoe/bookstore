package com.vmware.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmware.models.Book;
import com.vmware.models.InsertBook;
import com.vmware.repos.BookRepository;

@Service
public class BookService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BookRepository bookRepo;

	public Book addBook(InsertBook book) {
		String id = UUID.randomUUID().toString();
		Book addBook = new Book();
		addBook.setBookid(id);
		addBook.setTitle(book.getTitle());
		addBook.setAuthor(book.getAuthor());
		log.info("ADD BOOK : "+addBook.toString());
		return bookRepo.save(addBook);
	}

	public List<Book> getBooks() {
		return (List<Book>) bookRepo.findAll();
	}
	
	public Optional<Book> getBook(String bookId) throws NoSuchElementException {
		Optional<Book> b1 = (Optional<Book>) bookRepo.findByBookid(bookId);
		b1.orElse(null);
		log.info("GET BOOK : "+b1.toString());
		return  b1;
	}
	
	public Optional<Book> updBook(String bookId, InsertBook book) throws NoSuchElementException {
		Optional<Book> b1 = (Optional<Book>) bookRepo.findByBookid(bookId);
		if(b1.isPresent()) {
			b1.get().setTitle(book.getTitle());
			b1.get().setAuthor(book.getAuthor());
			bookRepo.save(b1.get());
		}
		b1.orElse(null);
		log.info("UPDATED BOOK : "+b1.toString());
		return b1;
	}
	public Optional<Book> deleteBook(String bookId) throws NoSuchElementException {
		Optional<Book> b1 = (Optional<Book>) bookRepo.findByBookid(bookId);
		if(b1.isPresent()) {
			bookRepo.delete(b1.get());
			log.info("DELETED BOOK : "+b1.toString());
			return b1;
		}
		b1.orElse(null);
		log.info("DELETED BOOK : "+b1.toString());
		return b1;
		
		
	}
}

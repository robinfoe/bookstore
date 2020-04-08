package com.vmware.controllers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vmware.models.Book;
import com.vmware.models.InsertBook;
import com.vmware.services.BookService;

@Controller
public class BookUIController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BookService bookService;

	@GetMapping(value = {"/", "/index"})
	public String index(Model model, HttpServletRequest request) throws MalformedURLException {
		
		URL url = new URL(request.getRequestURL().toString());
		
		String host  = url.getHost();
	    String userInfo = url.getUserInfo();
	    String scheme = url.getProtocol();
	    int port = url.getPort();
	    //log.info("VALUES"+host+userInfo+scheme+port);
		
		InsertBook book = new InsertBook();
		List<Book> books = bookService.getBooks();
		int count = books.size();
		//model.addAttribute("add", true);
		model.addAttribute("deleteAction", false);
		model.addAttribute("book", book);
		model.addAttribute("books", books);
		model.addAttribute("host", host);
		model.addAttribute("scheme", scheme);
		model.addAttribute("port", String.valueOf(port));
		model.addAttribute("bookCount", count);
		
		return "index";
	}

	@PostMapping("/index")
	public String addBook(@Valid @ModelAttribute("book") InsertBook book, BindingResult bindingResult,
			Model model,RedirectAttributes redirect) {
		
		if (!bindingResult.hasErrors()) {
			model.addAttribute("noErrors", true);
		}
	    try {
	    	Book b1 = bookService.addBook(book);
	    	redirect.addFlashAttribute("addSuccess", "Book Added..!" );
	        return "redirect:/index"; // + String.valueOf(newNote.getId());
	    } catch (Exception ex) {
	        // log exception first, 
	        // then show error
	        String errorMessage = ex.getMessage();
	        log.error(errorMessage);
	        model.addAttribute("errorMessage", errorMessage);
	        //model.addAttribute("add", true);
	        return "index";
	    }   

	}
	
	@GetMapping(value = "/books/{bookid}/delete")
	public String DeleteBookById(
	        Model model, @PathVariable String bookid, RedirectAttributes redirAttrs) {
	   
		Optional<Book> delBook = null;
		try {
			 delBook = bookService.deleteBook(bookid);
	    } catch (Exception ex) {
	        model.addAttribute("errorMessage", ex.getMessage());
	    }
		String bookTitle = delBook.get().getTitle();
		
		redirAttrs.addFlashAttribute("deleteMsg", "Deleted : Book Title - " +bookTitle);
	    return "redirect:/index";

	}
	
	@PostMapping(value = "/books/{bookid}")
	public String editBook(@Valid @ModelAttribute("book") InsertBook book,@PathVariable String bookid, BindingResult bindingResult,
			Model model,RedirectAttributes redirect) {
		
		if (!bindingResult.hasErrors()) {
			model.addAttribute("noErrors", true);
		}
	    try {
	    	bookService.updBook(bookid, book);
	    	redirect.addFlashAttribute("editSuccess", "Book Updated..!" );
	        return "redirect:/index"; // + String.valueOf(newNote.getId());
	    } catch (Exception ex) {
	        // log exception first, 
	        // then show error
	        String errorMessage = ex.getMessage();
	        log.error(errorMessage);
	        model.addAttribute("errorMessage", errorMessage);
	        //model.addAttribute("add", true);
	        return "index";
	    }   

	}
	
	



}

package com.vmware.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "books")
public class Book implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String bookid;
	@NotBlank
	private String title;
	@NotBlank
	private String author;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookid() {
		return bookid;
	}

	public void setBookid(String bookid) {
		this.bookid = bookid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Book(int id, String bookid, String title, String author) {
		super();
		this.id = id;
		this.bookid = bookid;
		this.title = title;
		this.author = author;
	}
	
	

	

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookid=" + bookid + ", title=" + title + ", author=" + author + "]";
	}

	public Book() {
		super();
	}

}

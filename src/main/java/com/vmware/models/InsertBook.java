package com.vmware.models;

import javax.validation.constraints.NotBlank;

public class InsertBook {
	
	@NotBlank
	private String title;
	@NotBlank
	private String author;
	@Override
	public String toString() {
		return "InsertBook [title=" + title + ", author=" + author + "]";
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
	public InsertBook(String title, String author) {
		super();
		this.title = title;
		this.author = author;
	}
	public InsertBook() {
		super();
	}
	
	
	

}

package com.java.ee.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.java.ee.domain.Author;
import com.java.ee.domain.Book;

@Produces(MediaType.APPLICATION_JSON)
public class AuthorResource {
	
	private final Book book;
	public AuthorResource(Book book) {
		this.book = book;
	}
	
	
	@GET
	public Author get() {
		return book.getAuthor();
	}

}

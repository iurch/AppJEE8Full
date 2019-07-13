package com.java.ee.boundary;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.java.ee.domain.Book;
import com.java.ee.domain.Bookshelf;
import com.java.ee.domain.Library;
import com.java.ee.domain.Loan;

@RequestScoped
public class LoanResource {

	@Inject
	private Bookshelf bookshelf;
	@Inject
	private Library library;
	@Inject
	private Logger logger;
	
	private String isbn;

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response loans(){
		logger.log(Level.INFO, "Getting loans for Book with ISBN {0}.", isbn);
		Book book = bookshelf.findByISBN(isbn);
		return Response.ok(book.getLoans()).build();
	}
	
	
	@GET
	@Path("{loanId}")
	public Response loan(@PathParam("loanId") String loanId) {
		Loan loan = library.loanInfo(loanId);
		return Response.ok(loan).build();
	}
	
	@DELETE
	@Path("{loanId}")
	public Response returnBook(@PathParam("loanId") String loanId) {
		library.returnBook(isbn, loanId);
		return Response.ok().build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response lendBook(Loan loan) {
		library.lendBook(isbn, loan);
		URI location = UriBuilder.fromResource(BookResource.class)
				.path("/{isbn}/loans/{loanId}")
				.resolveTemplate("isbn", isbn)
				.resolveTemplate("loanId", loan.getId())
				.build();
		return Response.ok(location).build();
	}
}

package com.java.ee.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.ws.rs.Path;

@Entity
@Table(name = "book")
@NamedQuery(name = Book.FIND_ALL,query = "SELECT b from Book b")
@JsonbPropertyOrder(value = {"isbn","title","author"})
public class Book {
	
	static final String FIND_ALL = "Book.findAll";
	@Id
	@Column(name = "isbn", unique = true)
	private String isbn;
	
	@Column(name="title", nullable = false)
	private String title;
	
	@Embedded
	private Author author;
	
	
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Loan> loans = new ArrayList<>();

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Path("/author")
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	
	public Collection<Loan> getLoans(){
		return loans;
	}
	
	public void setLoans(Collection<Loan> loans) {
		loans.clear();
		loans.addAll(loans);
	}
	
	
	public void addLoan(Loan loan) {
		loan.setBook(this);
		loans.add(loan);
	}
	
	public void removeLoan(Loan loan) {
		int index = loans.indexOf(loan);
		if(index > -1) {
			Loan l = loans.remove(index);
			l.setBook(null);
		}
	}
	
	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + "]";
	}
}

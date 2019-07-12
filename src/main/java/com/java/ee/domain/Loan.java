package com.java.ee.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Loan")
@JsonbPropertyOrder({"id","username","start","end"})
public class Loan {

	@Id
	@Column(name="id")
	private String id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "period_start")
	@JsonbDateFormat("yyyy-MM-dd")
	private LocalDate start;
	
	@Column(name="period_end")
	@JsonbDateFormat("yyyy-MM-dd")
	private LocalDate end;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="book_id")
	@JsonbTransient
	private Book book;
	
	public Loan() {
		this(UUID.randomUUID().toString());
	}
	
	public Loan(String loanId) {
		this.id = loanId;
	}

	public Loan(String username, LocalDate start, LocalDate end) {
		super();
		this.username = username;
		this.start = start;
		this.end = end;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Loan other = (Loan) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Loan [id=" + id + ", username=" + username + ", start=" + start + ", end=" + end + "]";
	}
	
}

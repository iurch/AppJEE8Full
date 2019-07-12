package com.java.ee.domain;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class Library {

	@Inject
	private EntityManager entityManager;
	
	@Inject
	private Logger logger;
	
	
	public Loan loanInfo(String loanId) {
		logger.log(Level.INFO, "Getting Loan with ID {0}.", loanId);
		return entityManager.getReference(Loan.class, loanId);
		
	}
	
	public void returnBook(String isbn, String loanId) {
		logger.log(Level.INFO, "Returning BOOK with isbn {0} and loan ID {1}.",new Object[] {isbn,loanId});
		Book book = entityManager.getReference(Book.class, isbn);
		book.removeLoan(new Loan(loanId));
	}
	
	
	public void lendBook(String isbn, Loan loan) {
		logger.log(Level.INFO,"Lend book with ISBN {0} in {1}.", new Object[] {isbn, loan});
		Book book = entityManager.getReference(Book.class, isbn);
		book.addLoan(loan);
	}
}

package com.java.ee.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class Bookshelf {
	
	@Inject
	private EntityManager entityManager;
	
	@Inject
	private Logger logger;
	
	public Collection<Book> findAll(){
		logger.log(Level.INFO, "Find All Books");
		TypedQuery<Book> findAll = entityManager.createNamedQuery(Book.FIND_ALL, Book.class);
		return Collections.unmodifiableCollection(findAll.getResultList());
	}
	
	public Book findByISBN(String isbn) {
		logger.log(Level.INFO, "Find book with ISBN {0}.",isbn);
		return entityManager.getReference(Book.class, Objects.requireNonNull(isbn));
	}
	
	public void create(Book book) {
		Objects.nonNull(book);
		logger.log(Level.INFO, "Creating {0}", book);
		entityManager.persist(book);
	}
	
	public void update(String isbn, Book book) {
		Objects.requireNonNull(isbn);
		logger.log(Level.INFO, "Updating {0} using ISBN {1}.",new Object[]{isbn, book});
		entityManager.merge(book);
	}
	
	
	public void delete(String isbn) {
		Objects.requireNonNull(isbn);
		logger.log(Level.INFO, "Delete book with ISBN {0}.",isbn);
		Book reference = entityManager.getReference(Book.class, isbn);
		entityManager.remove(reference);
	}

}

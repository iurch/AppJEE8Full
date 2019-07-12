package com.java.ee.integration;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class EntityManagerProducer {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	
	@Produces
	@RequestScoped
	public EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
	
	
	public void disposeEntityManager(@Disposes EntityManager entityManager) {
		entityManager.close();
	}
}

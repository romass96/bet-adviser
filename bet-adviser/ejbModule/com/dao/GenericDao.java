package com.dao;

import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;

public abstract class GenericDao<T> {
	
	@PersistenceContext
	protected EntityManager entityManager;
	private Class<T> entityClass;

	public GenericDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	public void save(T entity) {
		entityManager.persist(entity);
	}
	
	public void delete(T entity) {
		entityManager.remove(entity);
	}
	
	public T update(T entity) {
		return entityManager.merge(entity);
	}
	
	public T find(long entityId) {
		return entityManager.find(entityClass, entityId);
	}

	public List<T> findAll() {
		CriteriaQuery<T> cq = entityManager.getCriteriaBuilder().createQuery(entityClass);
		cq.select(cq.from(entityClass));
		return entityManager.createQuery(cq).getResultList();
	}
}

package com.xantrix.webapp.dao;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

//JPQL https://docs.oracle.com/javaee/7/tutorial/persistence-querylanguage.htm
//CRITERIA API https://docs.oracle.com/javaee/7/tutorial/persistence-criteria.htm
// @@@  Management of the base queries
// here we use the criteria API
public abstract class AbstractDao<I extends Serializable, Id extends Serializable>
	implements GenericRepository<I, Id>
{
	// Hibernate session
	@PersistenceContext     // the annotation create the persistent context for the entity manager
	// It a wrapper of the session or context, it allows you to execute all queries
	protected EntityManager entityManager;  // there is one entityManager for the application (unique cache and session)

	protected final Class<I> entityClass;
	
	CriteriaBuilder builder;
	
	@SuppressWarnings("unchecked")
	public AbstractDao()
	{
		this.entityClass = (Class<I>) ((ParameterizedType) 
				this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/*

	If you don't have any special requirements, as a rule of thumb, you should stick to the persist and merge methods,
	because they are standardized and guaranteed to conform to the JPA specification.

	*/


	private CriteriaQuery<I> InitCriteria()
	{
		builder = this.entityManager.getCriteriaBuilder();
        return builder.createQuery(this.entityClass);
	}
	
	@Override
	public List<I> SelTutti()
	{
        CriteriaQuery<I> query = this.InitCriteria();

        // criteria API example
        return this.entityManager.createQuery(
        		query.select(query.from(this.entityClass))).getResultList();
	}
	
	@Override
	public I SelById(Id id)
	{
		CriteriaQuery<I> query = this.InitCriteria();

		return this.entityManager.createQuery(
					query.where(
							builder.equal(
									query.from(this.entityClass).
									get("id"), id))). // with a filter
					getSingleResult();
			
	}
	
	@Override
	public void Inserisci(I entity)
	{
		// Inserting
		this.entityManager.persist(entity);
		flushAndClear();
	}
	
	@Override
	public void Aggiorna(I entity)
	{
		// Updating
		this.entityManager.merge(entity); 
		flushAndClear();
	}
	
	@Override
	public void Elimina(I entity)
	{
		// Deleting
		this.entityManager.remove(
				this.entityManager.contains(entity) ? // it manages the detach entity
						entity :
						this.entityManager.merge(entity)); // it attaches the entity to the persistent context (Hibernate session)
		flushAndClear();
	}
	
	@Override
	public void EliminaById(Id id)
	{
		CriteriaQuery<I> query = this.InitCriteria();
		
		this.entityManager.createQuery(
				query.where(
						builder.equal(
								query.from(this.entityClass)
								.get("id"), id)  // with a filter
        )).executeUpdate();
		
		flushAndClear();
	}

	/**
	 * Save the data in the DB because the entityManager has a cache
	 * The actual saving of data to the database occurs on committing the transaction or flushing the Session.
	 */
	private void flushAndClear() 
	{
	    entityManager.flush();
	    entityManager.clear();
	}
}

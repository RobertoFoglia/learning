package com.xantrix.webapp.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.xantrix.webapp.entities.Clienti;

@Repository
public class ClientiDaoImpl  extends AbstractDao<Clienti, String> 
	implements ClientiDao
{

	// @@@ CRITERIA API with single result
	@Override
	public Clienti SelByCodFidelity(String CodFidelity)
	{
		// query builder - it is used for building the filters
		CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();
		// query object that it will be passed to the query
		CriteriaQuery<Clienti> queryDefinition = queryBuilder.createQuery(Clienti.class);
		
		Root<Clienti> recordset = queryDefinition.from(Clienti.class);
		
		queryDefinition.select(recordset)
			.where(queryBuilder.equal(recordset.get("codFidelity"), CodFidelity));

		// Query creation
		Clienti cliente = entityManager.createQuery(queryDefinition).getSingleResult();

		// detached the entities ???
		entityManager.clear();
		
		return cliente;
	}
	
	// @@@ CRITERIA API with the list
	@Override
	public List<Clienti> SelByNominativo(String Nome)
	{
		
		CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Clienti> queryDefinition = queryBuilder.createQuery(Clienti.class);

		// like in SQL
		String ToSearch = "%" + Nome + "%";

		// @@@ expression for the filters
		Root<Clienti> recordset = queryDefinition.from(Clienti.class);
		
		Expression<String> exp1 = queryBuilder.concat(recordset.<String>get("nome"), " ");
		exp1 = queryBuilder.concat(exp1, recordset.<String>get("cognome"));
		
		Expression<String> exp2 = queryBuilder.concat(recordset.<String>get("cognome"), " ");
		exp2 = queryBuilder.concat(exp2, recordset.<String>get("nome"));
		
		Predicate whereClause = queryBuilder.or(
				queryBuilder.like(exp1, ToSearch), 
				queryBuilder.like(exp2, ToSearch));
		
		queryDefinition.select(recordset)
			.where(whereClause);
		
		List<Clienti> clienti = entityManager.createQuery(queryDefinition).getResultList();
		
		entityManager.clear();
		
		return clienti;
		

	}


	/// @@@ JPQL
	@Override
	@SuppressWarnings("unchecked")
	public List<Clienti> SelByComune(String comune)
	{
		return  entityManager
				// JPQL query
				.createQuery("SELECT a FROM Clienti a WHERE a.comune = :comune")
				.setParameter("comune", comune)
				.getResultList();
	}

	@Override
	public List<Clienti> SelByBollini(int numBollini, String Tipo)
	{
		String jpql = "";
		
		if (Tipo.equals(">"))
		{
			jpql = "SELECT a FROM Clienti a JOIN a.card b " +
					"WHERE b.bollini >= :qtabollini ORDER BY b.bollini DESC";
		}
		else if (Tipo.equals("<"))
		{
			jpql = "SELECT a FROM Clienti a JOIN a.card b " +
					"WHERE b.bollini <= :qtabollini ORDER BY b.bollini ASC";
		}
		
		return entityManager.createQuery(jpql)
				.setParameter("qtabollini", numBollini)
				.getResultList();
	}
	
	@Override
	public List<Clienti> SelTutti()
	{
		
		return  super.SelTutti();
	}
	
	@Override
	public void Salva(Clienti cliente)
	{
		super.Inserisci(cliente);
	}
	
	@Override
	public void Aggiorna(Clienti cliente)
	{
		super.Aggiorna(cliente);
	}

	@Override
	public void Elimina(Clienti cliente)
	{
		super.Elimina(cliente);
	}

	@Override
	public String SelLastCodFid()
	{
		String retVal = "0";
		
		try
		{
			String JPQL = "SELECT a.codFidelity FROM Clienti a ORDER BY a.codFidelity DESC";
			
			retVal = (String) entityManager
					.createQuery(JPQL)
					// one result
					.setMaxResults(1)
					.getSingleResult();
		}
		catch (Exception ex)
		{ }
		
		return retVal;
	}

	@Override
	public long QtaTotBollini()
	{
		
		long retVal = 0;
		// aggregation function
		String JPQL = "SELECT SUM(b.bollini) FROM Clienti a JOIN a.card b ";
		
		retVal = (long) entityManager.createQuery(JPQL).getSingleResult();
		
		return retVal;
	}

	

}

package com.xantrix.webapp.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.xantrix.webapp.entities.Utenti;

@Repository
public class UtentiDaoImpl  extends AbstractDao<Utenti, Integer> 
	implements UtentiDao
{
	@Override
	public void Salva(Utenti utente)
	{
		super.Inserisci(utente);
	}
	
	@Override
	public Utenti SelByIdFidelity(String idFidelity)
	{
		Utenti retVal = new Utenti();
		
		try
		{
			// the teacher uses the Criteria API, but here it is possible to use the JPQL
			CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Utenti> queryDefinition = queryBuilder.createQuery(Utenti.class);
			
			Root<Utenti> recordset = queryDefinition.from(Utenti.class);
			
			queryDefinition.select(recordset).
							where(queryBuilder.equal(recordset.get("codFidelity"), idFidelity));
			
			retVal =  entityManager.createQuery(queryDefinition).getSingleResult();
			
			return retVal;
		}
		catch(Exception ex)
		{
			return retVal;
		}
	}
}

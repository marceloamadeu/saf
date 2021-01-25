package br.gov.pr.adapar.saf.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.gov.pr.adapar.saf.entity.Fruit;
import br.gov.pr.adapar.saf.exception.ApplicationServiceException;


@RequestScoped
public class FruitDAO {

    @Inject
	EntityManager em;


	@Transactional
	public void incluir(Fruit fruit) throws ApplicationServiceException {
		try {
			em.persist(fruit);
		} catch (Exception e) {
			throw new ApplicationServiceException("fruit.erro", e);
		}
	}


	@Transactional
	public void alterar(Fruit fruit) throws ApplicationServiceException {
		try {
			em.merge(fruit);
		} catch (Exception e) {
			throw new ApplicationServiceException("fruit.erro", e);
		}
	}


	@Transactional
	public void excluir(Fruit fruit) throws ApplicationServiceException {
		try {
			fruit = em.find(Fruit.class, fruit.getId());
			em.remove(fruit);
		} catch (Exception e) {
			throw new ApplicationServiceException("fruit.erro", e);
		}
	}


	public List<Fruit> listar() throws ApplicationServiceException {
		try {
			TypedQuery<Fruit> query = em.createQuery("SELECT s FROM Fruit AS s ORDER BY s.nome", Fruit.class);
			return query.getResultList();
		} catch (Exception e) {
			throw new ApplicationServiceException("fruit.erro", e);
		}
	}


	public List<Fruit> listar(Integer offset, Integer limit, String sortField, String sortOrder)
			throws ApplicationServiceException {
		try {
			StringBuilder query = new StringBuilder("SELECT fruit FROM Fruit AS fruit ");
			UtilDAO.sorterQuery("fruit", sortField, sortOrder, query);
			TypedQuery<Fruit> tQuery = em.createQuery(query.toString(), Fruit.class);

			UtilDAO.sorterQuery("fruit", sortField, sortOrder, query);

			// Delimita o num de registros a ser recuperado
			UtilDAO.setPagination(tQuery, offset, limit);

			return tQuery.getResultList();
		} catch (Exception e) {
			throw new ApplicationServiceException("fruit.erro", new String[] { "listar" }, e);
		}
	}


	public Integer obterQuantidade() throws ApplicationServiceException {
		try {
			Query query = em.createQuery("SELECT COUNT(s) FROM Fruit s");
			Long x = (Long) query.getSingleResult();
			return Integer.valueOf(x.intValue());
		} catch (Exception e) {
			throw new ApplicationServiceException("fruit.erro", e);
		}
	}


	public Fruit obterPorId(Integer id) throws ApplicationServiceException {
		try {
			TypedQuery<Fruit> query = em.createQuery("SELECT s FROM Fruit AS s WHERE s.id = :id",
					Fruit.class);
			query.setParameter("id", id);
			return query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		} catch (Exception e) {
			throw new ApplicationServiceException("fruit.erro", new String[] { "obterPorId" }, e);
		}
	}
    
}

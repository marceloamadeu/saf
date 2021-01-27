package br.gov.pr.adapar.saf.dao;

import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;


@RequestScoped
public class MonitoramentoDAO {

	private static final Logger LOG = Logger.getLogger(MonitoramentoDAO.class.getName());
	
	@Inject
	EntityManager entityManager;

	/**
	 * Testa se o banco de dados esta respondendo requisicoes.
	 */
	public Boolean testarConexao() {
		try {
			StringBuilder query = new StringBuilder("SELECT now()");
			Query tQuery = entityManager.createNativeQuery(query.toString());
			Timestamp timestamp = (Timestamp) tQuery.getSingleResult();
			if (timestamp == null) {
				return false;
			}
			return true;
		} catch (Exception e) {
			LOG.log(Level.INFO,"Nao foi possivel estabelecer conexao com o banco de dados.", e);
			return false;
		}
	}

}
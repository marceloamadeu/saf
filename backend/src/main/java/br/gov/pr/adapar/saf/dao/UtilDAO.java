package br.gov.pr.adapar.saf.dao;

import java.util.HashMap;
import java.util.Iterator;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;


public abstract class UtilDAO {
	

	 // Quantidade máxima de resultados por requisicao, para manter desempenho.
	public static final int MAX_LIMIT = 100;
	
	/**
	 * Método que monta o Sorter para consultas
	 * @param sortField - Coluna de ordenação
	 * @param sortOrder - SorterOrder: ASCENDING, ASC, DESCENDING, DESC
	 * @param query - Consulta a ser realizada
	 */	
	public static void sorterQuery(String aliasObject, String sortField, String sortOrder, StringBuilder query) {
		if(StringUtils.isNotBlank(sortField) && StringUtils.isNotBlank(aliasObject)) {
			query.append(" ORDER BY ");
			if (sortOrder != null && (sortOrder.equalsIgnoreCase("DESCENDING") || sortOrder.equalsIgnoreCase("DESC"))) {
				query.append(aliasObject).append(".").append(sortField).append(" DESC");
			} else {
				query.append(aliasObject).append(".").append(sortField).append(" ASC");
			}				
		}
	}
	
	/**
	 * Seta os parametros de acordo com atributos de pesquisa
	 */
	public static void setParameters(HashMap<String, Object> parameters, Query query) {
		Iterator<String> keySetIterator = parameters.keySet().iterator();
		while(keySetIterator.hasNext()) {
			String key = keySetIterator.next();
			query.setParameter(key, parameters.get(key));
		}
	}
	
	/**
	 * Delimita o retorno dos dados
	 */
	public static <T> void setPagination(TypedQuery<T> tQuery, Integer offset, Integer limit) {
		offset = (offset != null && offset.intValue() >= 0) ? offset : 0;
		limit = (limit != null && limit.intValue() <= MAX_LIMIT) ? limit : MAX_LIMIT;
		tQuery.setFirstResult(offset.intValue());
        tQuery.setMaxResults(limit);
	}
	
}
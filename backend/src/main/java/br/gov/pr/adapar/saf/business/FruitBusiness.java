package br.gov.pr.adapar.saf.business;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.core.Response;

import br.gov.pr.adapar.saf.dao.FruitDAO;
import br.gov.pr.adapar.saf.entity.Fruit;
import br.gov.pr.adapar.saf.exception.ApplicationServiceException;
import br.gov.pr.adapar.saf.util.message.MessageBundle;
import br.gov.pr.adapar.saf.util.message.MessageServiceError;
import br.gov.pr.adapar.saf.util.validator.FieldValidator;


@RequestScoped
public class FruitBusiness {
    private static final Logger LOG = Logger.getLogger(FruitBusiness.class.getName());
	
	@Inject
	FruitDAO fruitDAO;
	
	@Inject
	Validator validator;
	

	public void incluir(Fruit fruit) throws ApplicationServiceException {
		
		// Valida parâmetros
		List<MessageServiceError> listaErros = FieldValidator.validateFields(validator, fruit);		
		if(listaErros.size() > 0) {
			throw new ApplicationServiceException("message.parametrosnaoinformados", Response.Status.BAD_REQUEST.getStatusCode(), listaErros);
		}
		
		try {
			fruitDAO.incluir(fruit);
		} catch (Exception e) {
			LOG.log(Level.SEVERE,"Erro na execucao do FruitBusiness: incluir", e);
			throw new ApplicationServiceException("fruit.erro", new String[] { "incluir" }, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		}		
	}
	

	public void alterar(Fruit fruit) throws ApplicationServiceException {
		
		// Valida parâmetros
		List<MessageServiceError> listaErros = FieldValidator.validateFields(validator, fruit);		
		if(listaErros.size() > 0) {
			throw new ApplicationServiceException("message.parametrosnaoinformados", Response.Status.BAD_REQUEST.getStatusCode(), listaErros);
		}
		
		// Verifica se a fruit existe
		if(this.existe(fruit.getId()) == false) {
			LOG.fine("Debug na execucao do FruitBusiness: alterar = não existe - idFruit="+fruit.getId());
			throw new ApplicationServiceException("fruit.naocadastrado", Response.Status.NOT_FOUND.getStatusCode());
		}
				
		try {
			fruitDAO.alterar(fruit);
		} catch (Exception e) {
			LOG.log(Level.SEVERE,"Erro na execucao do FruitBusiness: alterar", e);
			throw new ApplicationServiceException("fruit.erro", new String[] { "alterar" }, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		}		
	}
	

	public void excluir(Integer id) throws ApplicationServiceException {
		
		//Valida parâmetro id
		if(id == null) {
			List<MessageServiceError> listaErros = new ArrayList<MessageServiceError>(0);
			listaErros.add(new MessageServiceError(MessageBundle.getMessage("fruit.idfruit"), "idFruit"));
			throw new ApplicationServiceException("message.parametrosnaoinformados", Response.Status.BAD_REQUEST.getStatusCode(), listaErros);
		}
		
		Fruit fruit = this.obterPorId(id);
		
		//Valida se série existe		
		if(fruit == null || fruit.getId() == null) {
			LOG.fine("Debug na execucao do FruitBusiness: excluir = não pode excluir = não existe - idFruit="+id);
			throw new ApplicationServiceException("fruit.naoexiste", Response.Status.BAD_REQUEST.getStatusCode());
		}
				
		
		try {
			Fruit s = new Fruit();
			s.setId(id);
			fruitDAO.excluir(s);
		} catch (Exception e) {
			LOG.log(Level.SEVERE,"Erro na execucao do FruitBusiness: excluir", e);
			throw new ApplicationServiceException("fruit.erro", new String[] { "excluir" }, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		}	
	}
	

	public List<Fruit> listar(Integer offset, Integer limit, String sortField, String sortOrder) throws ApplicationServiceException {
		
		List<Fruit> fruits = new ArrayList<Fruit>(0);
		try {
			fruits = fruitDAO.listar(offset, limit, sortField, sortOrder);
		} catch (Exception e) {
			LOG.log(Level.SEVERE,"Erro na execucao do FruitBusiness: listar", e);
			throw new ApplicationServiceException("fruit.erro", new String[] { "listar" }, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		} finally {
			if(fruits.size() <= 0) {
				LOG.fine("Debug na execucao do FruitBusiness: listar = nenhum resultado");
				throw new ApplicationServiceException("fruit.nenhumresultadolistagem", Response.Status.NOT_FOUND.getStatusCode());
			}
		}
		
		return fruits;
	}
	

	public Integer obterQuantidade() throws ApplicationServiceException {		
		try {
			return fruitDAO.obterQuantidade();
		} catch (Exception e) {
			LOG.log(Level.SEVERE,"Erro na execucao do FruitBusiness: obterQuantidade", e);
			throw new ApplicationServiceException("fruit.erro", new String[] { "obterQuantidade" }, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		}
	}
	

	public Fruit obterPorId(Integer idFruit) throws ApplicationServiceException {
		//Valida parâmetro idFruit
		if(idFruit == null) {
			List<MessageServiceError> listaErros = new ArrayList<MessageServiceError>(0);
			listaErros.add(new MessageServiceError(MessageBundle.getMessage("fruit.idfruit"), "idFruit"));
			throw new ApplicationServiceException("message.parametrosnaoinformados", Response.Status.BAD_REQUEST.getStatusCode(), listaErros);
		}
		
		Fruit fruit = null;
		try {
			fruit = fruitDAO.obterPorId(idFruit);		 
		} catch (Exception e) {
			LOG.log(Level.SEVERE,"Erro na execucao do FruitBusiness: obterPorId", e);
			throw new ApplicationServiceException("fruit.erro", new String[] { "obterPorId" }, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		} finally {
			if (fruit == null) {
				LOG.fine("Debug na execucao do FruitBusiness: obterPorId = nenhumresultado - CPF="+idFruit);
				throw new ApplicationServiceException("fruit.naocadastrado", new String[] { "obterPorId" }, Response.Status.NOT_FOUND.getStatusCode());
			}
		}
		
		return fruit;
	}
	

	private Boolean existe(Integer idFruit) throws ApplicationServiceException {
		
		Boolean existe = false;
		try {
			if (this.obterPorId(idFruit) != null) {
				existe = true;
				LOG.fine("Debug na execucao do FruitBusiness: existe = true");
			}
		}  catch (ApplicationServiceException e) {
			LOG.log(Level.FINE,"Debug na execucao do FruitBusiness: existe = false", e);
			if(e.getStatusCode().equals(Response.Status.NOT_FOUND.getStatusCode())) {
				existe = false;
			} else throw e;
		}  catch (Exception e) {
			LOG.log(Level.SEVERE,"Erro na execucao do FruitBusiness: existe", e);
			throw new ApplicationServiceException("fruit.erro", new String[] { "existe" }, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		}
		
		return existe;
	}
}

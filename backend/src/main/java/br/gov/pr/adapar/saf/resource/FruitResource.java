package br.gov.pr.adapar.saf.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.gov.pr.adapar.saf.business.FruitBusiness;
import br.gov.pr.adapar.saf.exception.ApplicationServiceException;
import br.gov.pr.adapar.saf.util.message.MessageBundle;
import br.gov.pr.adapar.saf.util.message.MessageService;
import br.gov.pr.adapar.saf.vo.FruitVO;

@Path("/api/v1/fruits")
//@Path("v1/fruits")
@Tag(name="fruit", description = "Operações referentes as Frutas")
public class FruitResource {

    private static final Logger LOG = Logger.getLogger(FruitResource.class.getName());

	@Inject
	FruitBusiness fruitBusiness;

    /*
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        System.out.println("Finalmente....");
        return "hello from Quarkus-Vue!\nNumber Of processors: " + Runtime.getRuntime().availableProcessors() + "\nMemory (bytes): " + Runtime.getRuntime().maxMemory();
    }
    */

    /**
	 * Lista Frutas.
	 * 
	 * @param offset
	 * @param limit
	 * @param sortField
	 * @param sortOrder
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CircuitBreaker(requestVolumeThreshold = 10, failureRatio = 0.5, delay = 2500) // fault tolerance
	@Counted(name = "listarCount", description = "Total de chamadas") // metrics
	@Timed(name = "listarTimer", description = "Tempo de execucao", unit = MetricUnits.MILLISECONDS) // metrics
	@Parameter(name = "offset", description = "Índice de início para retorno das ocorrências. Default=0.", in = ParameterIn.QUERY)
	@Parameter(name = "limit", description = "Quantidade de ocorrências que devem ser retornadas. Máximo=100, Mínimo=1.", in = ParameterIn.QUERY)
	@Parameter(name = "sortField", description = "Nome do atributo utilizado para ordenação das ocorrências. Default=''.", in = ParameterIn.QUERY)
	@Parameter(name = "sortOrder", description = "Sentido da ordenação: ASCENDING ou ASC, DESCENDING ou DESC. Default=ASC.", in = ParameterIn.QUERY)
	@APIResponse(responseCode = "200", description = "OK.")
	@APIResponse(responseCode = "404", description = "Caso não sejam encontradas ocorrências com os parâmetros informados.")
	@APIResponse(responseCode = "500", description = "Caso ocorra um erro interno.")
	public Response listar(@QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit,
			@QueryParam("sortField") String sortField, @QueryParam("sortOrder") String sortOrder) {

		List<FruitVO> fruits = new ArrayList<FruitVO>(0);
		Integer quantidadeTotalListagem = 0;
		try {

			fruits = fruitBusiness.listar(offset, limit, sortField, sortOrder).stream().map(e -> new FruitVO(e))
					.collect(Collectors.toList());

			quantidadeTotalListagem = fruitBusiness.obterQuantidade();
		} catch (ApplicationServiceException appEx) {
			LOG.log(Level.FINE,"Problema na execucao do FruitService: listar", appEx);
			return Response.status(appEx.getStatusCode()).entity(new MessageService(appEx.getMessage())).build();
		} catch (Exception e) {
			LOG.log(Level.SEVERE,"Erro na execucao do FruitService: listar", e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(new MessageService(MessageBundle.getMessage("fruit.erro", new String[] { "listar" })))
					.build();
		}

		return Response.ok().entity(fruits).header("Pagination-Count", quantidadeTotalListagem).build();
	}



}
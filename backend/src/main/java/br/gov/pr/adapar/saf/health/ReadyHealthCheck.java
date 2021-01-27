package br.gov.pr.adapar.saf.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

import br.gov.pr.adapar.saf.dao.MonitoramentoDAO;


@Readiness
@ApplicationScoped
public class ReadyHealthCheck implements HealthCheck {
	
	@Inject
	MonitoramentoDAO monitoramentoDAO;

	@Override
	public HealthCheckResponse call() {

		HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Teste de conexão com o banco de dados");

		try {
			testarConexaoBancoDeDados();
			responseBuilder.up();
		} catch (IllegalStateException e) {
			responseBuilder.down().withData("error", e.getMessage());
		}

		return responseBuilder.build();
	}

	private void testarConexaoBancoDeDados() {
		if (!monitoramentoDAO.testarConexao()) {
			throw new IllegalStateException("Não foi possível conectar ao banco de dados");
		}
	}
}
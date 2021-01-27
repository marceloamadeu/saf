package br.gov.pr.adapar.saf.health;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class LiveHealthCheck implements HealthCheck {
	
	@ConfigProperty(name = "versao")
	private String versao;
	@Override
	public HealthCheckResponse call() {
		/*
		try { 
			
			//Somente teste para https
						
				 String url = "https://datagrid-app-hml-rhdg-detran.paas.celepar.parana";

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        String output = "";
        String line;
        while ((line = br.readLine()) != null) {
            output += line;
        }

        conn.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
			//System.out.println("Erro..");
			//e.printStackTrace();
		}
		
		
		return HealthCheckResponse.named("Liveness - OK - versão: " + versao).up().build();
	}
	*/

}
package br.gov.pr.adapar.saf;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1/fruits")
public class FruitResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        System.out.println("Finalmente....");
        return "hello from Quarkus-Vue!\nNumber Of processors: " + Runtime.getRuntime().availableProcessors() + "\nMemory (bytes): " + Runtime.getRuntime().maxMemory();
    }



}
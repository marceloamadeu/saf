package br.gov.pr.adapar.saf.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.gov.pr.adapar.saf.util.message.MessageBundle;
import br.gov.pr.adapar.saf.util.message.MessageService;

/**
 * Trata exceção do tipo InvalidFormatException.
 */
@Provider
public class ApplicationParserException implements ExceptionMapper<InvalidFormatException> {

	@Override
	public Response toResponse(InvalidFormatException e) {
		return Response.status(Response.Status.BAD_REQUEST).entity(new MessageService(MessageBundle.getMessage("message.parsererror"))).build();
	}
}

package br.gov.pr.adapar.saf.util.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import br.gov.pr.adapar.saf.util.message.MessageServiceError;

/**
 * Classe que implementa funcionalidades de validação de campos (Bean Validation)
 */
public class FieldValidator {
	
	public static List<MessageServiceError> validateFields(Validator validator, Object obj) {
		return validateFields(validator, obj, null);
	}

	public static List<MessageServiceError> validateFields(Validator validator, Object obj, String[] fields) {
		Set<ConstraintViolation<Object>> violations = validator.validate(obj);
		List<MessageServiceError> listaErros = new ArrayList<MessageServiceError>(0);
		List<String> fieldList = null;
		if (fields != null && fields.length > 0) {
			fieldList = Arrays.asList(fields);
		}
		for (ConstraintViolation<Object> violation : violations) {
			String propertyPath = violation.getPropertyPath().toString();
			// se lista de campos == null então valida todos os campos
			// caso contrário valida apenas os campos informados
			if (fieldList == null || fieldList.contains(propertyPath)) {
				String message = violation.getMessage();
				MessageServiceError errorMessage = new MessageServiceError();
				errorMessage.setField(propertyPath);
				errorMessage.setMessage(message);
				listaErros.add(errorMessage);
			}
		}
		return listaErros;
	}

}

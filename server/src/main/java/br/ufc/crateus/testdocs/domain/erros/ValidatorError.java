package br.ufc.crateus.testdocs.domain.erros;

import java.util.Map;

public class ValidatorError extends DomainError {
	private Map<String, String> errors;
	
	public ValidatorError(Map<String, String> errors) {
		super("VALIDATION_ERROR", "Campos inválidos");
		this.errors = errors;
	}

	public Map<String, String> getErrors() {
		return errors;
	}
}

package br.ufc.crateus.testdocs.services;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Validator;

public abstract class BaseService {
	protected  Validator validator;

	public BaseService(Validator validator) {
		this.validator = validator;
	}
	
	public <T> Map<String, String> validateEntity(T entity) {
		Map<String, String> errors = new HashMap<String, String>();
		this.validator.validate(entity)
				.forEach(e -> {
					errors.put(e.getPropertyPath().toString(), e.getMessage());
				});
		return errors;
	}
}

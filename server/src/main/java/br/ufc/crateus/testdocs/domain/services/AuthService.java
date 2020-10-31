package br.ufc.crateus.testdocs.domain.services;

import br.ufc.crateus.testdocs.domain.erros.DomainError;
import br.ufc.crateus.testdocs.domain.erros.Either;

public interface AuthService {
	Either<String, DomainError> authenticate(String email, String password);
}

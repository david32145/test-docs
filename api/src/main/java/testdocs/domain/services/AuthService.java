package testdocs.domain.services;

import testdocs.domain.erros.DomainError;
import testdocs.domain.erros.Either;

public interface AuthService {
	Either<String, DomainError> authenticate(String email, String password);
}

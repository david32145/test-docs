package testdocs.domain.services;

import testdocs.domain.entities.User;
import testdocs.domain.erros.DomainError;
import testdocs.domain.erros.Either;

public interface UserService {
	Either<User, DomainError> createUser(User user);
}

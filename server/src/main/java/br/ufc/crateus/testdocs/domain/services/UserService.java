package br.ufc.crateus.testdocs.domain.services;

import br.ufc.crateus.testdocs.domain.entities.User;
import br.ufc.crateus.testdocs.domain.erros.DomainError;
import br.ufc.crateus.testdocs.domain.erros.Either;

public interface UserService {
	Either<User, ? extends DomainError> createUser(User user);
}

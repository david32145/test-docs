package br.ufc.crateus.testdocs.services;

import java.util.Map;

import javax.validation.Validator;

import org.springframework.stereotype.Service;

import br.ufc.crateus.testdocs.domain.entities.User;
import br.ufc.crateus.testdocs.domain.erros.DomainError;
import br.ufc.crateus.testdocs.domain.erros.Either;
import br.ufc.crateus.testdocs.domain.erros.Left;
import br.ufc.crateus.testdocs.domain.erros.Rigth;
import br.ufc.crateus.testdocs.domain.erros.ValidatorError;
import br.ufc.crateus.testdocs.domain.repositories.UserRepository;
import br.ufc.crateus.testdocs.domain.services.UserService;

@Service
public class UserServiceImpl extends BaseService implements UserService {
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository, Validator validator) {
		super(validator);
		this.userRepository = userRepository;
	}

	@Override
	public Either<User, ? extends DomainError> createUser(User user) {
		Map<String, String> erros = validateEntity(user);
		if(!erros.isEmpty()) {
			return new Left<>(new ValidatorError(erros));
		}
		if (userRepository.existsByEmail(user.getEmail())) {
			return new Left<User, DomainError>(
					new DomainError("USER_EMAIL_ALREADY_EXISTS",
							"um usuário com esse email já existe"));
		}
		user.makePasswordHash();
		user = userRepository.add(user);
		if (user == null) {
			return new Left<User, DomainError>(new DomainError("UNKNOWN_ERROR",
					"Ocorreu um error ao cadastrar o usuário"));
		}
		return new Rigth<User, DomainError>(user);
	}

}

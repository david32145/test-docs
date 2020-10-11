package testdocs.services;

import testdocs.domain.entities.User;
import testdocs.domain.erros.DomainError;
import testdocs.domain.erros.Either;
import testdocs.domain.erros.Left;
import testdocs.domain.erros.Rigth;
import testdocs.domain.repositories.UserRepository;
import testdocs.domain.services.UserService;

public class UserServiceImpl implements UserService {
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Either<User, DomainError> createUser(User user) {
		if(userRepository.existsByEmail(user.getEmail())) {
			return new Left<User, DomainError>(new DomainError("USER_EMAIL_ALREADY_EXISTS", "um usuário com esse email já existe"));
		}
		user.makePasswordHash();
		user = userRepository.add(user);
		if(user == null) {
			return new Left<User, DomainError>(new DomainError("UNKNOWN_ERROR", "Ocorreu um error ao cadastrar o usuário"));
		}
		return new Rigth<User, DomainError>(user);
	}
	
	
}

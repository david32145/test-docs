package testdocs.services;

import testdocs.domain.entities.User;
import testdocs.domain.erros.DomainError;
import testdocs.domain.erros.Either;
import testdocs.domain.erros.Left;
import testdocs.domain.erros.Rigth;
import testdocs.domain.repositories.UserRepository;
import testdocs.domain.services.AuthService;

public class AuthServiceImpl implements AuthService {
	
	private UserRepository userRespository;
	
	public AuthServiceImpl(UserRepository userRespository) {
		this.userRespository = userRespository;
	}

	@Override
	public Either<String, DomainError> authenticate(String email, String password) {
		User user = userRespository.findByEmail(email);
		if(user == null) {
			return new Left<>(new DomainError("USER_NOT_FOUND", "Esse email não existe"));
		}
		if(user.passwordNotMatches(password)) {
			return new Left<>(new DomainError("PASSWORD_DONT_MATCHES", "Senha incorreta"));
		}
		
		return new Rigth<String, DomainError>("token");
	}
	
	

}

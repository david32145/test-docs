package br.ufc.crateus.testdocs.services;

import org.springframework.stereotype.Service;

import br.ufc.crateus.testdocs.domain.entities.User;
import br.ufc.crateus.testdocs.domain.erros.DomainError;
import br.ufc.crateus.testdocs.domain.erros.Either;
import br.ufc.crateus.testdocs.domain.erros.Left;
import br.ufc.crateus.testdocs.domain.erros.Rigth;
import br.ufc.crateus.testdocs.domain.repositories.UserRepository;
import br.ufc.crateus.testdocs.domain.services.AuthService;

@Service
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
		
		return new Rigth<String, DomainError>(user.getId() + "");
	}
	
	

}

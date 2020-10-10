package testdocs.application.controllers;

import java.sql.SQLException;

import testdocs.application.dto.AuthDTO;
import testdocs.application.shared.ServletResponse;
import testdocs.domain.erros.DomainError;
import testdocs.domain.erros.Either;
import testdocs.domain.services.AuthService;
import testdocs.infra.data.UserRepositoryImpl;
import testdocs.services.AuthServiceImpl;

public class AuthController {
	private AuthService authService;

	public AuthController() throws ClassNotFoundException, SQLException {
		this.authService = new AuthServiceImpl(new UserRepositoryImpl());
	}
	
	public ServletResponse auth(AuthDTO requestBody) {
		Either<String, DomainError> authResult = authService.authenticate(requestBody.getEmail(), requestBody.getPassword());
		if(authResult.hasData()) {
			return ServletResponse.ok(authResult.getData());
		}
		return ServletResponse.error(authResult.getError());
	}
}

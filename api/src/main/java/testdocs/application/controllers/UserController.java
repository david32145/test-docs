package testdocs.application.controllers;

import java.sql.SQLException;

import testdocs.application.shared.ServletResponse;
import testdocs.domain.entities.User;
import testdocs.domain.erros.DomainError;
import testdocs.domain.erros.Either;
import testdocs.domain.services.UserService;
import testdocs.infra.data.UserRepositoryImpl;
import testdocs.services.UserServiceImpl;

public class UserController {
	
	private UserService userService;
	
	public UserController() throws ClassNotFoundException, SQLException {
		this.userService = new UserServiceImpl(new UserRepositoryImpl());
	}

	public ServletResponse createUser(User requestBody) {
		Either<User, DomainError> createUserResult = userService.createUser(requestBody);
		if(createUserResult.hasData()) {
			return ServletResponse.created(createUserResult.getData());
		}
		return ServletResponse.error(createUserResult.getError());
	}
}

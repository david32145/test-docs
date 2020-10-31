package br.ufc.crateus.testdocs.application.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.crateus.testdocs.application.dto.CreateUserDTO;
import br.ufc.crateus.testdocs.domain.entities.User;
import br.ufc.crateus.testdocs.domain.erros.DomainError;
import br.ufc.crateus.testdocs.domain.erros.Either;
import br.ufc.crateus.testdocs.domain.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody CreateUserDTO body) {
		User user = User.create(body.getEmail(), body.getPassword(),
				body.getName(), body.getOffice(), body.getDescription(),
				body.getAvatarUri());
		Either<User,  ? extends DomainError> createUserResult = userService
				.createUser(user);
		if (createUserResult.hasData()) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(createUserResult.getData());
		}
		return ResponseEntity.badRequest().body(createUserResult.getError());
	}
}

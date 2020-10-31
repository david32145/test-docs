package br.ufc.crateus.testdocs.application.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.crateus.testdocs.application.dto.AuthDTO;
import br.ufc.crateus.testdocs.domain.erros.DomainError;
import br.ufc.crateus.testdocs.domain.erros.Either;
import br.ufc.crateus.testdocs.domain.services.AuthService;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {
	private AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping
	public ResponseEntity<?> auth(@RequestBody AuthDTO requestBody) {
		Either<String, DomainError> authResult = authService.authenticate(requestBody.getEmail(), requestBody.getPassword());
		if(authResult.hasData()) {
			return new ResponseEntity<String>(authResult.getData(), HttpStatus.OK);
		}
		return new ResponseEntity<DomainError>(authResult.getError(), HttpStatus.BAD_REQUEST);
	}
}

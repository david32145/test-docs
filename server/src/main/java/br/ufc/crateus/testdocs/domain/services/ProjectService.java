package br.ufc.crateus.testdocs.domain.services;

import java.util.List;

import br.ufc.crateus.testdocs.domain.entities.Project;
import br.ufc.crateus.testdocs.domain.entities.User;
import br.ufc.crateus.testdocs.domain.erros.DomainError;
import br.ufc.crateus.testdocs.domain.erros.Either;

public interface ProjectService {
	Either<Project, DomainError> createProduct(Project project, long ownerId);
	Either<Project, DomainError> updateProject(Project project, long projectId);
	Either<List<Project>, DomainError> findAllByOwnerId(long ownerId);
	Either<Project, DomainError> findById(long id);
	Either<User, DomainError> addMember(long projectId, String userId);
	Either<List<User>, DomainError> findUsersByProjectId(long projectId);
	Either<String, DomainError> deleteMember(long projectId, long userId);
	Either<String, DomainError> updateImage(long projectId, String newImageUri);
}

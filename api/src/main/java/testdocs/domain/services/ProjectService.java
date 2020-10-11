package testdocs.domain.services;

import java.util.List;

import testdocs.domain.entities.Project;
import testdocs.domain.entities.User;
import testdocs.domain.erros.DomainError;
import testdocs.domain.erros.Either;

public interface ProjectService {
	Either<Project, DomainError> createProduct(Project project, long ownerId);
	Either<Project, DomainError> updateProject(Project project, long projectId);
	Either<List<Project>, DomainError> findAllByOwnerId(long ownerId);
	Either<Project, DomainError> findById(long id);
	Either<User, DomainError> addMember(long projectId, String userId);
	Either<List<User>, DomainError> findUsersByProjectId(long projectId);
	Either<String, DomainError> deleteMember(long projectId, long userId);
}

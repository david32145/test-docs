package testdocs.application.controllers;

import java.sql.SQLException;
import java.util.List;

import testdocs.application.dto.CreateMemberDTO;
import testdocs.application.shared.ServletResponse;
import testdocs.domain.entities.Project;
import testdocs.domain.entities.User;
import testdocs.domain.erros.DomainError;
import testdocs.domain.erros.Either;
import testdocs.domain.services.ProjectService;
import testdocs.infra.data.ProjectRepositoryImpl;
import testdocs.infra.data.UserRepositoryImpl;
import testdocs.services.ProjectServiceImpl;

public class ProjectController {
	
	private ProjectService projectService;
	
	public ProjectController() throws ClassNotFoundException, SQLException {
		this.projectService = new ProjectServiceImpl(new ProjectRepositoryImpl(), new UserRepositoryImpl());
	}

	public ServletResponse createProject(Project project, long loggedUserId) {
		Either<Project, DomainError> createProjectResult = projectService.createProduct(project, loggedUserId);
		if(createProjectResult.hasData()) {
			return ServletResponse.created(createProjectResult.getData());
		}
		return ServletResponse.error(createProjectResult.getError());
	}
	
	public ServletResponse updateProject(Project project, long projectId) {
		Either<Project, DomainError> updateProjectResult = projectService.updateProject(project, projectId);
		if(updateProjectResult.hasData()) {
			return ServletResponse.ok(updateProjectResult.getData());
		}
		return ServletResponse.error(updateProjectResult.getError());
	}
	
	public ServletResponse findAllProjectByLoggedUser(long loggerUserId) {
		Either<List<Project>, DomainError> findResult = projectService.findAllByOwnerId(loggerUserId);
		if(findResult.hasData()) {
			return ServletResponse.ok(findResult.getData());
		}
		return ServletResponse.error(findResult.getError());
	}
	
	public ServletResponse findById(long projectId) {
		Either<Project, DomainError> findResult = projectService.findById(projectId);
		if(findResult.hasData()) {
			return ServletResponse.ok(findResult.getData());
		}
		return ServletResponse.error(findResult.getError());
	}
	
	public ServletResponse addMember(long projectId, CreateMemberDTO requestBody) {
		Either<User, DomainError> addMemberResult = projectService.addMember(projectId, requestBody.getUserEmail());
		if(addMemberResult.hasData()) {
			return ServletResponse.ok(addMemberResult.getData());
		}
		return ServletResponse.error(addMemberResult.getError());
	}
	
	public ServletResponse findAllMembers(long projectId) {
		Either<List<User>, DomainError> findMembersResult = projectService.findUsersByProjectId(projectId);
		if(findMembersResult.hasData()) {
			return ServletResponse.ok(findMembersResult.getData());
		}
		return ServletResponse.error(findMembersResult.getError());
	}
	
	public ServletResponse deleteMember(long projectId, long userId) {
		Either<String, DomainError> findMembersResult = projectService.deleteMember(projectId, userId);
		if(findMembersResult.hasData()) {
			return ServletResponse.ok(findMembersResult.getData());
		}
		return ServletResponse.error(findMembersResult.getError());	
	}
}

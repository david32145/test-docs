package testdocs.services;

import java.util.List;

import testdocs.domain.entities.Project;
import testdocs.domain.entities.User;
import testdocs.domain.erros.DomainError;
import testdocs.domain.erros.Either;
import testdocs.domain.erros.Left;
import testdocs.domain.erros.Rigth;
import testdocs.domain.repositories.ProjectRepository;
import testdocs.domain.repositories.UserRepository;
import testdocs.domain.services.ProjectService;

public class ProjectServiceImpl implements ProjectService {

	private ProjectRepository projectRepository;
	private UserRepository userRepository;

	public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository) {
		this.projectRepository = projectRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Either<Project, DomainError> createProduct(Project product, long ownerId) {
		User owner = userRepository.findById(ownerId);
		if (owner == null) {
			return new Left<>(new DomainError("USER_NOT_FOUND", "Esse usuário não existe mais"));
		}
		product.relationshipWithOwnerId(owner.getId());
		product = projectRepository.add(product);
		if (product == null) {
			return new Left<>(new DomainError("UNKNOWN_ERROR", "Ocorreu um error ao criar o projeto"));
		}
		return new Rigth<>(product);
	}

	@Override
	public Either<Project, DomainError> updateProject(Project project, long projectId) {
		Project oldProject = projectRepository.findById(projectId);
		if (oldProject == null) {
			return new Left<>(new DomainError("PROJECT_NOT_FOUND", "projeto não encotrado"));
		}
		Project newProject = new Project(projectId, project.getName(), project.getDescription(),
				project.getDescription(), oldProject.getOwnerId(), project.getImageUri());
		newProject = projectRepository.update(newProject);
		if (newProject == null) {
			return new Left<>(new DomainError("UNKNOWN_ERROR", "Ocorreu um error ao atualizar o projeto"));
		}
		return new Rigth<>(newProject);
	}

	@Override
	public Either<List<Project>, DomainError> findAllByOwnerId(long ownerId) {
		List<Project> projects = projectRepository.findAllByOwnerId(ownerId);
		if (projects == null) {
			return new Left<>(new DomainError("UNKNOWN_ERROR", "Ocorreu um error ao buscar seus projetos"));
		}
		return new Rigth<>(projects);
	}

	@Override
	public Either<Project, DomainError> findById(long id) {
		Project project = projectRepository.findById(id);
		if (project == null) {
			return new Left<>(new DomainError("PROJECT_NOT_FOUND", "Projeto não encotrado"));
		}
		return new Rigth<>(project);
	}

	@Override
	public Either<User, DomainError> addMember(long projectId, String userEmail) {
		boolean projectNotExists = projectRepository.existsById(projectId) == false;
		if (projectNotExists) {
			return new Left<>(new DomainError("PROJECT_NOT_FOUND", "Projeto não encontrado"));
		}
		User user = userRepository.findByEmail(userEmail);
		if (user == null) {
			return new Left<>(new DomainError("USER_NOT_FOUND", "Usuário não encontrado"));
		}
		boolean memberAlreadyExists = projectRepository.memberExists(projectId, user.getId());
		if (memberAlreadyExists) {
			return new Left<>(new DomainError("MEMBER_ALREADY_EXISTS", "Esse usuário já está no projeto"));
		}
		boolean addMemberStatus = projectRepository.addMember(projectId, user.getId());
		if (addMemberStatus) {
			return new Rigth<>(user);
		}
		return new Left<>(new DomainError("UNKNOWN_ERROR", "Ocorreu um error ao adicionar o membro"));
	}

	@Override
	public Either<List<User>, DomainError> findUsersByProjectId(long projectId) {
		List<User> users = userRepository.findUsersByProjectId(projectId);
		if (users == null) {
			return new Left<>(new DomainError("UNKNOWN_ERROR", "Ocorreu um error ao buscar os membros"));
		}
		return new Rigth<>(users);
	}
	
	@Override
	public Either<String, DomainError> deleteMember(long projectId, long userId) {
		boolean removeMemberStatus = projectRepository.deleteMember(projectId, userId);
		if(removeMemberStatus) {
			return new Rigth<>("ok");
		}
		return new Left<>(new DomainError("UNKNOWN_ERROR", "Ocorreu um error ao deletar o membro"));
	}

}

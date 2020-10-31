package br.ufc.crateus.testdocs.services;

import java.util.List;
import java.util.Map;

import javax.validation.Validator;

import org.springframework.stereotype.Service;

import br.ufc.crateus.testdocs.domain.entities.Project;
import br.ufc.crateus.testdocs.domain.entities.User;
import br.ufc.crateus.testdocs.domain.erros.DomainError;
import br.ufc.crateus.testdocs.domain.erros.Either;
import br.ufc.crateus.testdocs.domain.erros.Left;
import br.ufc.crateus.testdocs.domain.erros.Rigth;
import br.ufc.crateus.testdocs.domain.erros.ValidatorError;
import br.ufc.crateus.testdocs.domain.repositories.ProjectRepository;
import br.ufc.crateus.testdocs.domain.repositories.UserRepository;
import br.ufc.crateus.testdocs.domain.services.ProjectService;

@Service
public class ProjectServiceImpl extends BaseService implements ProjectService {

	private ProjectRepository projectRepository;
	private UserRepository userRepository;

	public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository, Validator validator) {
		super(validator);
		this.projectRepository = projectRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Either<Project, DomainError> createProduct(Project product, long ownerId) {
		Map<String, String> errors = validateEntity(product);
		if(!errors.isEmpty()) {
			return new Left<>(new ValidatorError(errors));
		}
		User owner = userRepository.findById(ownerId);
		if (owner == null) {
			return new Left<>(new DomainError("USER_NOT_FOUND", "Esse usuário não existe mais"));
		}
		product.addOwner(owner);
		product = projectRepository.save(product);
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
				project.getGithubUri(), project.getImageUri(), oldProject.getOwner());
		newProject = projectRepository.save(newProject);
		if (newProject == null) {
			return new Left<>(new DomainError("UNKNOWN_ERROR", "Ocorreu um error ao atualizar o projeto"));
		}
		return new Rigth<>(newProject);
	}
	
	@Override
	public Either<String, DomainError> updateImage(long projectId,
			String newImageUri) {
		Project project = projectRepository.findById(projectId);
		if (project == null) {
			return new Left<>(new DomainError("PROJECT_NOT_FOUND", "projeto não encotrado"));
		}
		project.changeImageUri(newImageUri);
		project = projectRepository.save(project);
		return new Rigth<>(newImageUri);

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
		Project project = projectRepository.findById(projectId);
		boolean projectNotExists = project == null;
		if (projectNotExists) {
			return new Left<>(new DomainError("PROJECT_NOT_FOUND", "Projeto não encontrado"));
		}
		User user = userRepository.findByEmail(userEmail);
		if (user == null) {
			return new Left<>(new DomainError("USER_NOT_FOUND", "Usuário não encontrado"));
		}
		boolean memberAlreadyExists = projectRepository.memberExists(projectId, user.getEmail());
		if (memberAlreadyExists) {
			return new Left<>(new DomainError("MEMBER_ALREADY_EXISTS", "Esse usuário já está no projeto"));
		}
		boolean addMemberStatus = projectRepository.addMember(project, user);
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
		Project project = projectRepository.findById(projectId);
		users.add(users.size() - 1, project.getOwner());
		return new Rigth<>(users);
	}
	
	@Override
	public Either<String, DomainError> deleteMember(long projectId, long userId) {
		Project project = projectRepository.findById(projectId);
		boolean removeMemberStatus = projectRepository.deleteMember(project, userId);
		if(removeMemberStatus) {
			return new Rigth<>("ok");
		}
		return new Left<>(new DomainError("UNKNOWN_ERROR", "Ocorreu um error ao deletar o membro"));
	}

}

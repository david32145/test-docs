package testdocs.domain.repositories;

import java.util.List;

import testdocs.domain.entities.Project;

public interface ProjectRepository {
	Project add(Project project);
	Project update(Project project);
	Project findById(long id);
	List<Project> findAllByOwnerId(long userId);
	boolean existsById(long id);
	boolean addMember(long id, long userId);
	boolean memberExists(long id, long userId);
	boolean deleteMember(long id, long userId);
}

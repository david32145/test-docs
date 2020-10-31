package br.ufc.crateus.testdocs.domain.repositories;

import java.util.List;

import br.ufc.crateus.testdocs.domain.entities.Project;
import br.ufc.crateus.testdocs.domain.entities.User;

public interface ProjectRepository {
	Project save(Project project);
	Project findById(Long id);
	List<Project> findAllByOwnerId(long userId);
	Boolean existsById(Long id);
	public Boolean addMember(Project project, User user);
	Boolean memberExists(Long id, String userEmail);
	Boolean deleteMember(Project project, Long userId);
}

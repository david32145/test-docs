package br.ufc.crateus.testdocs.data.repositories;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufc.crateus.testdocs.data.dao.ProjectDao;
import br.ufc.crateus.testdocs.domain.entities.Project;
import br.ufc.crateus.testdocs.domain.entities.User;
import br.ufc.crateus.testdocs.domain.repositories.ProjectRepository;

@Service
public class ProjectRepositoryImpl implements ProjectRepository{
	private ProjectDao projectDao;
	
	public ProjectRepositoryImpl(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	@Override
	public Project save(Project project) {
		return this.projectDao.save(project);
	}

	@Override
	public Project findById(Long id) {
		return this.projectDao.findById(id).orElse(null);
	}

	@Override
	public List<Project> findAllByOwnerId(long userId) {
		return this.findAllByOwnerId(userId);
	}

	@Override
	public Boolean existsById(Long id) {
		return this.existsById(id);
	}

	@Override
	public Boolean addMember(Project project, User user) {
		project.addMember(user);
		this.save(project);
		return true;
	}

	@Override
	public Boolean memberExists(Long id, String userEmail) {
		return this.projectDao.existsMember(id, userEmail) != null;
	}

	@Override
	public Boolean deleteMember(Project project, Long userId) {
		project.deleteByMemberId(userId);
		this.projectDao.save(project);
		return true;
	}

	
}

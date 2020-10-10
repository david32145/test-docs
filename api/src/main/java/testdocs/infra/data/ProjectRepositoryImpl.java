package testdocs.infra.data;

import java.sql.SQLException;
import java.util.List;

import testdocs.domain.entities.Project;
import testdocs.domain.repositories.ProjectRepository;
import testdocs.infra.data.dao.ProjectDao;

public class ProjectRepositoryImpl implements ProjectRepository {
	private ProjectDao projectDao;

	public ProjectRepositoryImpl() throws ClassNotFoundException, SQLException {
		this.projectDao = ProjectDao.getInstace();
	}

	@Override
	public Project add(Project project) {
		try {
			return projectDao.save(project);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Project update(Project project) {
		try {
			return projectDao.update(project);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Project findById(long id) {
		try {
			return projectDao.findById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Project> findAllByOwnerId(long userId) {
		try {
			return projectDao.findAllByUserId(userId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean existsById(long id) {
		return findById(id) != null;
	}
	
	@Override
	public boolean addMember(long id, long userId) {
		try {
			return projectDao.addMember(id, userId);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean memberExists(long id, long userId) {
		try {
			return projectDao.memberExists(id, userId);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean deleteMember(long id, long userId) {
		try {
			return projectDao.deleteMember(id, userId);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}

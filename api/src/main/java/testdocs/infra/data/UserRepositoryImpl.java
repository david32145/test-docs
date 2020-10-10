package testdocs.infra.data;

import java.sql.SQLException;
import java.util.List;

import testdocs.domain.entities.User;
import testdocs.domain.repositories.UserRepository;
import testdocs.infra.data.dao.UserDao;

public class UserRepositoryImpl implements UserRepository {
	private UserDao userDao;
	
	public UserRepositoryImpl() throws ClassNotFoundException, SQLException {
		this.userDao = UserDao.getInstace();
	}

	@Override
	public User add(User user) {
		try {
			return userDao.save(user);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findByEmail(String email) {
		try {
			return userDao.findByEmail(email);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public User findById(long id) {
		try {
			return userDao.findById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean existsByEmail(String email) {
		return findByEmail(email) != null;
	}
	
	@Override
	public List<User> findUsersByProjectId(long projectId) {
		try {
			return userDao.findUsersByProjectId(projectId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean existsByIds(String[] ids) {
		try {
			return userDao.findByIds(ids).size() == ids.length;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}

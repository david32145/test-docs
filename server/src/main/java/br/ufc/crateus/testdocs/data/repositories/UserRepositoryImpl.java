package br.ufc.crateus.testdocs.data.repositories;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import br.ufc.crateus.testdocs.data.dao.UserDao;
import br.ufc.crateus.testdocs.domain.entities.User;
import br.ufc.crateus.testdocs.domain.repositories.UserRepository;

@Service
public class UserRepositoryImpl implements UserRepository {
	
	private UserDao userDao;
	
	public UserRepositoryImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public User add(User user) {
		return this.userDao.save(user);
	}

	@Override
	public User findByEmail(String email) {
		return this.userDao.findByEmail(email);
	}

	@Override
	public boolean existsByEmail(String email) {
		return this.userDao.findByEmail(email) != null;
	}

	@Override
	public boolean existsByIds(Long[] ids) {
		return this.userDao.findByIds(Arrays.asList(ids)).size() == ids.length;
	}

	@Override
	public List<User> findUsersByProjectId(long projectId) {
		return this.userDao.findAllByProjectId(projectId);
	}

	@Override
	public User findById(long id) {
		return this.userDao.findById(id).orElse(null);
	}
	
	@Override
	public List<User> findByIds(Long[] ids) {
		return (List<User>) this.userDao.findAllById(Arrays.asList(ids));
	}
	
}

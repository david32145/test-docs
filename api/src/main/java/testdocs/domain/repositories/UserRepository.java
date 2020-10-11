package testdocs.domain.repositories;

import java.util.List;

import testdocs.domain.entities.User;

public interface UserRepository {
	User add(User user);
	User findByEmail(String email);
	boolean existsByEmail(String email);
	boolean existsByIds(String[] ids);
	List<User> findUsersByProjectId(long projectId);
	User findById(long id);
}

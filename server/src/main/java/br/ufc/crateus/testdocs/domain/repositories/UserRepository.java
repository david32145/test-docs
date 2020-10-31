package br.ufc.crateus.testdocs.domain.repositories;

import java.util.List;

import br.ufc.crateus.testdocs.domain.entities.User;

public interface UserRepository {
	User add(User user);
	User findByEmail(String email);
	boolean existsByEmail(String email);
	boolean existsByIds(Long[] ids);
	List<User> findUsersByProjectId(long projectId);
	List<User> findByIds(Long[] ids);
	User findById(long id);
}

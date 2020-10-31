package br.ufc.crateus.testdocs.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufc.crateus.testdocs.domain.entities.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
	User findByEmail(String email);
	
	@Query("select u from User u where u.id in :ids")
	List<User> findByIds(@Param("ids") List<Long> ids);
	
	@Query("select u from User u join u.projects pu where pu.id = :projectId")
	List<User> findAllByProjectId(@Param("projectId") long projectId);
}

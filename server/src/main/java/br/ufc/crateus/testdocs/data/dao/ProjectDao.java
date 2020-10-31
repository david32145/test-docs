package br.ufc.crateus.testdocs.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufc.crateus.testdocs.domain.entities.Project;
import br.ufc.crateus.testdocs.domain.entities.User;

@Repository
public interface ProjectDao extends CrudRepository<Project, Long>{
	@Query("select p from Project p where p.owner.id = :ownerId")
	List<Project> findByOwnerId(@Param("ownerId") Long ownerId);

	@Query("select u from User u join u.projects pu where pu.id = :projectId and u.email = :userEmail")
	User existsMember(@Param("projectId") long projectId, @Param("userEmail") String userEmail);

	@Query("select p.id, p.name, (select count(p2m) from Project p2 join p2.members p2m where p2.id = p.id) + 1, p.owner.name, p.imageUri from Project p where p.owner.id = :ownerId")
	List<String[]> listProject(@Param("ownerId") long ownerId);
}

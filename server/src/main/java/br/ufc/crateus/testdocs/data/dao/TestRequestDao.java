package br.ufc.crateus.testdocs.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufc.crateus.testdocs.domain.entities.TestRequest;

@Repository
public interface TestRequestDao extends CrudRepository<TestRequest, Long> {
	@Query("select t from TestRequest t join t.project tp where tp.id = :projectId")
	List<TestRequest> findAllByProjectId(@Param("projectId") long projectId);
}

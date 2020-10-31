package br.ufc.crateus.testdocs.data.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.crateus.testdocs.domain.entities.TestSuite;

@Repository
public interface TestSuiteDao extends CrudRepository<TestSuite, Long>{
	List<TestSuite> findAllByTestRequestId(Long id);
}

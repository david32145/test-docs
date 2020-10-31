package br.ufc.crateus.testdocs.data.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ufc.crateus.testdocs.domain.entities.TestCase;

@Repository
public interface TestCaseDao extends CrudRepository<TestCase, Long>{

}

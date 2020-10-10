package testdocs.domain.repositories;

import java.util.List;

import testdocs.domain.entities.TestCase;
import testdocs.domain.entities.TestRequest;
import testdocs.domain.entities.TestSuite;

public interface TestRequestRespository {
	TestRequest add(TestRequest testRequest);
	boolean delete(long id);
	boolean existsById(long id);
	List<TestRequest> findAllByProjectId(long projectId);
	TestCase addTestCase(TestCase testCase);
	TestSuite addTestSuite(TestSuite testSuite);
	boolean testSuiteById(long testSuiteId);
}

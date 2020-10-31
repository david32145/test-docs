package br.ufc.crateus.testdocs.data.repositories;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufc.crateus.testdocs.data.dao.TestCaseDao;
import br.ufc.crateus.testdocs.data.dao.TestRequestDao;
import br.ufc.crateus.testdocs.data.dao.TestSuiteDao;
import br.ufc.crateus.testdocs.domain.entities.TestCase;
import br.ufc.crateus.testdocs.domain.entities.TestRequest;
import br.ufc.crateus.testdocs.domain.entities.TestSuite;
import br.ufc.crateus.testdocs.domain.repositories.TestRequestRespository;

@Service
public class TestRequestRepositoryImpl implements TestRequestRespository {
	private TestRequestDao testRequestDao;
	private TestSuiteDao testSuiteDao;
	private TestCaseDao testCaseDao;
	
	public TestRequestRepositoryImpl(TestRequestDao testRequestDao,
			TestSuiteDao testSuiteDao, TestCaseDao testCaseDao) {
		this.testRequestDao = testRequestDao;
		this.testSuiteDao = testSuiteDao;
		this.testCaseDao = testCaseDao;
	}

	@Override
	public TestRequest add(TestRequest testRequest) {
		return this.testRequestDao.save(testRequest);
	}

	@Override
	public boolean delete(long id) {
		this.testRequestDao.deleteById(id);
		return true;
	}

	@Override
	public boolean existsById(long id) {
		return this.testRequestDao.existsById(id);
	}

	@Override
	public List<TestRequest> findAllByProjectId(long projectId) {
		return this.testRequestDao.findAllByProjectId(projectId);
	}

	@Override
	public TestCase addTestCase(TestCase testCase) {
		return this.testCaseDao.save(testCase);
	}

	@Override
	public TestSuite addTestSuite(TestSuite testSuite) {
		return this.testSuiteDao.save(testSuite);
	}

	@Override
	public boolean testSuiteById(long testSuiteId) {
		return this.testSuiteDao.existsById(testSuiteId);
	}
	
	@Override
	public List<TestSuite> findAllTestSuiteByTestRequestId(long testRequestId) {
		return this.testSuiteDao.findAllByTestRequestId(testRequestId);
	}

}

package testdocs.infra.data;

import java.sql.SQLException;
import java.util.List;

import testdocs.domain.entities.TestCase;
import testdocs.domain.entities.TestRequest;
import testdocs.domain.entities.TestSuite;
import testdocs.domain.repositories.TestRequestRespository;
import testdocs.infra.data.dao.TestRequestDao;

public class TestRequestRepositoryImpl implements TestRequestRespository {
	private TestRequestDao testRequestDao;
			
	public TestRequestRepositoryImpl() throws ClassNotFoundException, SQLException {
		this.testRequestDao = TestRequestDao.getInstance();
	}
	
	@Override
	public TestRequest add(TestRequest testRequest) {
		try {
			return testRequestDao.insert(testRequest);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(long id) {
		try {
			return testRequestDao.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<TestRequest> findAllByProjectId(long projectId) {
		try {
			return testRequestDao.selectAllBydProjectId(projectId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public TestSuite addTestSuite(TestSuite testSuite) {
		try {
			return testRequestDao.insertTestSuite(testSuite);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public TestCase addTestCase(TestCase testCase) {
		try {
			return testRequestDao.insertTestCase(testCase);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean testSuiteById(long testSuiteId) {
		try {
			return testRequestDao.testSuiteExistsById(testSuiteId);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean existsById(long id) {
		try {
			return testRequestDao.existsById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}

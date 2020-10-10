package testdocs.infra.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import testdocs.domain.entities.TestCase;
import testdocs.domain.entities.TestRequest;
import testdocs.domain.entities.TestSuite;
import testdocs.infra.JDBCPostgresConnection;
import testdocs.infra.data.dao.enums.TestRequestStatus;

public class TestRequestDao implements Dao<TestRequest> {
	private final static String INSERT_SQL = "insert into test_requests (version, project_id, status, requesting_user_id, tester_user_id, released_timestamp) values (?, ?, 'REQUESTED',?, ?, 0)";
	private final static String DELETE_SQL_BY_ID = "delete from test_requests where id = ?";
	private final static String SELECT_BY_ID = "select * from test_requests where id = ?;";
	private final static String SELECT_ALL_BY_PROJECT_ID = "select * from test_requests where project_id = ?";
	private final static String INSERT_TEST_SUITE = "insert into test_suites (title, description, test_request_id) values (?, ?, ?);";
	private final static String INSERT_TEST_CASE = "insert into test_cases (title, description, suite_id) values (?, ?, ?);";
	private final static String SELECT_TEST_SUITE_BY_ID = "select * from test_suites where id = ?;";
	
	private Connection connection;
	
	private static TestRequestDao instance;
	
	public static TestRequestDao getInstance() throws ClassNotFoundException, SQLException {
		if(instance == null) {
			instance = new TestRequestDao();
		}
		return instance;
	}
	
	private TestRequestDao() throws ClassNotFoundException, SQLException {
		connection = JDBCPostgresConnection.getConnection();
	}
	
	@Override
	public TestRequest mapRow(ResultSet resultSet) throws SQLException {
		long id = resultSet.getLong(1);
		String version = resultSet.getString(2);
		long projectId = resultSet.getLong(3);
		long requestingUserId = resultSet.getLong(4);
		TestRequestStatus status = TestRequestStatus.valueOf(resultSet.getString(5));
		long testerUserId = resultSet.getLong(6);
		long releasedTimestamp = resultSet.getLong(7);
		return new TestRequest(id, version, projectId, status, requestingUserId, testerUserId, releasedTimestamp);
	}

	
	public TestRequest insert(TestRequest testRequest) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, testRequest.getVersion());
		preparedStatement.setLong(2, testRequest.getProjectId());
		preparedStatement.setLong(3, testRequest.getRequestingUserId());
		preparedStatement.setLong(4, testRequest.getTesterUserId());
		boolean success = preparedStatement.executeUpdate() > 0;
		if(success) {
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			long generatedId = resultSet.getLong(1);
			return new TestRequest(generatedId, testRequest.getVersion(), testRequest.getProjectId(), testRequest.getStatus(), testRequest.getRequestingUserId(), testRequest.getTesterUserId(), 0);
		}
		return null;
	}
	
	public boolean delete(long id) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL_BY_ID);
		preparedStatement.setLong(1, id);
		return preparedStatement.executeUpdate() > 0;
	}
	
	public boolean existsById(long id) throws SQLException {
		PreparedStatement preparedStatement= connection.prepareStatement(SELECT_BY_ID);
		preparedStatement.setLong(1, id);
		return preparedStatement.executeQuery().next();
	}
	
	public List<TestRequest> selectAllBydProjectId(long projectId) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_PROJECT_ID);
		preparedStatement.setLong(1, projectId);
		List<TestRequest> testRequests = new ArrayList<>();
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			testRequests.add(mapRow(resultSet));
		}
		return testRequests;
	}
	
	public TestSuite insertTestSuite(TestSuite testSuite) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TEST_SUITE, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, testSuite.getTitle());
		preparedStatement.setString(2, testSuite.getDescription());
		preparedStatement.setLong(3, testSuite.getTestRequestId());
		boolean success = preparedStatement.executeUpdate() > 0;
		if(success) {
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			long generatedId = resultSet.getLong(1);
			return new TestSuite(generatedId, testSuite.getTitle(), testSuite.getDescription(), testSuite.getTestRequestId());
		}
		return null;
	}
	
	public TestCase insertTestCase(TestCase testCase) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TEST_CASE, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, testCase.getTitle());
		preparedStatement.setString(2, testCase.getDescription());
		preparedStatement.setLong(3, testCase.getTestSuiteId());
		boolean success = preparedStatement.executeUpdate() > 0;
		if(success) {
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			long generatedId = resultSet.getLong(1);
			return new TestCase(generatedId, testCase.getTitle(), testCase.getDescription(), testCase.getTestSuiteId());
		}
		return null;
	}
	
	public boolean testSuiteExistsById(long testSuiteId) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TEST_SUITE_BY_ID);
		preparedStatement.setLong(1, testSuiteId);
		return preparedStatement.executeQuery().next();
	}
}

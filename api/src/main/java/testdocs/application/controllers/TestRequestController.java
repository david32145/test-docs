package testdocs.application.controllers;

import java.sql.SQLException;
import java.util.List;

import testdocs.application.dto.CreateTestRequestDTO;
import testdocs.application.shared.ServletResponse;
import testdocs.domain.entities.TestCase;
import testdocs.domain.entities.TestRequest;
import testdocs.domain.entities.TestSuite;
import testdocs.domain.erros.DomainError;
import testdocs.domain.erros.Either;
import testdocs.domain.repositories.ProjectRepository;
import testdocs.domain.repositories.TestRequestRespository;
import testdocs.domain.repositories.UserRepository;
import testdocs.domain.services.TestRequestService;
import testdocs.infra.data.ProjectRepositoryImpl;
import testdocs.infra.data.TestRequestRepositoryImpl;
import testdocs.infra.data.UserRepositoryImpl;
import testdocs.services.TestRequestServiceImpl;

public class TestRequestController {
	private TestRequestService requestService;
	
	public TestRequestController() throws ClassNotFoundException, SQLException {
		UserRepository userRepository = new UserRepositoryImpl();
		ProjectRepository projectRepository = new ProjectRepositoryImpl();
		TestRequestRespository testRequestRespository = new TestRequestRepositoryImpl();
		this.requestService = new TestRequestServiceImpl(userRepository, projectRepository, testRequestRespository);
	}
	
	public ServletResponse createTestRequest(CreateTestRequestDTO requestBody) {
		TestRequest testRequest = TestRequest.create(requestBody.getVersion(), requestBody.getProjectId(), requestBody.getRequestingUserId(), requestBody.getTesterUserId());
		Either<TestRequest, DomainError> createTestRequestResult = requestService.createTestRequest(testRequest);
		if(createTestRequestResult.hasData()) {
			return ServletResponse.created(createTestRequestResult.getData());
		}
		return ServletResponse.error(createTestRequestResult.getError());
	}
	
	public ServletResponse deleteTestRequest(long testRequestId) {
		Either<Boolean, DomainError> deleteTestRequestResult = requestService.deleteTestRequest(testRequestId);
		if(deleteTestRequestResult.hasData()) {
			return ServletResponse.ok(deleteTestRequestResult.getData());
		}
		return ServletResponse.error(deleteTestRequestResult.getError());
	}
	
	public ServletResponse findAllTestRequestByProjectId(long projectId) {
		Either<List<TestRequest>, DomainError> findTestRequestResult = requestService.findTestRequestByProjectId(projectId);
		if(findTestRequestResult.hasData()) {
			return ServletResponse.ok(findTestRequestResult.getData());
		}
		return ServletResponse.error(findTestRequestResult.getError());
	}
	
	public ServletResponse createTestSuite(TestSuite testSuite) {
		Either<TestSuite, DomainError> createTestSuiteResult = requestService.createTestSuite(testSuite);
		if(createTestSuiteResult.hasData()) {
			return ServletResponse.ok(createTestSuiteResult.getData());
		}
		return ServletResponse.error(createTestSuiteResult.getError());
	}
	
	public ServletResponse createTestCase(TestCase testCase) {
		Either<TestCase, DomainError> createTestCaseResult = requestService.createTestCase(testCase);
		if(createTestCaseResult.hasData()) {
			return ServletResponse.ok(createTestCaseResult.getData());
		}
		return ServletResponse.error(createTestCaseResult.getError());
	}
}

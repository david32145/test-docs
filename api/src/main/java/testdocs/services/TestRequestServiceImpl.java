package testdocs.services;

import java.util.List;

import testdocs.domain.entities.TestCase;
import testdocs.domain.entities.TestRequest;
import testdocs.domain.entities.TestSuite;
import testdocs.domain.erros.DomainError;
import testdocs.domain.erros.Either;
import testdocs.domain.erros.Left;
import testdocs.domain.erros.Rigth;
import testdocs.domain.repositories.ProjectRepository;
import testdocs.domain.repositories.TestRequestRespository;
import testdocs.domain.repositories.UserRepository;
import testdocs.domain.services.TestRequestService;

public class TestRequestServiceImpl implements TestRequestService {
	private UserRepository userRepository;
	private ProjectRepository projectRepository;
	private TestRequestRespository testRequestRepository;

	public TestRequestServiceImpl(UserRepository userRepository, ProjectRepository projectRepository,
			TestRequestRespository testRequestRepository) {
		this.userRepository = userRepository;
		this.projectRepository = projectRepository;
		this.testRequestRepository = testRequestRepository;
	}

	@Override
	public Either<TestRequest, DomainError> createTestRequest(TestRequest testRequest) {
		String[] ids = { testRequest.getTesterUserId() + "", testRequest.getRequestingUserId() + "" };
		boolean testerAndRequestingUserExists = userRepository.existsByIds(ids);
		if (!testerAndRequestingUserExists) {
			return new Left<>(new DomainError("USER_NOT_FOUND", "tester ou requesting user id not exists"));
		}
		boolean projectExists = projectRepository.existsById(testRequest.getProjectId());
		if (!projectExists) {
			return new Left<>(new DomainError("PROJECT_NOT_FOUND", "projecto não encontrado"));
		}
		testRequest = testRequestRepository.add(testRequest);
		if (testRequest == null) {
			return new Left<>(new DomainError("UNKNOWN_ERROR", "Ocorreu um error ao criar a requisição de teste"));
		}
		return new Rigth<>(testRequest);
	}

	@Override
	public Either<Boolean, DomainError> deleteTestRequest(long testRequestId) {
		boolean testRequestDeleted = testRequestRepository.delete(testRequestId);
		if(testRequestDeleted) {
			return new Rigth<>(true);
		}
		return new Left<>(new DomainError("UNKNOWN_ERROR", "Ocorreu um error ao deletar a requisição de testes"));
	}

	@Override
	public Either<List<TestRequest>, DomainError> findTestRequestByProjectId(long projectId) {
		List<TestRequest> requests = testRequestRepository.findAllByProjectId(projectId);
		if(requests == null) {
			return new Left<>(new DomainError("UNKNOWN_ERROR", "Ocorreu um error ao carregar seus casos de testes"));
		}
		return new Rigth<>(requests);
	}
	
	@Override
	public Either<TestSuite, DomainError> createTestSuite(TestSuite testSuite) {
		boolean testRequestExistsById = testRequestRepository.existsById(testSuite.getTestRequestId());
		if(!testRequestExistsById) {
			return new Left<>(new DomainError("TEST_REQUEST_NOT_FOUND", "Requisição de teste não encontrada"));
		}
		testSuite = testRequestRepository.addTestSuite(testSuite);
		if(testSuite == null) {
			return new Left<>(new DomainError("UNKNOWN_ERROR", "Ocorreu um error ao criar a suite de teste"));
		}
		return new Rigth<>(testSuite);
	}
	
	@Override
	public Either<TestCase, DomainError> createTestCase(TestCase testCase) {
		boolean testSuiteExists = testRequestRepository.testSuiteById(testCase.getTestSuiteId());
		if(!testSuiteExists) {
			return new Left<>(new DomainError("TEST_SUITE_NOT_FOUND", "Suite de testes não encontrada"));
		}
		testCase = testRequestRepository.addTestCase(testCase);
		if(testCase == null) {
			return new Left<>(new DomainError("UNKNOWN_ERROR", "Ocorreu um error ao criar o caso de teste"));
		}
		return new Rigth<>(testCase);
	}
}

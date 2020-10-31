package br.ufc.crateus.testdocs.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufc.crateus.testdocs.domain.entities.Project;
import br.ufc.crateus.testdocs.domain.entities.TestCase;
import br.ufc.crateus.testdocs.domain.entities.TestRequest;
import br.ufc.crateus.testdocs.domain.entities.TestSuite;
import br.ufc.crateus.testdocs.domain.entities.User;
import br.ufc.crateus.testdocs.domain.erros.DomainError;
import br.ufc.crateus.testdocs.domain.erros.Either;
import br.ufc.crateus.testdocs.domain.erros.Left;
import br.ufc.crateus.testdocs.domain.erros.Rigth;
import br.ufc.crateus.testdocs.domain.repositories.ProjectRepository;
import br.ufc.crateus.testdocs.domain.repositories.TestRequestRespository;
import br.ufc.crateus.testdocs.domain.repositories.UserRepository;
import br.ufc.crateus.testdocs.domain.services.TestRequestService;

@Service
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
		Long[] ids = { testRequest.getTesterUser().getId(), testRequest.getRequestingUser().getId()};
		List<User> testerAndRequestingUsers = userRepository.findByIds(ids);
		if (testerAndRequestingUsers.size() != 2) {
			return new Left<>(new DomainError("USER_NOT_FOUND", "tester ou requesting user id not exists"));
		}
		Project project = projectRepository.findById(testRequest.getProject().getId());
		if (project == null) {
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
		boolean testRequestExistsById = testRequestRepository.existsById(testSuite.getTestRequest().getId());
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
		boolean testSuiteExists = testRequestRepository.testSuiteById(testCase.getTestSuite().getId());
		if(!testSuiteExists) {
			return new Left<>(new DomainError("TEST_SUITE_NOT_FOUND", "Suite de testes não encontrada"));
		}
		testCase = testRequestRepository.addTestCase(testCase);
		if(testCase == null) {
			return new Left<>(new DomainError("UNKNOWN_ERROR", "Ocorreu um error ao criar o caso de teste"));
		}
		return new Rigth<>(testCase);
	}

	@Override
	public Either<List<TestSuite>, DomainError> findAllTestSuiteByTestRequest(
			Long testRequestId) {
		boolean testRequestExists = testRequestRepository.existsById(testRequestId);
		if(!testRequestExists) {
			return new Left<>(new DomainError("TEST_REQUEST_NOT_FOUND", "Solicitação de testes não encontrada"));
		}
		List<TestSuite> testSuites = testRequestRepository.findAllTestSuiteByTestRequestId(testRequestId);
		return new Rigth<>(testSuites);
	}
}

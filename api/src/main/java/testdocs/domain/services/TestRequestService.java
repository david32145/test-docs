package testdocs.domain.services;

import java.util.List;

import testdocs.domain.entities.TestCase;
import testdocs.domain.entities.TestRequest;
import testdocs.domain.entities.TestSuite;
import testdocs.domain.erros.DomainError;
import testdocs.domain.erros.Either;

public interface TestRequestService {
	Either<TestRequest, DomainError> createTestRequest(TestRequest testRequest);
	Either<Boolean, DomainError> deleteTestRequest(long testRequestId);
	Either<List<TestRequest>, DomainError> findTestRequestByProjectId(long projectId);
	Either<TestCase, DomainError> createTestCase(TestCase testCase);
	Either<TestSuite, DomainError> createTestSuite(TestSuite testSuite);
}

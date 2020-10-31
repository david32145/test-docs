package br.ufc.crateus.testdocs.domain.services;

import java.util.List;

import br.ufc.crateus.testdocs.domain.entities.TestCase;
import br.ufc.crateus.testdocs.domain.entities.TestRequest;
import br.ufc.crateus.testdocs.domain.entities.TestSuite;
import br.ufc.crateus.testdocs.domain.erros.DomainError;
import br.ufc.crateus.testdocs.domain.erros.Either;

public interface TestRequestService {
	Either<TestRequest, DomainError> createTestRequest(TestRequest testRequest);
	Either<Boolean, DomainError> deleteTestRequest(long testRequestId);
	Either<List<TestRequest>, DomainError> findTestRequestByProjectId(long projectId);
	Either<TestCase, DomainError> createTestCase(TestCase testCase);
	Either<TestSuite, DomainError> createTestSuite(TestSuite testSuite);
	Either<List<TestSuite>, DomainError> findAllTestSuiteByTestRequest(Long testRequestId);
}

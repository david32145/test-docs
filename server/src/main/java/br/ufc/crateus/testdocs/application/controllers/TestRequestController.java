package br.ufc.crateus.testdocs.application.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.crateus.testdocs.data.dao.TestRequestDao;
import br.ufc.crateus.testdocs.domain.entities.TestCase;
import br.ufc.crateus.testdocs.domain.entities.TestRequest;
import br.ufc.crateus.testdocs.domain.entities.TestSuite;
import br.ufc.crateus.testdocs.domain.erros.DomainError;
import br.ufc.crateus.testdocs.domain.erros.Either;
import br.ufc.crateus.testdocs.domain.services.TestRequestService;

@RestController
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/api/test_requests")
public class TestRequestController {
	private TestRequestService testRequestService;
	private TestRequestDao testRequestDao;

	public TestRequestController(TestRequestService testRequestService,
			TestRequestDao testRequestDao) {
		this.testRequestService = testRequestService;
		this.testRequestDao = testRequestDao;
	}

	@PostMapping
	public ResponseEntity<?> createTestRequest(
			@RequestBody TestRequest requestBody) {
		Either<TestRequest, DomainError> createTestRequestResult = testRequestService
				.createTestRequest(requestBody);
		if (createTestRequestResult.hasData()) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(createTestRequestResult.getData());
		}
		return ResponseEntity.badRequest()
				.body(createTestRequestResult.getError());
	}

	@DeleteMapping("/{testRequestId}")
	public ResponseEntity<?> deleteTestRequest(
			@PathVariable("testRequestId") long testRequestId) {
		Either<Boolean, DomainError> deleteTestRequestResult = testRequestService
				.deleteTestRequest(testRequestId);
		if (deleteTestRequestResult.hasData()) {
			return ResponseEntity.ok(deleteTestRequestResult.getData());
		}
		return ResponseEntity.badRequest()
				.body(deleteTestRequestResult.getError());
	}

	@GetMapping("/projects/{projectId}")
	public ResponseEntity<?> findAllTestRequestByProjectId(
			@PathVariable("projectId") long projectId) {
		Either<List<TestRequest>, DomainError> findTestRequestResult = testRequestService
				.findTestRequestByProjectId(projectId);
		if (findTestRequestResult.hasData()) {
			return ResponseEntity.ok(findTestRequestResult.getData());
		}
		return ResponseEntity.badRequest()
				.body(findTestRequestResult.getError());
	}

	@PostMapping("/test_suites")
	public ResponseEntity<?> createTestSuite(@RequestBody TestSuite testSuite) {
		Either<TestSuite, DomainError> createTestSuiteResult = testRequestService
				.createTestSuite(testSuite);
		if (createTestSuiteResult.hasData()) {
			return ResponseEntity.ok(createTestSuiteResult.getData());
		}
		return ResponseEntity.badRequest()
				.body(createTestSuiteResult.getError());
	}

	@GetMapping("/{testRequestId}/test_suites")
	public ResponseEntity<?> getTestSuitesByTestRequestId(
			@PathVariable("testRequestId") long testRequestId) {
		Either<List<TestSuite>, DomainError> createTestSuiteResult = testRequestService
				.findAllTestSuiteByTestRequest(testRequestId);
		if (createTestSuiteResult.hasData()) {
			return ResponseEntity.ok(createTestSuiteResult.getData());
		}
		return ResponseEntity.badRequest()
				.body(createTestSuiteResult.getError());
	}

	@GetMapping("/{testRequestId}")
	public Object getTestRequest(
			@PathVariable("testRequestId") long testRequestId) {
		return testRequestDao.findById(testRequestId);
	}

	@PostMapping("/test_suites/test_cases")
	public ResponseEntity<?> createTestCase(@RequestBody TestCase testCase) {
		Either<TestCase, DomainError> createTestCaseResult = testRequestService
				.createTestCase(testCase);
		if (createTestCaseResult.hasData()) {
			return ResponseEntity.ok(createTestCaseResult.getData());
		}
		return ResponseEntity.badRequest()
				.body(createTestCaseResult.getError());
	}
	
	
	@PutMapping("/{testRequestId}/accept")
	public TestRequest accept(@PathVariable("testRequestId") long testRequestId) {
		TestRequest testRequest = testRequestDao.findById(testRequestId).orElse(null);
		testRequest.accept();
		testRequest = testRequestDao.save(testRequest);
		return testRequest;
	}
	
	@PutMapping("/{testRequestId}/reject")
	public TestRequest reject(@PathVariable("testRequestId") long testRequestId) {
		TestRequest testRequest = testRequestDao.findById(testRequestId).orElse(null);
		testRequest.reject();
		testRequest = testRequestDao.save(testRequest);
		return testRequest;
	}
}

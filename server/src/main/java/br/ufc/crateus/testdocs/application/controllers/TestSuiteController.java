package br.ufc.crateus.testdocs.application.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.crateus.testdocs.data.dao.TestCaseDao;
import br.ufc.crateus.testdocs.data.dao.TestSuiteDao;
import br.ufc.crateus.testdocs.domain.entities.TestCase;

@RestController
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.PUT })
@RequestMapping("/api/test_suites")
public class TestSuiteController {
	private TestSuiteDao testSuiteDao;
	private TestCaseDao testCaseDao;

	public TestSuiteController(TestSuiteDao testSuiteDao,
			TestCaseDao testCaseDao) {
		this.testSuiteDao = testSuiteDao;
		this.testCaseDao = testCaseDao;
	}

	@DeleteMapping("/{testSuiteId}")
	public void delete(@PathVariable("testSuiteId") long testSuiteId) {
		testSuiteDao.deleteById(testSuiteId);
	}

	@PutMapping("/test_case/{testCaseId}/approve")
	public TestCase approve(@PathVariable("testCaseId") long testCaseId) {
		TestCase testCase = testCaseDao.findById(testCaseId).orElse(null);
		testCase.approve();
		testCase = testCaseDao.save(testCase);
		return testCase;
	}

	@PutMapping("/test_case/{testCaseId}/disapprove")
	public TestCase disapprove(@PathVariable("testCaseId") long testCaseId) {
		TestCase testCase = testCaseDao.findById(testCaseId).orElse(null);
		testCase.disapprove();
		testCaseDao.save(testCase);
		return testCase;
	}
}

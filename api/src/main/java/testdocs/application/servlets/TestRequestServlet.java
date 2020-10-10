package testdocs.application.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testdocs.application.controllers.TestRequestController;
import testdocs.application.dto.CreateTestRequestDTO;
import testdocs.application.services.GsonService;
import testdocs.application.shared.ServletResponse;
import testdocs.domain.entities.TestCase;
import testdocs.domain.entities.TestSuite;

@WebServlet("/api/test_requests/*")
public class TestRequestServlet extends BaseHttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TestRequestController testRequestController;
	
	public TestRequestServlet() throws ClassNotFoundException, SQLException {
		super(new GsonService());
		testRequestController = new TestRequestController();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI();
		ServletResponse servletResponse = null;
		if(pathMatches("/testdocs/api/test_requests", path)) {
			servletResponse = testRequestController.createTestRequest(readBody(request.getReader(), CreateTestRequestDTO.class));
		}
		if(pathMatches("/testdocs/api/test_requests/test_suites", path)) {
			servletResponse = testRequestController.createTestSuite(readBody(request.getReader(), TestSuite.class));
		}
		if(pathMatches("/testdocs/api/test_requests/test_suites/test_cases", path)) {
			servletResponse = testRequestController.createTestCase(readBody(request.getReader(), TestCase.class));
		}
		finishRequest(servletResponse, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI();
		ServletResponse servletResponse = null;
		if(pathMatches("/testdocs/api/test_requests/projects/(.)*", path)) {
			servletResponse = testRequestController.findAllTestRequestByProjectId(takeLongParamByIndex(path, 5));
		}
		finishRequest(servletResponse, response);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI();
		ServletResponse servletResponse = null;
		if(pathMatches("/testdocs/api/test_requests/(.)*", path)) {
			servletResponse = testRequestController.deleteTestRequest(takeLongParamByIndex(path, 4));
		}
		finishRequest(servletResponse, response);
	}
}

package testdocs.application.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testdocs.application.controllers.ProjectController;
import testdocs.application.dto.CreateMemberDTO;
import testdocs.application.services.GsonService;
import testdocs.application.shared.ServletResponse;
import testdocs.domain.entities.Project;

@WebServlet("/api/projects/*")
public class ProjectServlet extends BaseHttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProjectController projectController;

	public ProjectServlet() throws ClassNotFoundException, SQLException {
		super(new GsonService());
		this.projectController = new ProjectController();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletResponse servletResponse = null;
		String path = request.getRequestURI();
		if (pathMatches("/testdocs/api/projects(/)?", path)) {
			servletResponse = projectController.createProject(this.readBody(request.getReader(), Project.class),
					getLoggedUserId(request));
		}
		if (pathMatches("/testdocs/api/projects/(.)*/members", path)) {
			servletResponse = projectController.addMember(takeLongParamByIndex(path, 4),
					this.readBody(request.getReader(), CreateMemberDTO.class));
		}

		super.finishRequest(servletResponse, response);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletResponse servletResponse = null;
		String path = request.getRequestURI();
		if (pathMatches("/testdocs/api/projects/(.)*(/)?", path)) {
			servletResponse = projectController.updateProject(this.readBody(request.getReader(), Project.class),
					takeLongParamByIndex(path, 4));
		}

		super.finishRequest(servletResponse, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletResponse servletResponse = null;
		String path = request.getRequestURI();
		if (pathMatches("/testdocs/api/projects/my_projects(/)?", path)) {
			servletResponse = projectController.findAllProjectByLoggedUser(getLoggedUserId(request));
		} else if (pathMatches("/testdocs/api/projects/(.)*/members", path)) {
			servletResponse = projectController.findAllMembers(takeLongParamByIndex(path, 4));
		} else if (pathMatches("/testdocs/api/projects/(\\d)*(/)?", path)) {
			servletResponse = projectController.findById(takeLongParamByIndex(path, 4));
		}
		super.finishRequest(servletResponse, response);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletResponse servletResponse = null;
		String path = request.getRequestURI();
		if (pathMatches("/testdocs/api/projects/(.)*/members/(.)*", path)) {
			servletResponse = projectController.deleteMember(takeLongParamByIndex(path, 4), takeLongParamByIndex(path, 6));
		}
		super.finishRequest(servletResponse, response);
	}
}

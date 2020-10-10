package testdocs.application.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testdocs.application.controllers.AuthController;
import testdocs.application.dto.AuthDTO;
import testdocs.application.services.GsonService;
import testdocs.application.shared.ServletResponse;

@WebServlet("/api/auth/*")
public class AuthServlet extends BaseHttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AuthController authController;
	
	public AuthServlet() throws ClassNotFoundException, SQLException {
		super(new GsonService());
		this.authController = new AuthController();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletResponse servletResponse = null;
		if(pathMatches("/testdocs/api/auth(/)?", request.getRequestURI())) {
			servletResponse = authController.auth(super.readBody(request.getReader(), AuthDTO.class));
		}
		super.finishRequest(servletResponse, response);
	}
	
}

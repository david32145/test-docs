package testdocs.application.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testdocs.application.controllers.UserController;
import testdocs.application.services.GsonService;
import testdocs.application.shared.ServletResponse;
import testdocs.domain.entities.User;

@WebServlet("/api/users/*")
public class UserServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserController userController;
	public UserServlet() throws ClassNotFoundException, SQLException {
		super(new GsonService());
		this.userController = new UserController();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletResponse servletResponse = null;
		if(pathMatches("/testdocs/api/users(/)?", request.getRequestURI())) {
			servletResponse = userController.createUser(super.readBody(request.getReader(), User.class));
		}
		super.finishRequest(servletResponse, response);
		return;
	}
	
	

}

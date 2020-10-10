package testdocs.application.servlets;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testdocs.application.services.GsonService;
import testdocs.application.shared.ServletResponse;

abstract public class BaseHttpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected GsonService gsonService;
	
	public BaseHttpServlet(GsonService gsonService) {
		this.gsonService = gsonService;
	}
	
	protected <T> T readBody(BufferedReader reader, Class<T> bodyClass) {
		return this.gsonService.fromJson(reader, bodyClass);
	}
	
	protected boolean pathMatches(String matches, String path) {
		return path.matches(matches);
	}
	
	protected void finishRequest(ServletResponse servletResponse, HttpServletResponse response) throws IOException {
		servletResponse = servletResponse == null ? ServletResponse.notFound() : servletResponse;
		if(servletResponse.getResponseBody() != null) {
			response.getWriter().print(gsonService.toJson(servletResponse.getResponseBody()));			
		}
		response.setStatus(servletResponse.getStatus());
		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().flush();
	}
	
	protected long getLoggedUserId(HttpServletRequest request) {
		return Long.parseLong(request.getHeader("authorization"));
	}
	
	protected long takeLongParamByIndex(String path, int index) {
		String split[] = path.split("/");
		return Long.parseLong(split[index]);
	}
}

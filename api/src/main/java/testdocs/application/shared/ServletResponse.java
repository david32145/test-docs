package testdocs.application.shared;

import javax.servlet.http.HttpServletResponse;

public class ServletResponse {
	private Object responseBody;
	private int status;
	
	public ServletResponse(Object responseBody, int status) {
		this.responseBody = responseBody;
		this.status = status;
	}
	
	public Object getResponseBody() {
		return responseBody;
	}
	
	public int getStatus() {
		return status;
	}

	public static ServletResponse error(Object responseBody) {
		return new ServletResponse(responseBody, HttpServletResponse.SC_BAD_REQUEST);
	}
	
	public static ServletResponse created(Object responseBody) {
		return new ServletResponse(responseBody, HttpServletResponse.SC_CREATED);
	}
	
	public static ServletResponse ok(Object responseBody) {
		return new ServletResponse(responseBody, HttpServletResponse.SC_OK);
	}
	
	public static ServletResponse notFound() {
		return new ServletResponse(null, HttpServletResponse.SC_NOT_FOUND);
	}
}

package testdocs.application.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class AuthFilter implements Filter {

	private ServletContext context;

	private boolean isPublicPath(String path) {
		return path.matches("/testdocs/api/auth(/)?") || path.matches("/testdocs/api/users(/)?");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		if (isPublicPath(httpServletRequest.getRequestURI())) {
			chain.doFilter(request, response);
			return;
		}

		String authorization = httpServletRequest.getHeader("authorization");
		if(authorization == null || authorization.isBlank()) {
			httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			httpServletResponse.getWriter().flush();
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}

package br.ufc.crateus.testdocs.application.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String path = httpServletRequest.getRequestURI();
		if(path.equals("/api/auth") || path.equals("/api/users")) {
			if(httpServletRequest.getMethod().equalsIgnoreCase("POST")) {
				chain.doFilter(request, response);	
				return;
			}
		}
		String authorization = httpServletRequest.getHeader("Authorization");
		if(authorization != null && !authorization.isBlank()) {
			chain.doFilter(request, response);
			return;
		}
		httpServletResponse.setStatus(401);
	}

}

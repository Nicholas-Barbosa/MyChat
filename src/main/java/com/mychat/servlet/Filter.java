package com.mychat.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/faces/*")
public class Filter implements javax.servlet.Filter {

	@Inject
	private SessionUser user;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (user.getUser() == null) {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String loginUrl = String.format("%s/%s", httpRequest.getContextPath(), "login.xhtml");
			httpResponse.sendRedirect(loginUrl);
			return;
		}
		chain.doFilter(request, response);

	}

}

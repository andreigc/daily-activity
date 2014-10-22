package com.andreicg.solution.dailyagenda.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain chain) throws IOException, ServletException {
	if (request instanceof HttpServletRequest) {
	    HttpServletRequest httpReq = (HttpServletRequest) request;
	    Cookie[] cookies = httpReq.getCookies();
	    if (cookies != null) {
		for (Cookie cookie : cookies) {
		    if (cookie.getName().equals("sessionId")) {
			if (AuthenticationUtil.isAuthenticated(cookie
				.getValue())) {
			    chain.doFilter(request, response);
			    return;
			}
		    }
		}
	    }
	    if (response instanceof HttpServletResponse) {
		HttpServletResponse res = (HttpServletResponse) response;
		res.setStatus(HttpServletResponse.SC_FORBIDDEN);
	    }
	}
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
}

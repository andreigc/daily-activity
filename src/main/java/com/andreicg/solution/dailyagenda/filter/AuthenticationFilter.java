package com.andreicg.solution.dailyagenda.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.andreicg.solution.dailyagenda.util.AuthenticationUtil;



public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain chain) throws IOException, ServletException {
	if (request instanceof HttpServletRequest) {
	    HttpServletRequest httpReq = (HttpServletRequest) request;
	    String sessionId = httpReq.getHeader("sessionId");
	    if (!StringUtils.isBlank(sessionId) && AuthenticationUtil.isAuthenticated(sessionId)) {
		chain.doFilter(new AddParamsToHttpRequestHeader(httpReq), response);
	    } else  if (response instanceof HttpServletResponse) {
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

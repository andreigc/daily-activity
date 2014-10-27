package com.andreicg.solution.dailyagenda.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class AddParamsToHttpRequestHeader extends HttpServletRequestWrapper {
    public AddParamsToHttpRequestHeader(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
	
	if(name.equals("userId")){
	    int userId = AuthenticationUtil.getUserId(this.getHeader("sessionId"));
	    List<String> list = new ArrayList<String>();
	    list.add(""+userId);
	    return Collections.enumeration(list);
	}
        return super.getHeaders(name);
    }

    @Override
    public Enumeration getHeaderNames() {
        List<String> names = Collections.list(super.getHeaderNames());
        names.add("userId");
        return Collections.enumeration(names);
    }
}
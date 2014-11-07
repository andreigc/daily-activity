package com.andreicg.solution.dailyagenda.restws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.andreicg.solution.dailyagenda.json.Credentials;
import com.andreicg.solution.dailyagenda.model.User;
import com.andreicg.solution.dailyagenda.model.UserDAO;
import com.andreicg.solution.dailyagenda.util.AuthenticationUtil;

@Path("/auth")
public class AuthResource {

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String login(Credentials loginObject){
	User user = UserDAO.checkCredentials(loginObject);
	if(AuthenticationUtil.isAuthenticated(user)){
	    return AuthenticationUtil.getSessionId(user);
	}
	if(user!=null){
	    String sessionId = AuthenticationUtil.doLogin(user);
	    return sessionId;
	}
	return null;
    }
    
    @Path("/logout")
    @POST
    public void logout(@Context HttpHeaders headers){
	List<String> sessionIdHeader = headers.getRequestHeader("sessionId");
	if(sessionIdHeader!=null && sessionIdHeader.size()==1){
	    AuthenticationUtil.doLogout(sessionIdHeader.get(0));
	}
    }
    
}
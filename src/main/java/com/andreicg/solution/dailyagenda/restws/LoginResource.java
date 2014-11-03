package com.andreicg.solution.dailyagenda.restws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.andreicg.solution.dailyagenda.json.Credentials;
import com.andreicg.solution.dailyagenda.model.User;
import com.andreicg.solution.dailyagenda.model.UserDAO;
import com.andreicg.solution.dailyagenda.util.AuthenticationUtil;

@Path("/login")
public class LoginResource {

    
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
    
}

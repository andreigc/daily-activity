package com.andreicg.solution.dailyagenda.restws;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.andreicg.solution.dailyagenda.json.LoginJson;

public class LoginResource {

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void login(LoginJson loginObject){
	
    }
    
}

package com.andreicg.solution.dailyagenda.restws;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.andreicg.solution.dailyagenda.json.UserJson;
import com.andreicg.solution.dailyagenda.model.User;
import com.andreicg.solution.dailyagenda.model.UserDAO;

@Path("/user")
public class UserResource {

    @Path("/register")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(UserJson input) {
	User user = UserJson.userJsonToUser(input);
	UserDAO.addNewUser(user);
    }
    
}

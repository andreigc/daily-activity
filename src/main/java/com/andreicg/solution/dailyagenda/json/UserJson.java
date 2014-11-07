package com.andreicg.solution.dailyagenda.json;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.andreicg.solution.dailyagenda.model.User;

public class UserJson {

    private int ID;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;

    public int getID() {
	return ID;
    }

    public void setID(int ID) {
	this.ID = ID;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getSurname() {
	return surname;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
    
    public static User userJsonToUser(UserJson input) {
   	User user = new User();	
   	user.setEmail(input.getEmail());
   	user.setUsername(input.getUsername());
   	user.setPassword(input.getPassword());
   	user.setName(input.getName());
   	user.setSurname(input.getSurname());
   	return user;
    }


}

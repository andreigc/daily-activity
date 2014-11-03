package com.andreicg.solution.dailyagenda.model;

import org.apache.commons.lang.builder.ToStringBuilder;

public class User {

    private String userName;
    private String password;
    private int ID;
    

    public int getID() {
	return ID;
    }

   
    public void setID(int ID) {
	this.ID = ID;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ID;
	result = prime * result
		+ ((password == null) ? 0 : password.hashCode());
	result = prime * result
		+ ((userName == null) ? 0 : userName.hashCode());
	return result;
    }


    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	User other = (User) obj;
	if (ID != other.ID)
	    return false;
	if (password == null) {
	    if (other.password != null)
		return false;
	} else if (!password.equals(other.password))
	    return false;
	if (userName == null) {
	    if (other.userName != null)
		return false;
	} else if (!userName.equals(other.userName))
	    return false;
	return true;
    }


    @Override
    public String toString(){
	return ToStringBuilder.reflectionToString(this);
    }
    
}

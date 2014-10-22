package com.andreicg.solution.dailyagenda.model;

import org.apache.commons.lang.builder.ToStringBuilder;

public class User {

    private String name;
    private int ID;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getID() {
	return ID;
    }

    public void setID(int ID) {
	this.ID = ID;
    }

    @Override
    public String toString(){
	return ToStringBuilder.reflectionToString(this);
    }
    
}

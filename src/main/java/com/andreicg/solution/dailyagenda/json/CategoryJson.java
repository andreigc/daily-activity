package com.andreicg.solution.dailyagenda.json;

import java.util.ArrayList;
import java.util.List;

public class CategoryJson {
    private int id;
    private String name;
    private String description;
    private int taskNum;
    private List<TaskJson> taskList = new ArrayList<TaskJson>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String categoryName) {
	this.name = categoryName;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String categoryDescription) {
	this.description = categoryDescription;
    }

    public int getTaskNum() {
	return taskNum;
    }

    public void setTaskNum(int taskNum) {
	this.taskNum = taskNum;
    }
    
    public void incrementTaskNum(){
	taskNum++;
    }

    public List<TaskJson> getTaskList() {
	return taskList;
    }

}

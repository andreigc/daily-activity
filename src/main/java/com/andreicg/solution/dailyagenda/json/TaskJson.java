package com.andreicg.solution.dailyagenda.json;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

public class TaskJson {

    private int id;
    private int categoryId;
    private int userId;
    private String description;
    private int priority;
    private int taskType;
    private int completionGrade;
    private String statusComment;

    private int subtaskNum;
    private List<TaskJson> subtasks = new ArrayList<TaskJson>();

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public int getPriority() {
	return priority;
    }

    public void setPriority(int priority) {
	this.priority = priority;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    
    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public int getCompletionGrade() {
        return completionGrade;
    }

    public void setCompletionGrade(int completionGrade) {
        this.completionGrade = completionGrade;
    }

    public String getStatusComment() {
        return statusComment;
    }

    public void setStatusComment(String statusComment) {
        this.statusComment = statusComment;
    }
    
    
    public int getSubtaskNum() {
        return subtaskNum;
    }

    public void setSubtaskNum(int subtaskNum) {
        this.subtaskNum = subtaskNum;
    }

    /**
     * 
     * @return A pre-allocated TaskJson list
     */
    public List<TaskJson> getSubtasks() {
	return subtasks;
    }
    
    @Override
    public String toString(){
	return ToStringBuilder.reflectionToString(this);
    }

}

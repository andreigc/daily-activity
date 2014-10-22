package com.andreicg.solution.dailyagenda.model;

import org.apache.commons.lang.builder.ToStringBuilder;


public class Task {

    public static final int COMPLETED_VALUE = 101;
    
    private int id;
    private int userId;
    private int parentId;
    private int recurrenceId;
    private int categoryId;
    private int taskType;
    private String scheduledAtHours;
    private String description;
    private int priority;
    //completion grade will take a value from an interval of length 100 with an offset of 1 (1-101)
    private int completionGrade;
    private String statusComment;

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

    public int getUserId() {
	return userId;
    }

    public void setUserId(int userId) {
	this.userId = userId;
    }

    public int getParentId() {
	return parentId;
    }

    public void setParentId(int parentId) {
	this.parentId = parentId;
    }

    public int getRecurrenceId() {
	return recurrenceId;
    }

    public void setRecurrenceId(int recurrenceId) {
	this.recurrenceId = recurrenceId;
    }

    public int getTaskType() {
	return taskType;
    }

    public void setTaskType(int taskType) {
	this.taskType = taskType;
    }

    public String getScheduledAtHours() {
	return scheduledAtHours;
    }

    public void setScheduledAtHours(String scheduledAtHours) {
	this.scheduledAtHours = scheduledAtHours;
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

    @Override
    public String toString(){
	return ToStringBuilder.reflectionToString(this);
    }
    
}

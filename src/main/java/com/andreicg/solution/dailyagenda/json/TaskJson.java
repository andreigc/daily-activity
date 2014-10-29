package com.andreicg.solution.dailyagenda.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.andreicg.solution.dailyagenda.model.Category;
import com.andreicg.solution.dailyagenda.model.Task;
import com.andreigc.solution.dailyagenda.enums.TaskType;

public class TaskJson {

    private int id;
    private int categoryId;
    private int userId;
    private int parentId;
    private String description;
    private int priority;
    private int taskType;
    private int completionGrade;
    private String statusComment;
    private Date startDate;

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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    /**
     * 
     * @return A pre-allocated TaskJson list
     */
    public List<TaskJson> getSubtasks() {
	return subtasks;
    }
    
    
    public static Task taskJsonToTask(TaskJson taskJson) {
   	Task task = new Task();
   	task.setId(taskJson.getId());
   	task.setDescription(taskJson.getDescription());
   	task.setPriority(taskJson.getPriority());
   	task.setUserId(taskJson.getUserId());
   	task.setTaskType(taskJson.getTaskType());
   	task.setCompletionGrade(taskJson.getCompletionGrade());
   	task.setStatusComment(taskJson.getStatusComment());
   	task.setCategoryId(taskJson.getCategoryId());
   	task.setParentId(taskJson.getParentId());
   	return task;
    }
    
    public static List<TaskJson> hierarchizeTaskJsonList(List<TaskJson> tasks){
	List<TaskJson> result = new ArrayList<TaskJson>();
	for(TaskJson taskJson: tasks){
	    TaskType type = TaskType.toTaskType(taskJson.getTaskType());
	    if (type == TaskType.STANDALONE) {
		result.add(taskJson);
	    } else if (type == TaskType.CONTAINER) {
		int subtaskNo = 0;
		for (TaskJson subTaskJson : tasks) {
		    if (subTaskJson.getParentId() == taskJson.getId()) {
			subtaskNo++;
			taskJson.getSubtasks().add(subTaskJson);
		    }
		}
		taskJson.setSubtaskNum(subtaskNo);
		result.add(taskJson);
	    }
	}
	return result;
	
    }

    public static List<CategoryJson> categorizeTaskJsonList(List<TaskJson> tasks,
	    List<Category> categories) {
	
	List<CategoryJson> list = Category.categoryListToCategoryJsonList(categories);

	for (TaskJson task : tasks) {
	    for (CategoryJson cat : list) {
		if (cat.getId() == task.getCategoryId()) {
		    cat.getTaskList().add(task);
		    cat.incrementTaskNum();
		    break;
		}
	    }
	}
	return list;
    }
    
    public Date getStartDate() {
   	return startDate;
    }

    public void setStartDate(Date startDate) {
   	this.startDate = startDate;
    }
    
    @Override
    public String toString(){
	return ToStringBuilder.reflectionToString(this);
    }

   

 

}

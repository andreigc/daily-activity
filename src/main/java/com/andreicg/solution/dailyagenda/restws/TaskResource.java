package com.andreicg.solution.dailyagenda.restws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.andreicg.solution.dailyagenda.json.TaskJson;
import com.andreicg.solution.dailyagenda.model.Task;
import com.andreicg.solution.dailyagenda.model.TaskDAO;
import com.andreigc.solution.dailyagenda.enums.CompletionType;
import com.andreigc.solution.dailyagenda.enums.TaskType;

@Path("/tasks")
public class TaskResource {

    private TaskJson taskToTaskJson(Task task) {
	TaskJson taskJson = new TaskJson();
	taskJson.setId(task.getId());
	taskJson.setDescription(task.getDescription());
	taskJson.setPriority(task.getPriority());
	taskJson.setUserId(task.getUserId());
	taskJson.setTaskType(task.getTaskType());
	taskJson.setCompletionGrade(task.getCompletionGrade());
	taskJson.setStatusComment(task.getStatusComment());
	return taskJson;
    }

    private List<TaskJson> taskListToTaskJsonList(List<Task> tasks) {
	List<TaskJson> tasksJson = new ArrayList<TaskJson>();
	for (Task task : tasks) {
	    TaskJson taskJson = taskToTaskJson(task);
	    TaskType type = TaskType.toTaskType(task.getTaskType());
	    if (type == TaskType.STANDALONE) {
		tasksJson.add(taskJson);
	    } else if (type == TaskType.CONTAINER) {
		for (Task subTask : tasks) {
		    if (subTask.getParentId() == task.getId()) {
			TaskJson subTaskJson = taskToTaskJson(subTask);
			taskJson.getSubtasks().add(subTaskJson);
		    }
		}
		tasksJson.add(taskJson);
	    }
	}
	return tasksJson;
    }

    private Task taskJsonToTask(TaskJson taskJson) {
	Task task = new Task();
	task.setId(taskJson.getId());
	task.setDescription(taskJson.getDescription());
	task.setPriority(taskJson.getPriority());
	task.setUserId(taskJson.getUserId());
	task.setTaskType(taskJson.getTaskType());
	task.setCompletionGrade(taskJson.getCompletionGrade());
	task.setStatusComment(taskJson.getStatusComment());
	return task;
    }

    @Path("/getMultiple")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TaskJson> getTasks(@QueryParam("userId") int userId,
	    @QueryParam("categoryId") int categoryId,
	    @QueryParam("completionType") int completionType) {
	List<Task> tasks = TaskDAO.getAllTasksForUser(userId, categoryId,
		CompletionType.getCompletionTypeById(completionType));
	return taskListToTaskJsonList(tasks);
    }
    
    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TaskJson getTask(@QueryParam("taskId") int taskId){
	Task task = TaskDAO.getTask(taskId);
	if(task!=null){
	    return taskToTaskJson(task);
	}
	return null;
	
    }

    @Path("/create")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void addTask(TaskJson input) {
	Task task = taskJsonToTask(input);
	TaskDAO.createTask(task);
    }
    
    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateTask(TaskJson input){
	Task task = taskJsonToTask(input);
	TaskDAO.updateTask(task);
    }

}

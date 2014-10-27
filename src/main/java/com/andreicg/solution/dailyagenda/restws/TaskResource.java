package com.andreicg.solution.dailyagenda.restws;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.andreicg.solution.dailyagenda.json.CategoryJson;
import com.andreicg.solution.dailyagenda.json.Response;
import com.andreicg.solution.dailyagenda.json.TaskJson;
import com.andreicg.solution.dailyagenda.model.Category;
import com.andreicg.solution.dailyagenda.model.CategoryDAO;
import com.andreicg.solution.dailyagenda.model.Task;
import com.andreicg.solution.dailyagenda.model.TaskDAO;
import com.andreigc.solution.dailyagenda.enums.CompletionType;

@Path("/tasks")
public class TaskResource {

    @Path("/get/multiple")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryJson> getTasks(@QueryParam("userId") int userId,
	    @QueryParam("categoryId") int categoryId,
	    @QueryParam("completionType") int completionType) {
	List<Task> tasks = TaskDAO.getAllTasksForUser(userId, categoryId,
		CompletionType.getCompletionTypeById(completionType));
	List<Category> categories = null;
	if (categoryId != 0) {
	    categories = new ArrayList<Category>();
	    CategoryDAO.getCategory(categoryId);
	} else {
	    categories = CategoryDAO.getAllCategories();
	}
	List<TaskJson> tasksJson = Task.taskListToTaskJsonList(tasks);
	List<TaskJson> nestedTasks = TaskJson
		.hierarchizeTaskJsonList(tasksJson);
	return TaskJson.categorizeTaskJsonList(nestedTasks, categories);
    }

    @Path("/get/single")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TaskJson getTask(@QueryParam("taskId") int taskId) {
	Task task = TaskDAO.getTask(taskId);
	if (task != null) {
	    return Task.taskToTaskJson(task);
	}
	return null;

    }

    @Path("/create")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTask(@Context HttpHeaders headers,TaskJson input) {
	Task task = TaskJson.taskJsonToTask(input);
	List<String> userIdHeader = headers.getRequestHeader("userId");
	if(userIdHeader!=null && userIdHeader.size()==1){
	    task.setUserId(Integer.parseInt(userIdHeader.get(0)));
	    TaskDAO.createTask(task);
	    
	    Response response = new Response();
	    response.setMessage("done");
	    return response;
	}
	return null;
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateTask(TaskJson input) {
	Task task = TaskJson.taskJsonToTask(input);
	TaskDAO.updateTask(task);
    }

    @Path("/delete")
    @DELETE
    public Response deleteTask(@QueryParam("taskId") int taskId) {
	TaskDAO.deleteTask(taskId);
	Response response = new Response();
	response.setMessage("done");
	return response;
    }

}

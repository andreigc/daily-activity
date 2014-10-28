package com.andreicg.solution.dailyagenda.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.andreigc.solution.dailyagenda.enums.CompletionType;
import com.andreigc.solution.dailyagenda.enums.TaskType;

public class TaskDAO {
    
    private static final String COLUMN_ID = "\"ID\"";
    private static final String COLUMN_DESCRIPTION = "\"Description\"";
    private static final String COLUMN_RECURRENCE_ID = "\"RecurrenceID\"";
    private static final String COLUMN_CATEGORY_ID = "\"CategoryID\"";
    private static final String COLUMN_PRIORITY = "\"Priority\"";
    private static final String COLUMN_COMPLETION_GRADE = "\"CompletionGrade\"";
    private static final String COLUMN_STATUS_COMMENT = "\"StatusComment\"";
    
    private static Connection conn;

    
    private static Connection getConnection() throws SQLException {
	if (conn == null || conn.isClosed()) {
	    conn = ConnectionFactory.getInstance().getConnection();
	}
	return conn;
    }
    
    private static List<Task> executeTaskSelectQuery(
	    PreparedStatement prepStatement) throws SQLException {
	ResultSet resultSet = prepStatement.executeQuery();
	List<Task> tasks = new ArrayList<Task>();
	// construct task list from query result
	while (resultSet.next()) {
	    int id = resultSet.getInt("ID");
	    int categoryId = resultSet.getInt("CategoryID");
	    int userId = resultSet.getInt("UserID");
	    int parentId = resultSet.getInt("ParentID");
	    int recurrenceId = resultSet.getInt("RecurrenceID");
	    int taskType = resultSet.getInt("TaskType");
	    String scheduledAtHours = resultSet.getString("ScheduledAtHours");
	    String description = resultSet.getString("Description");
	    int priority = resultSet.getInt("Priority");
	    int completionGrade = resultSet.getInt("CompletionGrade");
	    String statusComment = resultSet.getString("StatusComment");

	    Task task = new Task();
	    task.setId(id);
	    task.setCategoryId(categoryId);
	    task.setParentId(parentId);
	    task.setRecurrenceId(recurrenceId);
	    task.setTaskType(taskType);
	    task.setUserId(userId);
	    task.setScheduledAtHours(scheduledAtHours);
	    task.setDescription(description);
	    task.setPriority(priority);
	    task.setCompletionGrade(completionGrade);
	    task.setStatusComment(statusComment);

	    tasks.add(task);
	}
	return tasks;
    }

    public static Task getTask(int taskId){
	String queryString = "SELECT * from public.\"Task\" WHERE \"ID\" = ?";
	try{
	    Connection connection = getConnection();
	    PreparedStatement prepStatement = connection.prepareStatement(queryString);
	    prepStatement.setInt(1, taskId);
	    List<Task> tasks = executeTaskSelectQuery(prepStatement);
	    if(tasks.size()==1){
		return tasks.get(0);
	    }
	}catch(SQLException e){
	    e.printStackTrace();
	}
	return null;
    }
    
    public static List<Task> getAllTasksForUser(int userId,int categoryId,
	    CompletionType completionType,long startDateMilliseconds) {
	
	String queryString = "SELECT * from public.\"Task\" INNER JOIN public.\"Recurrence\" ON public.\"Task\".\"RecurrenceID\" = public.\"Recurrence\".\"ID\" WHERE \"UserID\" = ?";
	if (completionType == CompletionType.COMPLETED) {
	    queryString += " AND  \"CompletionGrade\"="+Task.COMPLETED_VALUE ;
	} else if (completionType == CompletionType.UNFINISHED) {
	    queryString += " AND ( \"CompletionGrade\"<"+Task.COMPLETED_VALUE +" OR \"TaskType\"="+TaskType.SUBTASK.getOrdinal()+")";
	}
	
	
	
	if(categoryId>0){
	    queryString += " AND \"CategoryID\"= ? ";
	
	}
	
	if(startDateMilliseconds > 0){
	    queryString += " AND \"StartDate\" <= ?";
	}
	
	try {
	    Connection connection = getConnection();
	    PreparedStatement prepStatement = connection
		    .prepareStatement(queryString);
	    int index = 1;
	    prepStatement.setInt(index++, userId);
	    if(categoryId>0){
		prepStatement.setInt(index++, categoryId);
	    }
	    if(startDateMilliseconds > 0){
		prepStatement.setDate(index++, new Date(startDateMilliseconds));
	    }
	    return executeTaskSelectQuery(prepStatement);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return new ArrayList<Task>();
    }

    public static void createTask(Task task) {
	String insertQuery = "INSERT INTO public.\"Task\" ";
	insertQuery += "(\"CategoryID\", \"UserID\", \"ParentID\","
		+ "\"RecurrenceID\", \"TaskType\", \"ScheduledAtHours\", \"Description\""
		+ ", \"Priority\", \"CompletionGrade\", \"StatusComment\") VALUES"
		+ "(?,?,?,?,?,?,?,?,?,?)";
	try {
	    Connection connection = getConnection();
	    PreparedStatement prepStatement = connection
		    .prepareStatement(insertQuery);
	    int categoryID = task.getCategoryId();
	    if (categoryID == 0) {
		prepStatement.setNull(1, java.sql.Types.INTEGER);
	    } else {
		prepStatement.setInt(1, task.getCategoryId());
	    }
	    prepStatement.setInt(2, task.getUserId());
	    if (task.getParentId() == 0) {
		prepStatement.setNull(3, java.sql.Types.INTEGER);
	    } else {
		prepStatement.setInt(3, task.getParentId());
	    }
	    if (task.getRecurrenceId() == 0) {
		prepStatement.setNull(4, java.sql.Types.INTEGER);
	    } else {
		prepStatement.setInt(4, task.getRecurrenceId());
	    }

	    prepStatement.setInt(5, task.getTaskType());
	    prepStatement.setString(6, task.getScheduledAtHours());
	    prepStatement.setString(7, task.getDescription());
	    prepStatement.setInt(8, task.getPriority());
	    prepStatement.setInt(9, task.getCompletionGrade());
	    prepStatement.setString(10, task.getStatusComment());
	    prepStatement.executeUpdate();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    
    /**
     * Updates only the changed task fields in the database
     * At the moment no update support available for:
     *  - changing the task type
     *  - changing the parent of a task
     * @param task
     */
    public static void updateTask(Task task) {
	StringBuilder builder = new StringBuilder("UPDATE public.\"Task\" SET ");
	Task dbTask = getTask(task.getId());
	
	List<String> queryParts = new ArrayList<String>();
	int categoryId = task.getCategoryId();
	if(categoryId>0 && categoryId!=dbTask.getCategoryId()){
	    StringBuilder queryPart = new StringBuilder(COLUMN_CATEGORY_ID);
	    queryPart.append("=");
	    queryPart.append(categoryId);
	    queryParts.add(queryPart.toString());
	}
	
	int recurrenceId = task.getRecurrenceId();
	if(recurrenceId>0 && recurrenceId!=dbTask.getRecurrenceId()){
	    StringBuilder queryPart = new StringBuilder(COLUMN_RECURRENCE_ID);
	    queryPart.append("=");
	    queryPart.append(recurrenceId);
	    queryParts.add(queryPart.toString());
	}
	
	String description = task.getDescription();
	if(!StringUtils.isEmpty(task.getDescription()) && !description.equals(dbTask.getDescription())){
	    StringBuilder queryPart = new StringBuilder(COLUMN_DESCRIPTION);
	    queryPart.append("=");
	    queryPart.append("'");
	    queryPart.append(description);
	    queryPart.append("'");
	    queryParts.add(queryPart.toString());
	}
	
	int priority = task.getPriority();
	if(priority>0 && priority!=dbTask.getPriority()){
	    StringBuilder queryPart = new StringBuilder(COLUMN_PRIORITY);
	    queryPart.append("=");
	    queryPart.append(priority);
	    queryParts.add(queryPart.toString());
	}
	
	int completionGrade = task.getCompletionGrade();
	if(completionGrade>0 && completionGrade!=task.getCompletionGrade()){
	    StringBuilder queryPart = new StringBuilder(COLUMN_COMPLETION_GRADE);
	    queryPart.append("=");
	    queryPart.append(completionGrade);
	    queryParts.add(queryPart.toString());
	}
	
	String statusComment = task.getStatusComment();
	if(!StringUtils.isEmpty(task.getStatusComment()) && !statusComment.equals(dbTask.getStatusComment())){
	    StringBuilder queryPart = new StringBuilder(COLUMN_STATUS_COMMENT);
	    queryPart.append("=");
	    queryPart.append("'");
	    queryPart.append(statusComment);
	    queryPart.append("'");
	    queryParts.add(queryPart.toString());
	}
	
	//stop executing the method if there is nothing to update
	if(queryParts.size()==0){
	    return;
	}
	
	String updatePart = StringUtils.join(queryParts.iterator(),",");
	builder.append(updatePart);
	
	builder.append(" WHERE ");
	builder.append(COLUMN_ID);
	builder.append("=");
	builder.append(task.getId());
	
	try {
	    Connection connection = getConnection();
	    PreparedStatement prepStatement = connection.prepareStatement(builder.toString());
	    prepStatement.executeUpdate();
	}catch(SQLException e){
	    e.printStackTrace();
	}
	
    }

    public static void deleteTask(int taskId) {
	String deleteQuery = "DELETE FROM public.\"Task\" WHERE \"ID\" = ?";
	try {
	    Connection connection = getConnection();
	    PreparedStatement prepStatement = connection
		    .prepareStatement(deleteQuery);
	    prepStatement.setInt(1,taskId);
	    prepStatement.executeUpdate();
	}catch(SQLException e){
	    e.printStackTrace();
	}
    }
}

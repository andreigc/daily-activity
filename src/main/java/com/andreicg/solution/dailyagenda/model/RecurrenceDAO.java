package com.andreicg.solution.dailyagenda.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class RecurrenceDAO {

    private static Connection conn;

    private static Connection getConnection() throws SQLException {
	if (conn == null || conn.isClosed()) {
	    conn = ConnectionFactory.getInstance().getConnection();
	}
	return conn;
    }
    
    
    private static Recurrence executeCategorySelectQuery(
	    PreparedStatement prepStatement) throws SQLException {
	ResultSet resultSet = prepStatement.executeQuery();
	while (resultSet.next()) {
	    
	    int id = resultSet.getInt("ID");
	    int recurrenceType = resultSet.getInt("RecurrenceType");
	    int recurrenceDayOfTheWeek = resultSet.getInt("RecurrenceDayOfTheWeek");
	    Date startDate = resultSet.getDate("StartDate");

	    Recurrence recurrence = new Recurrence();
	    recurrence.setId(id);
	    recurrence.setRecurrenceType(recurrenceType);
	    recurrence.setRecurrenceDayOfTheWeek(recurrenceDayOfTheWeek);
	    recurrence.setStartDate(new java.util.Date(startDate.getTime()));
	    return recurrence;
	}
	return null;
    }
    
    public static Recurrence getRecurrence(int recurrenceId){
	String queryString = "SELECT * from public.\"Recurrence\" WHERE \"ID\" = ?";
	try{
	    Connection connection = getConnection();
	    PreparedStatement prepStatement = connection.prepareStatement(queryString);
	    prepStatement.setInt(1, recurrenceId);
	    return executeCategorySelectQuery(prepStatement);
	}catch(SQLException e){
	    e.printStackTrace();
	}
	return null;
    }
    
    
}

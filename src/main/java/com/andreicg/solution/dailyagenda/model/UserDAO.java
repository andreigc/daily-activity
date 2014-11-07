package com.andreicg.solution.dailyagenda.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.andreicg.solution.dailyagenda.json.Credentials;

public class UserDAO {

    private static Connection conn;
    
    private static Connection getConnection() throws SQLException {
	if (conn == null || conn.isClosed()) {
	    conn = ConnectionFactory.getInstance().getConnection();
	}
	return conn;
    }
    
    
    public static void addNewUser(User user){
	String insertQuery = "INSERT INTO public.\"User\" ";
	insertQuery += "(\"Username\", \"Password\", \"Name\",\"Surname\", \"Email\") VALUES(?,?,?,?,?)";
	
	try {
	    Connection connection = getConnection();
	    PreparedStatement prepStatement = connection.prepareStatement(insertQuery);
	    
	    prepStatement.setString(1, user.getUsername());
	    prepStatement.setString(2, user.getPassword());
	    prepStatement.setString(3, user.getName());
	    prepStatement.setString(4, user.getSurname());
	    prepStatement.setString(5, user.getEmail());
	    prepStatement.executeUpdate();
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    public static User checkCredentials(Credentials credentials){
	String queryString = "SELECT * from public.\"User\" WHERE \"Username\" = ?";
	try {
	    Connection connection = getConnection();
	    PreparedStatement prepStatement = connection.prepareStatement(queryString);
	    prepStatement.setString(1, credentials.getUsername());

	    ResultSet rs = prepStatement.executeQuery();
	    if(rs.next()){
		String encodedPassword = rs.getString("Password").trim();
		if(encodedPassword.equals(credentials.getEncodedPassword())){
		    User user = new User();
		   // usr.setPassword(encodedPassword);
		    user.setUsername(credentials.getUsername());
		    user.setID(rs.getInt("ID"));
		    return user;
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
	
    }
    
}

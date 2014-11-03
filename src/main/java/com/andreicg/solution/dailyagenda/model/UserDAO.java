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
    
    public static User checkCredentials(Credentials credentials){
	String queryString = "SELECT * from public.\"User\" WHERE \"Username\" = ?";
	try {
	    Connection connection = getConnection();
	    PreparedStatement prepStatement = connection.prepareStatement(queryString);
	    prepStatement.setString(1, credentials.getUserName());

	    ResultSet rs = prepStatement.executeQuery();
	    if(rs.next()){
		String encodedPassword = rs.getString("Password").trim();
		if(encodedPassword.equals(credentials.getEncodedPassword())){
		    User user = new User();
		   // usr.setPassword(encodedPassword);
		    user.setUserName(credentials.getUserName());
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

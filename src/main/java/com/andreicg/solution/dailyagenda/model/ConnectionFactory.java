package com.andreicg.solution.dailyagenda.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private String driverName = "org.postgresql.Driver";
    private String url = "jdbc:postgresql://localhost/dailyagenda";
    private String user = "postgres";
    private String password = "postgres";

    private static ConnectionFactory connectionFactory;

    private ConnectionFactory() {
	try {
	    Class.forName(driverName);
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
    }
    
    public static ConnectionFactory getInstance(){
	if(connectionFactory==null){
	    connectionFactory = new ConnectionFactory();
	}
	return connectionFactory;
    }
    
    public Connection getConnection() throws SQLException{
	 return DriverManager.getConnection(url, user, password);
    }

}

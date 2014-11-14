package com.andreicg.solution.dailyagenda.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

public class ConnectionFactory {
	private String driverName = "org.postgresql.Driver";
	private String localDbUrl = "jdbc:postgresql://localhost/dailyagenda";
	private String localDbUser = "postgres";
	private String localDbPassword = "postgres";

	private static ConnectionFactory connectionFactory;

	private ConnectionFactory() {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static ConnectionFactory getInstance() {
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}

	public Connection getConnection() throws SQLException {

		
		Connection result = null;
		URI dbUri = null;
		String databaseUrl = System.getenv("DATABASE_URL");
		if(!StringUtils.isBlank(databaseUrl)){
			try {
				dbUri = new URI(databaseUrl);
				String username = dbUri.getUserInfo().split(":")[0];
				String password = dbUri.getUserInfo().split(":")[1];
				String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':'
						+ dbUri.getPort() + dbUri.getPath();
				result = DriverManager.getConnection(dbUrl, username, password);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}else{
			result = DriverManager.getConnection(localDbUrl,localDbUser,localDbPassword);
		}
		return result;
	}
}

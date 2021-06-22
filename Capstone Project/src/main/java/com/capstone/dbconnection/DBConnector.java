package com.capstone.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnector {
	private static final String CONNECTION_STRING = "jdbc:hsqldb:file:/easypassdatabase;";
	private static final String CONNECTION_USER = "root";
	private static final String CONNECTION_PASSWORD = "";
	
	public static Connection getConnection() {
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(CONNECTION_STRING, CONNECTION_USER, CONNECTION_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}

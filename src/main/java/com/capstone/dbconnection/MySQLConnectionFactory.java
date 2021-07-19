package com.capstone.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionFactory implements ConnectionFactory {

	private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/easypass?&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	private static final MySQLConnectionFactory instance = new MySQLConnectionFactory();
	
	private MySQLConnectionFactory() {
		
	}
	
	public static ConnectionFactory getInstance() {		
		return instance;
	}
	
	@Override
	public Connection getConnection() {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
		}
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}

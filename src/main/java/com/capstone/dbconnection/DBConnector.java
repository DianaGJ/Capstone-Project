package com.capstone.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnector {
	public static Connection getConnection(DBConnectionInfo connectionInfo) {
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(connectionInfo.getConnectionString(), 
					connectionInfo.getUserName(), connectionInfo.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}

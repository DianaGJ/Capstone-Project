package com.capstone.dbconnection;

public enum DBConnectionInfo {
	HSQL("jdbc:hsqldb:file:data/easypassdatabase;shutdown=true", "easypass", ""), 
	MYSQL("jdbc:mysql://localhost:3306/easypass", "easypass", "easypassword");
	
	private String connectionString;
	private String userName;
	private String password;
	
	private DBConnectionInfo(String connectionString, String userName, String password) {
		this.connectionString = connectionString;
		this.userName = userName;
		this.password = password;
	}
	
	public String getConnectionString() {
		return connectionString;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
}

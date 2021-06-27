package com.capstone.model.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.capstone.model.StoredPassword;

public final class StoredPasswordFactory {
	private static final StoredPasswordFactory instance = new StoredPasswordFactory();
	
	private StoredPasswordFactory() {
		
	}
	
	public static StoredPasswordFactory getInstance() {
		return instance;
	}
	
	public StoredPassword createPassword(ResultSet results) throws SQLException {
		UUID id = UUID.fromString(results.getString("id"));
		
		return new StoredPassword(id, 
				results.getString("name"), 
				results.getString("description"), 
				results.getDate("created"), 
				results.getDate("modified"),
				results.getString("website"),
				results.getString("password"));
	}
}

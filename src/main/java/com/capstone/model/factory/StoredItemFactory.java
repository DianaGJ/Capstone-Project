package com.capstone.model.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.capstone.model.Note;
import com.capstone.model.Password;
import com.capstone.model.Profile;
import com.capstone.model.StoredItem;

public final class StoredItemFactory {
	public StoredItem createItem(ResultSet results) throws SQLException {
		StoredItem item = null;
		String itemType = results.getString("item_type");
		
		switch (itemType) {
		case "NO":
			item = createNote(results);
			break;
		case "PA":
			item = createPassword(results);
			break;
		case "PR":
			item = createProfile(results);
			break;
		}
		
		return item;
	}
	
	private StoredItem createNote(ResultSet results) throws SQLException {
		UUID id = UUID.fromString(results.getString("id"));
		
		return new Note(id, 
				results.getString("name"), 
				results.getString("description"), 
				results.getDate("created"), 
				results.getDate("modified"), 
				results.getString("contents"));
	}
	
	private StoredItem createPassword(ResultSet results) throws SQLException {
		UUID id = UUID.fromString(results.getString("id"));
		
		return new Password(id, 
				results.getString("name"), 
				results.getString("description"), 
				results.getDate("created"), 
				results.getDate("modified"),
				results.getString("website"),
				results.getString("password"));
	}
	
	private StoredItem createProfile(ResultSet results) throws SQLException {
		UUID id = UUID.fromString(results.getString("id"));
		
		return new Profile(id, 
				results.getString("name"), 
				results.getString("description"), 
				results.getDate("created"), 
				results.getDate("modified"),
				results.getString("firstname"),
				results.getString("lastname"),
				results.getString("address"),
				results.getString("city"));
	}
}

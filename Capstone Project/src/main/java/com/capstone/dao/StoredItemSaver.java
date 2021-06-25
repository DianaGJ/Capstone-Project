package com.capstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.capstone.model.StoredItem;
import com.capstone.model.User;

public abstract class StoredItemSaver {
	private static final String INSERT_ITEM_SQL = "INSERT INTO stored_item si (id, name, description, item_type, user_id) VALUES (?, ?, ?, ?, ?);";
	private static final String UPDATE_ITEM_SQL = "UPDATE stored_item SET name = ?, description = ?, modified = NOW WHERE id = ?;";
	
	private final Connection connection;
	
	public StoredItemSaver(Connection connection) {
		this.connection = connection;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public void executeInsert(User user, StoredItem item) throws SQLException {
		if (user == null || item == null) {
			throw new IllegalArgumentException("user or item is null");
		}
		
		PreparedStatement statement = connection.prepareStatement(INSERT_ITEM_SQL);
		
		statement.setString(1, item.getId().toString());
		statement.setString(2, item.getName());
		statement.setString(3, item.getDescription());
		statement.setString(4, item.getItemTypeCode());
		statement.setString(5, user.getId().toString());
		statement.execute();
	}
	
	public void executeUpdate(User user, StoredItem item) throws SQLException {		
		if (user == null || item == null) {
			throw new IllegalArgumentException("user or item is null");
		}
		
		PreparedStatement statement = connection.prepareStatement(UPDATE_ITEM_SQL);
		
		statement.setString(1, item.getName());
		statement.setString(2, item.getDescription());
		statement.setString(3, item.getId().toString());
		statement.execute();
	}
}

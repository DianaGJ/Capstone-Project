package com.capstone.dao.strategy;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.capstone.model.StoredItem;
import com.capstone.model.User;

public abstract class SaveItemPreparer {
	private User user;
	private StoredItem item;
	
	public SaveItemPreparer(User user, StoredItem item) {
		this.user = user;
		this.item = item;
	}
	
	public StoredItem getItem() {
		return item;
	}
	
	public void prepareInsert(PreparedStatement statement) throws SQLException {
		statement.setString(1, item.getId().toString());
		statement.setString(2, item.getName());
		statement.setString(3, item.getDescription());
		statement.setString(4, item.getItemTypeCode());
		statement.setString(5, user.getId().toString());
		statement.setString(6, item.getId().toString());
	}
	
	public void preparedUpdate(PreparedStatement statement) {

	}
	
	public void save(PreparedStatement statement) throws SQLException {
		statement.execute();
	}
}

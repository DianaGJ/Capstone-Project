package com.capstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.capstone.model.Password;
import com.capstone.model.StoredItem;
import com.capstone.model.User;

public class PasswordSaver extends StoredItemSaver {
	private static final String INSERT_PASSWORD_SQL = "INSERT INTO password (item_id, website, password) VALUES(?, ?, ?);";

	public PasswordSaver(Connection connection) {
		super(connection);
	}

	@Override
	public void save(User user, StoredItem item) throws SQLException {
		super.save(user, item);
		
		setExecuting(true);
		
		Password password = (Password)item;
		
		PreparedStatement statement = getConnection().prepareStatement(INSERT_PASSWORD_SQL);
		
		statement.setString(1, password.getId().toString());
		statement.setString(2, password.getWebsite());
		statement.setString(3, password.getPassword());
		statement.execute();
		
		setExecuting(false);
	}
}

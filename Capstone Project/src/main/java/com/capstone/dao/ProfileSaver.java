package com.capstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.capstone.model.Profile;
import com.capstone.model.StoredItem;
import com.capstone.model.User;

public class ProfileSaver extends StoredItemSaver {
	private static final String INSERT_PROFILE_SQL = "INSERT INTO profile (item_id, firstname, lastname, address, city) VALUES (?, ?, ?, ?, ?);";

	public ProfileSaver(Connection connection) {
		super(connection);
	}
	
	@Override
	public void save(User user, StoredItem item) throws SQLException {
		super.save(user, item);
		
		setExecuting(true);
		
		Profile profile = (Profile)item;
		
		PreparedStatement statement = getConnection().prepareStatement(INSERT_PROFILE_SQL);
		
		statement.setString(1, profile.getId().toString());
		statement.setString(2, profile.getFirstName());
		statement.setString(3, profile.getLastName());
		statement.setString(4, profile.getAddress());
		statement.setString(5, profile.getCity());
		statement.execute();
		
		setExecuting(false);
	}
}

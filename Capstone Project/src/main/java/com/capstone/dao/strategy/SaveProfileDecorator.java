package com.capstone.dao.strategy;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.capstone.model.Profile;
import com.capstone.model.User;

public class SaveProfileDecorator extends SaveItemPreparer {

	public SaveProfileDecorator(User user, Profile item) {
		super(user, item);
	}

	@Override
	public void prepareInsert(PreparedStatement statement) throws SQLException {
		super.prepareInsert(statement);
		
		Profile profile = (Profile)getItem();
		
		statement.setString(7, profile.getFirstName());
		statement.setString(8, profile.getLastName());
		statement.setString(9, profile.getAddress());
		statement.setString(10, profile.getCity());
	}
}

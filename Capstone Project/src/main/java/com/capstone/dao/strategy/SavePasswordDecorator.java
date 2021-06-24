package com.capstone.dao.strategy;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.capstone.model.Password;
import com.capstone.model.User;

public class SavePasswordDecorator extends SaveItemPreparer {
	public SavePasswordDecorator(User user, Password item) {
		super(user, item);
	}
	
	@Override
	public void prepareInsert(PreparedStatement statement) throws SQLException {
		super.prepareInsert(statement);
		
		Password password = (Password)getItem();
		
		statement.setString(7, password.getWebsite());
		statement.setString(8, password.getPassword());
	}
}

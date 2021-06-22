package com.capstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.capstone.dbconnection.DBConnector;
import com.capstone.model.User;
import com.capstone.model.UserBuilder;

public class ApplicationDao implements DaoService {
	private static final String INSERT_SQL = "INSERT INTO USER (username, password, email) VALUES (?, ?, ?);";
	private static final String SELECT_ALL_SQL = "SELECT * FROM user;";
	
	@Override
	public boolean createUser(User user) {
		int insertedRows = 0;
		
		try (
			Connection connection = DBConnector.getConnection();
			PreparedStatement statement = connection.prepareStatement(INSERT_SQL);
		) {
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			
			insertedRows = statement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return insertedRows > 0;
	}

	@Override
	public User getUser(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();
		
		try (
			Connection connection = DBConnector.getConnection();
			Statement statement = connection.createStatement();
		) {
			ResultSet results = statement.executeQuery(SELECT_ALL_SQL);
			
			UserBuilder userBuilder = new UserBuilder();
			
			while (results.next()) {
				long id = results.getLong("id");
				String username = results.getString("username");
				String password = results.getString("password");
				String email = results.getString("email");
				
				User user = userBuilder
						.setId(id)
						.setUsername(username)
						.setPassword(password)
						.setEmail(email)
						.build();
				
				users.add(user);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		
		return false;
	}
	
}

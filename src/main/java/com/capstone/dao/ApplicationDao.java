package com.capstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.capstone.dbconnection.DBConnectionInfo;
import com.capstone.dbconnection.DBConnector;
import com.capstone.model.StoredPassword;
import com.capstone.model.User;
import com.capstone.model.builder.UserBuilder;
import com.capstone.model.factory.StoredPasswordFactory;

public class ApplicationDao implements DaoService {
	private static final String INSERT_USER_SQL = "INSERT INTO USER (id, username, password, email) VALUES (?, ?, ?, ?);";
	
	private static final String SELECT_ALL_USERS_SQL = "SELECT * FROM user u;";
	private static final String SELECT_ONE_USER_SQL = "SELECT * FROM user u WHERE id = ?;";
	
	private static final String DELETE_USER_SQL = "DELETE FROM user WHERE id = ?;";
	private static final String UPDATE_USER_SQL = "UPDATE user SET username = ?, password = ?, email = ? WHERE id = ?;";
	
	private static final String SELECT_PASSWORDS_SQL = "SELECT * FROM password p WHERE user_id = ?;";
	private static final String INSERT_PASSWORD_SQL = "INSERT INTO password (id, name, description, website, password, user_id) "
			+ "VALUES (?, ?, ?, ?, ?, ?);";
	private static final String DELETE_PASSWORD_SQL = "DELETE FROM password WHERE user_id = ?;";

	private final DBConnectionInfo connectionInfo;
	
	public ApplicationDao() {
		this(DBConnectionInfo.HSQL);
	}
	
	public ApplicationDao(DBConnectionInfo connectionInfo) {
		this.connectionInfo = connectionInfo;
	}
	
	@Override
	public boolean createUser(User user) {
		int insertedRows = 0;
		
		try (
			Connection connection = DBConnector.getConnection(connectionInfo);
			PreparedStatement userStatement = connection.prepareStatement(INSERT_USER_SQL);
			PreparedStatement passwordStatement = connection.prepareStatement(INSERT_PASSWORD_SQL);
		) {
			userStatement.setString(1, user.getId().toString());
			userStatement.setString(2, user.getUsername());
			userStatement.setString(3, user.getPassword());
			userStatement.setString(4, user.getEmail());
			
			insertedRows = userStatement.executeUpdate();
			
			for (StoredPassword item : user.getItems()) {				
				passwordStatement.clearParameters();
				
				passwordStatement.setString(1, item.getId().toString());
				passwordStatement.setString(2, item.getName());
				passwordStatement.setString(3, item.getDescription());
				passwordStatement.setString(4, item.getWebsite());
				passwordStatement.setString(5, item.getPassword());
				passwordStatement.setString(6, user.getId().toString());
				
				passwordStatement.execute();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return insertedRows > 0;
	}

	@Override
	public User getUser(UUID id) {
		User user = null;
		
		try (
			Connection connection = DBConnector.getConnection(connectionInfo);
			PreparedStatement statement = connection.prepareStatement(SELECT_ONE_USER_SQL);
		) {
			statement.setString(1, id.toString());
			
			ResultSet results = statement.executeQuery();
			
			while (results.next()) {				
				user = buildUser(connection, results);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public List<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();
		
		try (
			Connection connection = DBConnector.getConnection(connectionInfo);
			Statement statement = connection.createStatement();
		) {
			ResultSet results = statement.executeQuery(SELECT_ALL_USERS_SQL);
			
			while (results.next()) {			
				User user = buildUser(connection, results);
				users.add(user);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	private User buildUser(Connection connection, ResultSet results) throws SQLException {		
		UUID id = UUID.fromString(results.getString("id"));		
		List<StoredPassword> items = getItems(connection, id.toString());
		
		User user = UserBuilder.getInstance()
			.setId(id)
			.setUsername(results.getString("username"))
			.setPassword(results.getString("password"))
			.setEmail(results.getString("email"))
			.withItems(items)
			.build();
		
		return user;
	}
	
	private List<StoredPassword> getItems(Connection connection, String userId) throws SQLException {
		List<StoredPassword> items = new ArrayList<StoredPassword>();
		StoredPassword item = null;
		
		PreparedStatement itemsStatement = connection.prepareStatement(SELECT_PASSWORDS_SQL);
		itemsStatement.setString(1, userId);
		
		ResultSet results = itemsStatement.executeQuery();
		StoredPasswordFactory storedPasswordFactory = StoredPasswordFactory.getInstance();
		
		while (results.next()) {
			item = storedPasswordFactory.createPassword(results);
			items.add(item);
		}
		
		return items;
	}

	@Override
	public boolean updateUser(User user) {
		boolean success = false;
		
		try (
			Connection connection = DBConnector.getConnection(connectionInfo);
			PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL);
			PreparedStatement passwordStatement = connection.prepareStatement(INSERT_PASSWORD_SQL);
		) {
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getId().toString());
			
			int rowCount = statement.executeUpdate();
			success = rowCount > 0;
			
			PreparedStatement deleteItems = connection.prepareStatement(DELETE_PASSWORD_SQL);
			deleteItems.setString(1, user.getId().toString());
			deleteItems.execute();
			
			for (StoredPassword item : user.getItems()) {
				passwordStatement.clearParameters();
				
				passwordStatement.setString(1, item.getId().toString());
				passwordStatement.setString(2, item.getName());
				passwordStatement.setString(3, item.getDescription());
				passwordStatement.setString(4, item.getWebsite());
				passwordStatement.setString(5, item.getPassword());
				passwordStatement.setString(6, user.getId().toString());
				
				passwordStatement.execute();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	@Override
	public boolean deleteUser(User user) {
		boolean success = false;
		
		try (
				Connection connection = DBConnector.getConnection(connectionInfo);
				PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL);
		) {
			statement.setString(1, user.getId().toString());
			
			int rowCount = statement.executeUpdate();
			success = rowCount > 0;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
}

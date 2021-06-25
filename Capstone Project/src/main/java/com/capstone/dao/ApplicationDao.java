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
import com.capstone.model.StoredItem;
import com.capstone.model.User;
import com.capstone.model.builder.UserBuilder;
import com.capstone.model.factory.StoredItemFactory;

public class ApplicationDao implements DaoService {
	private static final String INSERT_USER_SQL = "INSERT INTO USER (id, username, password, email) VALUES (?, ?, ?, ?);";
	
	private static final String SELECT_ALL_USERS_SQL = "SELECT * FROM user u;";
	private static final String SELECT_STORED_ITEMS_SQL = "SELECT * FROM stored_item si "
			+ "LEFT JOIN note n ON n.item_id = si.id "
			+ "LEFT JOIN password p ON p.item_id = si.id "
			+ "LEFT JOIN profile pr ON pr.item_id = si.id "
			+ "WHERE si.user_id = ?;";
	
	private static final String SELECT_ONE_USER_SQL = "SELECT * FROM user u WHERE u.id = ?;";
	
	private static final String DELETE_USER_SQL = "DELETE FROM user WHERE id = ?;";
	private static final String UPDATE_USER_SQL = "UPDATE user SET username = ?, password = ?, email = ? WHERE id = ?;";

	private final DBConnectionInfo connectionInfo;
	
	private UserBuilder userBuilder;
	private StoredItemFactory storedItemFactory;
	
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
			PreparedStatement statement = connection.prepareStatement(INSERT_USER_SQL);
		) {
			statement.setString(1, user.getId().toString());
			statement.setString(2, user.getUsername());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getEmail());
			
			insertedRows = statement.executeUpdate();
			
			for (StoredItem item : user.getItems()) {				
				StoredItemSaver saver = getStoredItemSaver(connection, item);
				saver.executeInsert(user, item);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return insertedRows > 0;
	}
	
	private StoredItemSaver getStoredItemSaver(Connection connection, StoredItem item) {
		switch (item.getItemTypeCode()) {
		case "NO":
			return new NoteSaver(connection);
		case "PA":
			return new PasswordSaver(connection);
		case "PR":
			return new ProfileSaver(connection);
		default:
			return null;
		}
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
		if (userBuilder == null) {
			userBuilder = new UserBuilder();
		}
		
		UUID id = UUID.fromString(results.getString("id"));		
		List<StoredItem> items = getItems(connection, id.toString());
		
		User user = userBuilder
			.setId(id)
			.setUsername(results.getString("username"))
			.setPassword(results.getString("password"))
			.setEmail(results.getString("email"))
			.withItems(items)
			.build();
		
		return user;
	}
	
	private List<StoredItem> getItems(Connection connection, String userId) throws SQLException {
		List<StoredItem> items = new ArrayList<StoredItem>();
		StoredItem item = null;
		
		PreparedStatement itemsStatement = connection.prepareStatement(SELECT_STORED_ITEMS_SQL);
		itemsStatement.setString(1, userId);
		
		ResultSet results = itemsStatement.executeQuery();
		
		if (storedItemFactory == null) {
			storedItemFactory = new StoredItemFactory();
		}
		
		while (results.next()) {
			item = storedItemFactory.createItem(results);
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
		) {
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getId().toString());
			
			int rowCount = statement.executeUpdate();
			success = rowCount > 0;
			
			for (StoredItem item : user.getItems()) {
				StoredItemSaver itemSaver = getStoredItemSaver(connection, item);

				if (item.getCreated() == null) {
					itemSaver.executeInsert(user, item);
				}
				else {
					itemSaver.executeUpdate(user, item);
				}
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

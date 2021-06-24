package com.capstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.capstone.dao.strategy.SaveNoteDecorator;
import com.capstone.dao.strategy.SavePasswordDecorator;
import com.capstone.dao.strategy.SaveProfileDecorator;
import com.capstone.dao.strategy.SaveItemPreparer;
import com.capstone.dbconnection.DBConnectionInfo;
import com.capstone.dbconnection.DBConnector;
import com.capstone.model.Note;
import com.capstone.model.Password;
import com.capstone.model.Profile;
import com.capstone.model.StoredItem;
import com.capstone.model.User;
import com.capstone.model.builder.UserBuilder;
import com.capstone.model.factory.StoredItemFactory;

public class ApplicationDao implements DaoService {
	private static final String INSERT_USER_SQL = "INSERT INTO USER (id, username, password, email) VALUES (?, ?, ?, ?);";
	
	private static final String INSERT_ITEM_SQL = "INSERT INTO stored_item si (id, name, description, item_type, user_id) VALUES (?, ?, ?, ?, ?);";
	private static final String INSERT_NOTE_SQL = "INSERT INTO note (item_id, contents) VALUES(?, ?);";
	private static final String INSERT_PASSWORD_SQL = "INSERT INTO password (item_id, website, password) VALUES(?, ?, ?);";
	private static final String INSERT_PROFILE_SQL = "INSERT INTO profile (item_id, firstname, lastname, address, city) VALUES (?, ?, ?, ?, ?);";
	
	private static final String SELECT_ALL_USERS_SQL = "SELECT * FROM user u;";
	private static final String SELECT_STORED_ITEMS_SQL = "SELECT * FROM stored_item si "
			+ "LEFT JOIN note n ON n.item_id = si.id "
			+ "LEFT JOIN password p ON p.item_id = si.id "
			+ "LEFT JOIN profile pr ON pr.item_id = si.id "
			+ "WHERE si.user_id = ?;";
	
	private static final String SELECT_ONE_USER_SQL = "SELECT * FROM user u WHERE u.id = ?;";
	
	private static final String DELETE_USER_SQL = "DELETE FROM user WHERE id = ?;";
	private static final String UPDATE_USER_SQL = "UPDATE user SET username = ?, password = ?, email = ? WHERE id = ?;";
	private static final String UPDATE_ITEM_SQL = "UPDATE stored_item SET name = ?, description = ?, modified = LOCAL_TIMESTAMP WHERE id = ?;";
	
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
				String sql = INSERT_ITEM_SQL;
				
				switch (item.getItemTypeCode()) {
					case "NO":
						sql += INSERT_NOTE_SQL;
						break;
					case "PA":
						sql += INSERT_PASSWORD_SQL;
						break;
					case "PR":
						sql += INSERT_PROFILE_SQL;
						break;
				}
				
				PreparedStatement saveItemStatement = connection.prepareStatement(sql);
				
				SaveItemPreparer saveMethod = saveItemPreparer(user, item);
				saveMethod.prepareInsert(saveItemStatement);
				saveMethod.save(saveItemStatement);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return insertedRows > 0;
	}
	
	private SaveItemPreparer saveItemPreparer(User user, StoredItem item) {
		switch (item.getItemTypeCode()) {
		case "NO":
			return new SaveNoteDecorator(user, (Note)item);
		case "PA":
			return new SavePasswordDecorator(user, (Password)item);
		case "PR":
			return new SaveProfileDecorator(user, (Profile)item);
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
				PreparedStatement saveItemStatement = null;
				
				if (item.getCreated() == null) {
					saveItemStatement = connection.prepareStatement(INSERT_ITEM_SQL);
					saveItemStatement.setString(1, item.getId().toString());
					saveItemStatement.setString(2, item.getName());
					saveItemStatement.setString(3, item.getDescription());
					saveItemStatement.setString(4, item.getItemTypeCode());
					saveItemStatement.setString(5, user.getId().toString());
				}
				else {
					saveItemStatement = connection.prepareStatement(UPDATE_ITEM_SQL);
					saveItemStatement.setString(1, item.getName());
					saveItemStatement.setString(2, item.getDescription());
					saveItemStatement.setString(3, item.getId().toString());
				}
				
				saveItemStatement.execute();	
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

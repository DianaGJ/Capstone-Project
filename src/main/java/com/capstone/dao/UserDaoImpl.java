package com.capstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.capstone.dbconnection.MySQLConnectionFactory;
import com.capstone.model.User;

public class UserDaoImpl implements UserDao {
	private PreparedStatement ps;

	public UserDaoImpl() {

	}

	private static final String UPDATE_USER = "UPDATE user SET username = ?,email = ?, password = ?,is_verified = ?,verification_code_time =? WHERE id = ?";
	private static final String UPDATE_USER_WITH_CODE = "UPDATE user SET username = ?,email = ?, password = ?,is_verified = ?,verification_code =? ,verification_code_time = NOW() WHERE id = ?";
	private static final String INSERT_USER = "INSERT INTO user(username,email,password,is_verified,verification_code,verification_code_time) VALUES (?,?,?,?,?,NOW())";
	private static final String GET_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
	private static final String GET_USER_BY_USERNAME = "SELECT * FROM user WHERE username = ?";
	private static final String GET_USER_BY_EMAIL = "SELECT * FROM user WHERE email = ?;";
	private static final String SELECT_ALL_USERS = "Select * from user";

	public void insertUser(User user) throws SQLException {
		Connection connection = MySQLConnectionFactory.getInstance().getConnection();
		ps = connection.prepareStatement(INSERT_USER);
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getPassword());
		ps.setBoolean(4, user.isVerified());
		ps.setString(5, user.getVerificationCode());
		
		if (ps.execute()) {
			System.out.println("NEW USER ADDED");
		}
		
		ps.close();
		connection.close();
	}

	@Override
	public User getUserByUsername(String username) throws SQLException {
		Connection connection = MySQLConnectionFactory.getInstance().getConnection();
		ps = connection.prepareStatement(GET_USER_BY_USERNAME);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();

		User user = new User();
		
		while (rs.next()) {
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setVerified(rs.getBoolean("is_verified"));
			user.setVerificationCode(rs.getString("verification_code"));
		}
		
		rs.close();
		ps.close();
		connection.close();
		
		return user.getUsername() != null ? user : null;
	}

	@Override
	public User getUserById(int id) throws SQLException {
		Connection connection = MySQLConnectionFactory.getInstance().getConnection();
		ps = connection.prepareStatement(GET_USER_BY_ID);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		User user = new User();
		
		while (rs.next()) {
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setVerified(rs.getBoolean("is_verified"));
			user.setVerificationCode(rs.getString("verification_code"));
		}
		
		rs.close();
		ps.close();
		connection.close();
		
		return user;
	}
	
	@Override
	public User getUserByEmail(String email) throws SQLException {
		Connection connection = MySQLConnectionFactory.getInstance().getConnection();
		ps = connection.prepareStatement(GET_USER_BY_EMAIL);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();

		User user = new User();
		
		while (rs.next()) {
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setVerified(rs.getBoolean("is_verified"));
			user.setVerificationCode(rs.getString("verification_code"));
		}
		
		rs.close();
		ps.close();
		connection.close();
		
		return user;
	}

	@Override
	public void updateUser(User user) throws SQLException {
		Connection connection = MySQLConnectionFactory.getInstance().getConnection();
		
		if (user.getVerificationCode() == null) {
			ps = connection.prepareStatement(UPDATE_USER);
		}
		else {
			ps = connection.prepareStatement(UPDATE_USER_WITH_CODE);
		}
		
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getPassword());
		ps.setBoolean(4, user.isVerified());
		ps.setString(5, user.getVerificationCode());
		ps.setInt(6,  user.getId());
		
		if (ps.executeUpdate() > 0) {
			System.out.println("USER UPDATED");
		} else {
			System.out.println("something went wrong!!");
		}
		
		ps.close();
		connection.close();
	}

	

	@Override
	public void deleteUser(int id) {

	}

	@Override
	public List<User> getAllUsers() throws SQLException {
		Connection connection = MySQLConnectionFactory.getInstance().getConnection();
		ps = connection.prepareStatement(SELECT_ALL_USERS);
		ResultSet rs = ps.executeQuery();

		List<User> users = new ArrayList<User>();
		
		while (rs.next()) {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setVerified(rs.getBoolean("is_verified"));
			user.setVerificationCode(rs.getString("verification_code"));
			users.add(user);
		}
		
		rs.close();
		ps.close();
		connection.close();
		
		return users;
	}

}

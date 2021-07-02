package com.capstone.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.capstone.dbconnection.MysqlConnection;
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
	private static final String SELECT_ALL_USERS = "Select * from user";

	public void insert(User user) throws SQLException {

		ps = MysqlConnection.getConnection().prepareStatement(INSERT_USER);
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getPassword());
		ps.setBoolean(4, user.isVerified());
		ps.setString(5, user.getVerificationCode());
		if (ps.execute()) {
			System.out.println("NEW USER ADDED");
		}
		ps.close();

	}

	@Override
	public User getByUsername(String username) throws SQLException {
		User user = new User();
		ps = MysqlConnection.getConnection().prepareStatement(GET_USER_BY_USERNAME);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setVerified(rs.getBoolean("is_verified"));
			user.setVerificationCode(rs.getString("verification_code"));
		}
		ps.close();
		return user.getUsername() != null ? user : null;
	}

	@Override
	public User getById(int id) throws SQLException {
		User user = new User();
		ps = MysqlConnection.getConnection().prepareStatement(GET_USER_BY_ID);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setVerified(rs.getBoolean("is_verified"));
			user.setVerificationCode(rs.getString("verification_code"));
		}
		ps.close();
		return user;
	}

	@Override
	public void update(User user) throws SQLException {
		if (user.getVerificationCode() == null) {
			ps = MysqlConnection.getConnection().prepareStatement(UPDATE_USER);
		}
			else {
			ps = MysqlConnection.getConnection().prepareStatement(UPDATE_USER_WITH_CODE);
			}
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setBoolean(4, user.isVerified());
			ps.setString(5, user.getVerificationCode());
			ps.setInt(6,  user.getId());
			if (ps.executeUpdate() >0) {
				System.out.println("USER UPDATED");
			}else {
				System.out.println("something went wrong!!");
			}
			ps.close();

			
		}

	

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getAll() throws SQLException {
		List<User> users = new ArrayList<User>();
		
		ps = MysqlConnection.getConnection().prepareStatement(SELECT_ALL_USERS);
		ResultSet rs = ps.executeQuery();
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
		return users;
	}

}

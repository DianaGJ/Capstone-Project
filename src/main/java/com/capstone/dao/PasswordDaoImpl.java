package com.capstone.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.capstone.dbconnection.MysqlConnection;
import com.capstone.model.Password;


public class PasswordDaoImpl implements PasswordDao {
	private PreparedStatement ps;
	private static final String INSERT_PASSWORD = "Insert into password (website,websiteUser,user_id,password) values (?,?,?,?)";
	private static final String GET_ALL_PASSWORDS_OF_USER = "SELECT * from password WHERE user_id = ? ";
	private static final String GET_PASSWORD_BY_ID = "Select * FROM password WHERE id = ?";
	private static final String UPDATE_PASSWORD = "UPDATE password SET website = ?, websiteUser = ?, password = ? WHERE id = ? ";

	@Override
	public void insert(Password password) throws SQLException {
		ps = MysqlConnection.getConnection().prepareStatement(INSERT_PASSWORD);
		ps.setString(1, password.getWebsite());
		ps.setString(2, password.getWebsiteUser());
		ps.setInt(3, password.getUserId());
		ps.setString(4, password.getPassword());
		if (ps.execute()) {
			System.out.println("PASSWORD ADDED ");
		}
		
	}

	@Override
	public Password getById(int id) throws SQLException {
		ps = MysqlConnection.getConnection().prepareStatement(GET_PASSWORD_BY_ID);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Password password = new Password();
		while (rs.next()) {
			
			password.setId(rs.getInt("id"));
			password.setWebsite(rs.getString("website"));
			password.setWebsiteUser(rs.getString("websiteUser"));
			password.setUserId(rs.getInt("user_id"));
			password.setPassword(rs.getString("password"));
			
		}
		return password;
	}

	@Override
	public void update(Password password) throws SQLException {
		
		ps = MysqlConnection.getConnection().prepareStatement(UPDATE_PASSWORD);
		ps.setString(1, password.getWebsite());
		ps.setString(2, password.getWebsiteUser());
		ps.setString(3, password.getPassword());
		ps.setInt(4, password.getId());
		
		if (ps.executeUpdate() >0) {
			System.out.println("PASSWORD UPDATED");
		}else {
			System.out.println("SOMETHING WENT WRONG");
		}
	}

	@Override
	public void delete(int id) throws SQLException {
		ps = MysqlConnection.getConnection().prepareStatement("DELETE  FROM password where id = ?");
		ps.setInt(1, id);
		if (ps.execute()) {
			System.out.println("PASSWORD DELETED!");
		}
		
	}

	@Override
	public List<Password> getAllForUser(int id) throws SQLException {
		List<Password> passwords = new ArrayList<Password>();
		ps = MysqlConnection.getConnection().prepareStatement(GET_ALL_PASSWORDS_OF_USER);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Password password = new Password();
			password.setId(rs.getInt("id"));
			password.setWebsite(rs.getString("website"));
			password.setWebsiteUser(rs.getString("websiteUser"));
			password.setUserId(rs.getInt("user_id"));
			passwords.add(password);
		}
		return passwords;
	}

}

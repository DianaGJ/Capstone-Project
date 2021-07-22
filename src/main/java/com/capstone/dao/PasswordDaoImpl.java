package com.capstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.capstone.dbconnection.ConnectionFactory;
import com.capstone.model.Password;


public class PasswordDaoImpl implements PasswordDao {

	private static final String INSERT_PASSWORD = "Insert into password (website,websiteUser,user_id,password) values (?,?,?,?)";
	private static final String GET_ALL_PASSWORDS_OF_USER = "SELECT * from password WHERE user_id = ? ";
	private static final String GET_PASSWORD_BY_ID = "Select * FROM password WHERE id = ?";
	private static final String UPDATE_PASSWORD = "UPDATE password SET website = ?, websiteUser = ?, password = ? WHERE id = ? ";

	private final ConnectionFactory connectionFactory;
	private PreparedStatement ps;
	
	public PasswordDaoImpl(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
	
	@Override
	public void insertPassword(Password password) throws SQLException {
		Connection connection = connectionFactory.getConnection();		
		ps = connection.prepareStatement(INSERT_PASSWORD);
		
		ps.setString(1, password.getWebsite());
		ps.setString(2, password.getWebsiteUser());
		ps.setInt(3, password.getUserId());
		ps.setString(4, password.getPassword());
		
		if (ps.execute()) {
			System.out.println("PASSWORD ADDED ");
		}
		
		ps.close();
		connection.close();
	}

	@Override
	public Password getPasswordByUserId(int id) throws SQLException {
		Connection connection = connectionFactory.getConnection();
		ps = connection.prepareStatement(GET_PASSWORD_BY_ID);
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
		
		rs.close();
		ps.close();
		connection.close();
		
		return password;
	}

	@Override
	public void updatePassword(Password password) throws SQLException {
		Connection connection = connectionFactory.getConnection();
		ps = connection.prepareStatement(UPDATE_PASSWORD);
		ps.setString(1, password.getWebsite());
		ps.setString(2, password.getWebsiteUser());
		ps.setString(3, password.getPassword());
		ps.setInt(4, password.getId());
		
		if (ps.executeUpdate() >0) {
			System.out.println("PASSWORD UPDATED");
		} else {
			System.out.println("SOMETHING WENT WRONG");
		}
		
		ps.close();
		connection.close();
	}

	@Override
	public void deletePassword(int id) throws SQLException {
		Connection connection = connectionFactory.getConnection();
		ps = connection.prepareStatement("DELETE  FROM password where id = ?");
		ps.setInt(1, id);
		
		if (ps.execute()) {
			System.out.println("PASSWORD DELETED!");
		}
		
		ps.close();
		connection.close();
	}

	@Override
	public List<Password> getAllPasswordsForUserId(int id) throws SQLException {
		Connection connection = connectionFactory.getConnection();
		ps = connection.prepareStatement(GET_ALL_PASSWORDS_OF_USER);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		List<Password> passwords = new ArrayList<Password>();

		while (rs.next()) {
			Password password = new Password();
			password.setId(rs.getInt("id"));
			password.setWebsite(rs.getString("website"));
			password.setWebsiteUser(rs.getString("websiteUser"));
			password.setUserId(rs.getInt("user_id"));
			passwords.add(password);
		}
		
		rs.close();
		ps.close();
		connection.close();
		
		return passwords;
	}

}

package com.capstone.dao;

import java.sql.SQLException;
import java.util.List;

import com.capstone.model.User;

public interface UserDao {
	
	public void insertUser(User user) throws SQLException;
	public User getUserById(int id) throws SQLException;
	public User getUserByUsername(String username) throws SQLException;
	public void updateUser(User user) throws SQLException;
	public void deleteUser(int id);
	public List<User> getAllUsers() throws SQLException;

}

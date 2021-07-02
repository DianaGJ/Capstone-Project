package com.capstone.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.capstone.model.Password;
import com.capstone.model.User;

public interface ApplicationService {
	
	public void insertUser(User user) throws SQLException;
	public User getUserByUsername(String username) throws SQLException;
	public User getUserById(int id) throws SQLException;
	public boolean isUsernameAvaiable(String username) throws SQLException;
	public List<User> getUsers() throws SQLException;
	public List<Password> getAllPasswords(int id) throws SQLException;
	public void updateUser(User user) throws SQLException;
	public boolean deleteUser(User user);
	public void deletePassword(int id) throws SQLException;
	public void addPasswordToUser(Password password) throws SQLException;
	public Password getPassword(int id) throws SQLException;
	public void updatePassword(Password password) throws SQLException;
}

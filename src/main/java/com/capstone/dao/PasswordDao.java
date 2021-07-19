package com.capstone.dao;

import java.sql.SQLException;
import java.util.List;

import com.capstone.model.Password;

public interface PasswordDao {
	
	public void insertPassword(Password password) throws SQLException;
	public Password getPasswordByUserId(int userId) throws SQLException;
	public void updatePassword(Password password) throws SQLException;
	public void deletePassword(int id) throws SQLException;
	public List<Password> getAllPasswordsForUserId(int id) throws SQLException;

}

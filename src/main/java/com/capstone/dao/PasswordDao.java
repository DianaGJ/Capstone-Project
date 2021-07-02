package com.capstone.dao;

import java.sql.SQLException;
import java.util.List;

import com.capstone.model.Password;
import com.capstone.model.User;

public interface PasswordDao {
	
	public void insert(Password password) throws SQLException;
	public Password getById(int id) throws SQLException;
	public void update(Password password) throws SQLException;
	public void delete(int id) throws SQLException;
	public List<Password> getAllForUser(int id) throws SQLException;

}

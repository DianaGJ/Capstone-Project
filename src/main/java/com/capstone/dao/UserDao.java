package com.capstone.dao;

import java.sql.SQLException;
import java.util.List;

import com.capstone.model.User;

public interface UserDao {
	
	public void insert(User user) throws SQLException;
	public User getById(int id) throws SQLException;
	public User getByUsername(String username) throws SQLException;
	public void update(User user) throws SQLException;
	public void delete(int id);
	public List<User> getAll() throws SQLException;
	

}

package com.capstone.dao;

import java.util.List;

import com.capstone.model.User;

public interface DaoService {
	public boolean createUser(User user);
	public User getUser(long id);
	public List<User> getUsers();
	public boolean updateUser(User user);
	public boolean deleteUser(User user);
}

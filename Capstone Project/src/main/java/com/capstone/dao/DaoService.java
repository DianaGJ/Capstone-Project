package com.capstone.dao;

import java.util.List;
import java.util.UUID;

import com.capstone.model.User;

public interface DaoService {
	public boolean createUser(User user);
	public User getUser(UUID id);
	public List<User> getUsers();
	public boolean updateUser(User user);
	public boolean deleteUser(User user);
}

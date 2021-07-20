package com.capstone.dao;

import java.sql.SQLException;
import java.util.List;

import com.capstone.model.Password;
import com.capstone.model.User;

public class ApplicationServiceImpl implements ApplicationService {
	
	UserDao userDao;
	PasswordDao passwordDao;
	
	public  ApplicationServiceImpl() {
		userDao = new UserDaoImpl();
		passwordDao = new PasswordDaoImpl();
	}

	public void insertUser(User user) throws SQLException {
		userDao.insertUser(user);
	}
	
	public User getUserById(int id) throws SQLException {
		return userDao.getUserById(id);
	}

	public User getUserByUsername(String username) throws SQLException {
		return userDao.getUserByUsername(username);
	}
	
	public User getUserByEmail(String email) throws SQLException {
		return userDao.getUserByEmail(email);
	}

	public List<User> getAllUsers() throws SQLException {
		return userDao.getAllUsers();
	}

	public void updateUser(User user) throws SQLException {
		userDao.updateUser(user);
	}

	public void deleteUser(int id) {
		userDao.deleteUser(id);
	}

	public void insertPassword(Password password) throws SQLException {
		passwordDao.insertPassword(password);
	}

	public void updatePassword(Password password) throws SQLException {
		passwordDao.updatePassword(password);
	}

	public List<Password> getAllPasswordsForUserId(int id) throws SQLException {
		return passwordDao.getAllPasswordsForUserId(id);
	}

	public void deletePassword(int id) throws SQLException {
		passwordDao.deletePassword(id);
	}

	public Password getPasswordByUserId(int id) throws SQLException {
		return passwordDao.getPasswordByUserId(id);
	}
}

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

	@Override
	public void insertUser(User user) throws SQLException {
		userDao.insert(user);
		
	}
	
	@Override
	public User getUserByUsername(String username) throws SQLException {
	
		
		return userDao.getByUsername(username);
	}

	@Override
	public boolean isUsernameAvaiable(String username) throws SQLException {
		
		return userDao.getByUsername(username) != null ? false : true ;
	}

	@Override
	public List<User> getUsers() throws SQLException {
		
		return userDao.getAll();
	}

	@Override
	public void updateUser(User user) throws SQLException {
		
		userDao.update(user);
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPasswordToUser(Password password) throws SQLException {
		passwordDao.insert(password);
		
	}

	@Override
	public void updatePassword(Password password) throws SQLException {
		passwordDao.update(password);
		
	}

	@Override
	public List<Password> getAllPasswords(int id) throws SQLException {
		// TODO Auto-generated method stub
		return passwordDao.getAllForUser(id);
	}

	@Override
	public void deletePassword(int id) throws SQLException {
		passwordDao.delete(id);
		
	}

	@Override
	public Password getPassword(int id) throws SQLException {
		// TODO Auto-generated method stub
		return passwordDao.getById(id);
	}

	@Override
	public User getUserById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return userDao.getById(id);
	}


	
	

}

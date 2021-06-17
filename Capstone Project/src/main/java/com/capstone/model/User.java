package com.capstone.model;

import java.util.ArrayList;

public class User {
	private long id;
	private String username;
	private String password;
	private String email;
	private ArrayList<StoredItem> items;
	
	public long getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void store(StoredItem item) {
		items.add(item);
	}
	
	public void delete(StoredItem item) {
		int index = items.indexOf(item);
		items.remove(index);
	}
}

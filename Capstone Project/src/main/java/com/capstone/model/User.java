package com.capstone.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class User {
	private UUID id;
	private String username;
	private String password;
	private String email;
	private List<StoredItem> items;
	
	public User(UUID id, String username, String password, String email) {
		this(id, username, password, email, new ArrayList<StoredItem>());
	}
	
	public User(UUID id, String username, String password, String email, List<StoredItem> items) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.items = items;
	}
	
	public UUID getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void store(StoredItem item) {
		items.add(item);
	}
	
	public void delete(StoredItem item) {
		int index = items.indexOf(item);
		items.remove(index);
	}
	
	public List<StoredItem> getItems() {
		return items;
	}
}

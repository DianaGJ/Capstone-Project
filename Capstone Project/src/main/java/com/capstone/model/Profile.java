package com.capstone.model;

import java.util.Date;
import java.util.UUID;

public class Profile extends StoredItem {
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	
	public Profile(UUID id, String name, String description, Date created, Date modified, String firstName, String lastName, String address, String city) {
		super(id, name, description, created, modified);
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getCity() {
		return city;
	}
}

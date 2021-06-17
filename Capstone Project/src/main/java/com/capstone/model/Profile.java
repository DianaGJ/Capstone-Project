package com.capstone.model;

public class Profile extends StoredItem {
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	
	public Profile(String name, String description, String firstName, String lastName, String address, String city) {
		super(name, description);

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

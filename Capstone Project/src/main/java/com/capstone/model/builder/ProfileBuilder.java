package com.capstone.model.builder;

import com.capstone.model.Profile;
import com.capstone.model.StoredItem;

public class ProfileBuilder extends StoredItemBuilder {
	private String firstname;
	private String lastname;
	private String address;
	private String city;
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public StoredItem build() {
		return new Profile(getId(), getName(), getDescription(), null, null, firstname, lastname, address, city);
	}
}

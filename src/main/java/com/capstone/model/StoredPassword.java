package com.capstone.model;

import java.util.Date;
import java.util.UUID;

public class StoredPassword {
	private UUID id;
	private String name;
	private String description;
	private Date created;
	private Date lastModified;
    private String website;
    private String password;
	
	public StoredPassword(UUID id, String name, String description, Date created, Date lastModified, String website, String password) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.created = created;
		this.lastModified = lastModified;
        this.website = website;
        this.password = password;
	}
	
	public UUID getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getCreated() {
		return created;
	}
	
	public Date getLastModified() {
		return lastModified;
	}
	
    public String getWebsite() {
        return website;
    }
    
    public void setWebsite(String website) {
        this.website = website;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}

package com.capstone.model;

import java.util.Date;
import java.util.UUID;

public abstract class StoredItem {
	private UUID id;
	private String name;
	private String description;
	private Date created;
	private Date lastModified;
	
	public StoredItem(UUID id, String name, String description, Date created, Date lastModified) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.created = created;
		this.lastModified = lastModified;
	}
	
	public StoredItem(UUID id, String name, String description) {
		this(id, name, description, null, null);
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
	
	public abstract String getItemTypeCode();
}

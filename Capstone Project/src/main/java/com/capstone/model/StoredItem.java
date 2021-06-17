package com.capstone.model;

import java.util.Date;

public abstract class StoredItem {
	private long id;
	private String name;
	private String description;
	private Date created;
	private Date lastModified;
	
	// Helper constructor that uses one DateTime for created and lastModified.
	private StoredItem(String name, String description, Date created) {
		this(0, name, description, created, created);
	}
	
	public StoredItem(long id, String name, String description, Date created, Date lastModified) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.created = created;
		this.lastModified = lastModified;
	}
	
	public StoredItem(String name, String description) {
		this(name, description, new Date());
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Date getCreated() {
		return created;
	}
	
	public final Date getLastModified() {
		return lastModified;
	}
	
	// Tells StoredItem to update lastModified to the current date and time.
	protected final void updateLastModified() {
		lastModified = new Date();
	}
}

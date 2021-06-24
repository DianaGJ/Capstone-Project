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
	
	public UUID getId() {
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

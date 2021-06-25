package com.capstone.model.builder;

import java.util.UUID;

import com.capstone.model.StoredItem;

public abstract class StoredItemBuilder {
	private UUID id;
	private String name;
	private String description;
	
	public StoredItemBuilder setId(UUID id) {
		this.id = id;
		return this;
	}
	
	protected UUID getId() {
		return id;
	}
	
	public StoredItemBuilder setName(String name) {
		this.name = name;
		return this;
	}
	
	protected String getName() {
		return name;
	}
	
	public StoredItemBuilder setDescription(String description) {
		this.description = description;
		return this;
	}
	
	protected String getDescription() {
		return description;
	}
	
	public abstract StoredItem build();
}

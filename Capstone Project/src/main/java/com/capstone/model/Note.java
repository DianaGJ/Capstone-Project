package com.capstone.model;

import java.util.Date;
import java.util.UUID;

public class Note extends StoredItem {
	private String contents;
	
	public Note(UUID id, String name, String description, Date created, Date modified, String contents) {
		super(id, name, description, created, modified);
		
		this.contents = contents;
	}
	
	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
}

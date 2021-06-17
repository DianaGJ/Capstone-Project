package com.capstone.model;

public class Note extends StoredItem {
	private String contents;
	
	public Note(String name, String description, String contents) {
		super(name, description);
		
		this.contents = contents;
	}
	
	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
}

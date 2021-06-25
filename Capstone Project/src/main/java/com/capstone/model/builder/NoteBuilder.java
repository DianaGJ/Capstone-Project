package com.capstone.model.builder;

import com.capstone.model.Note;
import com.capstone.model.StoredItem;

public class NoteBuilder extends StoredItemBuilder {
	private String contents;
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	@Override
	public StoredItem build() {
		return new Note(getId(), getName(), getDescription(), null, null, contents);
	}
}

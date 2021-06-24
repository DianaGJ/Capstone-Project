package com.capstone.dao.strategy;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.capstone.model.Note;
import com.capstone.model.User;

public class SaveNoteDecorator extends SaveItemPreparer {	
	public SaveNoteDecorator(User user, Note item) {
		super(user, item);
	}
	
	@Override
	public void prepareInsert(PreparedStatement statement) throws SQLException {		
		super.prepareInsert(statement);

		Note note = (Note)getItem();
		
		statement.setString(7, note.getContents());
	}
}

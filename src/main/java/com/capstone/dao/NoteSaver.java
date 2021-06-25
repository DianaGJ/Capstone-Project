package com.capstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.capstone.model.Note;
import com.capstone.model.StoredItem;
import com.capstone.model.User;

public class NoteSaver extends StoredItemSaver {
	private static final String INSERT_NOTE_SQL = "INSERT INTO note (item_id, contents) VALUES(?, ?);";

	public NoteSaver(Connection connection) {
		super(connection);
	}
	
	@Override
	public void save(User user, StoredItem item) throws SQLException {
		super.save(user, item);
		
		setExecuting(true);
		
		Note note = (Note)item;
		
		PreparedStatement statement = getConnection().prepareStatement(INSERT_NOTE_SQL);
		statement.setString(1, note.getId().toString());
		statement.setString(2, note.getContents());
		statement.execute();
		
		setExecuting(false);
	}
}

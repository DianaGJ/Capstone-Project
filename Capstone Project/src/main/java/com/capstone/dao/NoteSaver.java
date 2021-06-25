package com.capstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.capstone.model.Note;
import com.capstone.model.StoredItem;
import com.capstone.model.User;

public class NoteSaver extends StoredItemSaver {
	private static final String INSERT_NOTE_SQL = "INSERT INTO note (item_id, contents) VALUES(?, ?);";
	private static final String UPDATE_NOTE_SQL = "UPDATE note SET contents = ? WHERE item_id = ?;";

	public NoteSaver(Connection connection) {
		super(connection);
	}
	
	@Override
	public void executeInsert(User user, StoredItem item) throws SQLException {
		super.executeInsert(user, item);
		
		Note note = (Note)item;
		
		PreparedStatement statement = getConnection().prepareStatement(INSERT_NOTE_SQL);
		statement.setString(1, note.getId().toString());
		statement.setString(2, note.getContents());
		statement.execute();
	}
	
	@Override
	public void executeUpdate(User user, StoredItem item) throws SQLException {
		super.executeUpdate(user, item);
		
		Note note = (Note)item;
		
		PreparedStatement statement = getConnection().prepareStatement(UPDATE_NOTE_SQL);
		statement.setString(1, note.getContents());
		statement.setString(2, note.getId().toString());
		statement.execute();
	}
}

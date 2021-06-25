package com.capstone.dao;

import java.sql.Connection;

import com.capstone.model.StoredItem;

public final class StoredItemSaverFactory {
	
	private static StoredItemSaverFactory instance;
	
	private NoteSaver noteSaver;
	private PasswordSaver passwordSaver;
	private ProfileSaver profileSaver;
	
	private StoredItemSaverFactory() {
		
	}
	
	public static synchronized StoredItemSaverFactory getInstance() {
		if (instance == null) {
			instance = new StoredItemSaverFactory();
		}
		
		return instance;
	}
	
	public StoredItemSaver getSaver(Connection connection, StoredItem item) {
		switch (item.getItemTypeCode()) {
		case "NO":
			if (getInstance().noteSaver == null) {
				getInstance().noteSaver = new NoteSaver(connection);
			}
			else {
				getInstance().noteSaver.setConnection(connection);
			}
			
			return getInstance().noteSaver;
		case "PA":
			if (getInstance().passwordSaver == null) {
				getInstance().passwordSaver = new PasswordSaver(connection);
			}
			else {
				getInstance().passwordSaver.setConnection(connection);
			}
			
			return getInstance().passwordSaver;
		case "PR":
			if (getInstance().profileSaver == null) {
				getInstance().profileSaver = new ProfileSaver(connection);
			}
			else {
				getInstance().profileSaver.setConnection(connection);
			}
			
			return getInstance().profileSaver;
		default:
			return null;
		}
	}
}

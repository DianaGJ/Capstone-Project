package com.capstone.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.capstone.dbconnection.MysqlConnection;
import com.capstone.model.User;

public class UserValidator {

//	public static User getUserIfExists(String username, String password) {
//		int userCount = 0;
//		List<User> users = new ArrayList<User>();
//		try {
//			PreparedStatement ps = MysqlConnection.getConnection().prepareStatement(
//					"SELECT * FROM user where username = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
//					ResultSet.CONCUR_UPDATABLE);
//			ps.setString(1, username);
//			if (ps.execute()) {
//				ResultSet rs = ps.executeQuery();
//
//				while (rs.next() && userCount < 2) {
//					users.add(new User(UUID.fromString(rs.getString("id")), rs.getString("username"),
//							rs.getString("password"), rs.getString("email"), rs.getBoolean("verified"),
//							rs.getString("verification_code")));
//					userCount++;
//					if (userCount > 1) {
//						System.out.println("there's two users with the same username");
//						break;
//					}
//				}
//			} else {
//				// If execution of PreparedStatement fails
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return userCount == 1 ? users.get(0) : null;
//
//	}

}

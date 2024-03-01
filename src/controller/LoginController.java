package controller;

import java.sql.Connection;

import model.UserDAOMySQLImpl;
import util.HashedPassword;
import model.Role;
import model.User;

public class LoginController {
	

	public static boolean AuthenticateUser(Connection connection, String username, String password) {
		UserDAOMySQLImpl uDAO = new UserDAOMySQLImpl(connection);
		User dbUser = uDAO.getUserByUsername(username);
		if(dbUser == null) {
			return false;
		}
		return HashedPassword.authenticate(username, password, dbUser);
	}
	
	public static Role GetUserRole(Connection connection, String username) {
		UserDAOMySQLImpl uDAO = new UserDAOMySQLImpl(connection);
		User user = uDAO.getUserByUsername(username);
		return user.getRole();
	}
}

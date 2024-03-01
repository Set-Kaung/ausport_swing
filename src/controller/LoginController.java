package controller;

import java.sql.Connection;

import model.UserDAOImpl;
import util.HashedPassword;
import model.Role;
import model.User;

public class LoginController {
	

	public static boolean AuthenticateUser(Connection connection, String username, String password) {
		UserDAOImpl uDAO = new UserDAOImpl(connection);
		User dbUser = uDAO.getUserByUsername(username);
		if(dbUser == null) {
			return false;
		}
		return HashedPassword.authenticate(username, password, dbUser);
	}
	
	public static Role GetUserRole(Connection connection, String username) {
		UserDAOImpl uDAO = new UserDAOImpl(connection);
		User user = uDAO.getUserByUsername(username);
		return user.getRole();
	}
}

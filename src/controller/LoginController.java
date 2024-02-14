package controller;

import java.sql.Connection;

import model.UserDAOImpl;
import util.HashedPassword;
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
}

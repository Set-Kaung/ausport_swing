package controller;

import java.sql.Connection;

import model.Role;
import model.User;
import model.UserDAOImpl;
import util.HashedPassword;

public class SignUpController {
	public static final int SUCCESS = 0;
	public static final int WRONG_ID = -1;
	public static final int PASSWORD_NOT_MATCH = -2;
	public static final int DATABASE_ERROR = -3;
	public static final int USER_ALREADY_EXISTS = 1;

	public static int signUpUser(Connection connection, String username, String firstPassword, String secondPassword) {
		if (!username.matches("u\\d{7}")) {

			return WRONG_ID;
		}
		if (!firstPassword.equals(secondPassword)) {
			return PASSWORD_NOT_MATCH;
		}
		
		if(checkIfUserExists(connection, username)) {
			return USER_ALREADY_EXISTS; 
		}

		User u = new User(username, HashedPassword.getHashedPassword(firstPassword), Role.NORMAL);
		UserDAOImpl dao = new UserDAOImpl(connection);
		long rows = dao.insertUser(u);
		if (rows == 0) {
			
			return DATABASE_ERROR;
		}
		return SUCCESS;
	}
	
	private static boolean checkIfUserExists(Connection connection, String username) {
		UserDAOImpl dao = new UserDAOImpl(connection);
		User u = dao.getUserByUsername(username);
		return u != null;
	}
}

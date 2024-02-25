package controller;

import java.sql.Connection;

import model.Role;
import model.User;
import model.UserDAOImpl;
import util.HashedPassword;

public class SignUpController {
	public static final int WRONG_ID = -1;
	public static final int PASSWORD_NOT_MATCH = -2;
	public static final int DATABASE_ERROR = -3;
	public static final int SUCCESS = 0;

	public static int signUpUser(Connection connection, String username, String firstPassword, String secondPassword) {
		if (!username.matches("u\\d{7}")) {

			return WRONG_ID;
		}
		if (!firstPassword.equals(secondPassword)) {
			return PASSWORD_NOT_MATCH;
		}

		User u = new User(username, HashedPassword.getHashedPassword(firstPassword), Role.NORMAL);
		UserDAOImpl dao = new UserDAOImpl(connection);
		long rows = dao.insertUser(u);
		if (rows == 0) {
			return DATABASE_ERROR;
		}
		return SUCCESS;
	}
}

package controller;

import java.sql.Connection;

import model.User;
import model.UserDAOMySQLImpl;
import util.HashedPassword;

public class UserController {
	public static final int PASSWORD_NOT_MATCH = -1;
	public static final int NOT_AUTHENTICATED = -2;
	public static final int SUCCESS = 0;


	
	public static int updatePassword(Connection connection, String username, String oldPassword, String newPassword, String confrimPassword) {
		if(!newPassword.equals(confrimPassword)) {
			return PASSWORD_NOT_MATCH;
		}
		UserDAOMySQLImpl dao = new UserDAOMySQLImpl(connection);
		User dbUser = dao.getUserByUsername(username);
		if(HashedPassword.authenticate(username, oldPassword, dbUser)) {
			User userUpdate = new User(username, HashedPassword.getHashedPassword(newPassword),dbUser.getRole());
			dao.updateUser(userUpdate);
			return SUCCESS;
		}
		return NOT_AUTHENTICATED;
	}

}

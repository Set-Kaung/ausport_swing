package controller;

import java.sql.Connection;
import java.util.regex.Pattern;

import model.Role;
import model.User;
import model.UserDAOImpl;
import util.HashedPassword;

public class SignUpController {
	public static boolean signUpUser(Connection connection, String username,String firstPassword, String secondPassword) {
		if(!username.matches("u\\d{7}")) {
			return false;
		}
		if(!firstPassword.equals(secondPassword)) {
			return false;
		}
		
		User u = new User(username, HashedPassword.getHashedPassword(firstPassword), Role.NORMAL);
		UserDAOImpl dao = new UserDAOImpl(connection);
		long rows = dao.insertUser(u);
		if(rows == 0) {
			return false;
		}
		return true;
	}
}

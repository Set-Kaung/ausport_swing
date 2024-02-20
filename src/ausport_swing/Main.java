package ausport_swing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import controller.SignUpController;
import model.*;
import view.ChooseView;
import view.Login;
import view.UserMain;

public class Main {
	
	
	

	public static void main(String[] args) {
		String host = DatabaseConfig.DB_HOST;
		String name = DatabaseConfig.DB_NAME;
		String port = DatabaseConfig.DB_PORT;
		String user = DatabaseConfig.DB_USER;
		String password = DatabaseConfig.DB_PASSWORD;
		String connectionString = String.format("jdbc:mysql://%s:%s/%s",host,port,name);
		
		try {
		Connection connection = DriverManager.getConnection(connectionString,user,password);
		
		new Login(connection);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}





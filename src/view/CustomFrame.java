package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public abstract class CustomFrame extends JFrame {

	private static final long serialVersionUID = 920413463961465805L;
	
	protected static Connection connection;
	private static Dimension screenReso = Toolkit.getDefaultToolkit().getScreenSize();
	private static double width = screenReso.getWidth();
	private static double height = screenReso.getHeight();

	public static void setConnection(Connection conn) {
		connection  = conn;
	}
	
	protected void setupFrame() {
		pack();
		Dimension frameSize = this.getSize();
		int x = (int) ((width/2) - (frameSize.getWidth()/2));
		int y = (int) ((height/2) - (frameSize.getHeight()/2));
		this.setLocation(x, y);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	
}

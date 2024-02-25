package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class CustomFrame extends JFrame {

	private static final long serialVersionUID = 920413463961465805L;

	private static Dimension screenReso = Toolkit.getDefaultToolkit().getScreenSize();
	private static double width = screenReso.getWidth();
	private static double height = screenReso.getHeight();

	public CustomFrame() {

	}
	protected void setupFrame() {
		pack();
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		Dimension frameSize = this.getSize();
		int x = (int) ((width/2) - (frameSize.getWidth()/2));
		int y = (int) ((height/2) - (frameSize.getHeight()/2));
		this.setLocation(x, y);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
	}
}

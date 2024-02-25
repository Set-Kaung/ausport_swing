package view;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BackButton extends JButton {
	private static Path filePath = Paths.get("src","assests","Back"+".png");
	private static ImageIcon icon = new ImageIcon(filePath.toString());
	
	public BackButton() {
		
		this.setIcon(icon);
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
	}
}

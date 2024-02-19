package view;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Signup extends JFrame {
	private JTextField textField;
	public Signup() {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		JLabel signupLbl = new JLabel("SignUp");
		panel.add(signupLbl);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3);
	}

}

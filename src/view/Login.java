package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controller.LoginController;

public class Login extends CustomFrame {
	private static final long serialVersionUID = 1L;

	private JTextField usernameField;
	private JPasswordField passwordField;

	public Login(Connection connection) {
		setTitle("AUSport");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));

		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		panel_1.add(lblNewLabel_1);

		usernameField = new JTextField();
		panel_1.add(usernameField);
		usernameField.setColumns(10);

		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));

		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		panel_2.add(lblNewLabel_2);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setColumns(10);
		panel_2.add(passwordField);

		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));

		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		panel_4.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);

		JButton loginBtn = new JButton("Login");
		panel_5.add(loginBtn);
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = String.valueOf(passwordField.getPassword());
				if (LoginController.AuthenticateUser(connection, username, password)) {
					JOptionPane.showMessageDialog(loginBtn, "Login Successful!");
					new UserMain(connection, username);
					dispose();
				} else {
					JOptionPane.showMessageDialog(loginBtn, "Wrong Username or Password", "Login Failed!",
							JOptionPane.ERROR_MESSAGE);
					passwordField.setText(null);
				}

			}
		});
		loginBtn.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);

		JButton signupBtn = new JButton("SignUp");
		panel_6.add(signupBtn);
		signupBtn.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		signupBtn.setBackground(Color.LIGHT_GRAY);
		signupBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Signup(connection);
				dispose();
			}

		});

		setupFrame();
	}

}

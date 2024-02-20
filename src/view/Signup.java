package view;

import javax.swing.JFrame;

import java.sql.Connection;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.SignUpController;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Signup extends JFrame {
	private JTextField usernameField;
	private Connection connection;
	private JPasswordField passwordField;
	private JPasswordField passwordConfirmField;
	public Signup(Connection connection) {
		this.connection = connection;
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel.getLayout();
		flowLayout_3.setHgap(20);
		flowLayout_3.setVgap(20);
		getContentPane().add(panel);
		
		JLabel signupLbl = new JLabel("SignUp");
		signupLbl.setFont(new Font("Times New Roman", Font.BOLD, 22));
		panel.add(signupLbl);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setHgap(20);
		getContentPane().add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lblNewLabel);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_1.add(usernameField);
		usernameField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setHgap(20);
		getContentPane().add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		passwordField.setColumns(10);
		panel_2.add(passwordField);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_3.getLayout();
		flowLayout_2.setHgap(20);
		getContentPane().add(panel_3);
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_2 = new JLabel("Confirm");
		panel_6.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JLabel lblNewLabel_3 = new JLabel("Password:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_6.add(lblNewLabel_3);
		
		passwordConfirmField = new JPasswordField();
		passwordConfirmField.setToolTipText("Confirm Password");
		passwordConfirmField.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		passwordConfirmField.setColumns(10);
		panel_3.add(passwordConfirmField);
		
		JPanel panel_4 = new JPanel();
		getContentPane().add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7);
		
		JButton signUpBtn = new JButton("Sign-Up");
		signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = String.valueOf(passwordField.getPassword());
				String confirmPassword = String.valueOf(passwordConfirmField.getPassword());
				switch (SignUpController.signUpUser(connection, username, password, confirmPassword)) {
				case SignUpController.SUCCESS:{
					JOptionPane.showMessageDialog(signUpBtn,"Sign Up Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
				case SignUpController.PASSWORD_NOT_MATCH:{
					JOptionPane.showMessageDialog(signUpBtn,"Password does not match!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				case SignUpController.DATABASE_ERROR:{
					JOptionPane.showMessageDialog(signUpBtn,"Database Error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				case SignUpController.WRONG_ID:{
					JOptionPane.showMessageDialog(signUpBtn,"Username should be u6xxxxxx", "Error", JOptionPane.ERROR_MESSAGE);
				}
				default:{
					JOptionPane.showMessageDialog(signUpBtn,"Internal Error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				}
			}
		});
		panel_7.add(signUpBtn);
		
		JPanel panel_8 = new JPanel();
		panel_5.add(panel_8);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_8.add(btnNewButton_1);
		
		setBounds(100,100,349,498);
		setResizable(false);
		setVisible(true);
		pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}

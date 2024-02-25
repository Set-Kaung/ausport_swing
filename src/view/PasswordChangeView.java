package view;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import controller.UserController;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PasswordChangeView extends CustomFrame {

	private static final long serialVersionUID = -4381493277831001950L;
	private JPasswordField oldPasswordField;
	private JPasswordField confirmPasswordFIeld;
	private JPasswordField newPasswordField;

	public PasswordChangeView(String username) {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Change Password");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		
		JPanel panel_10 = new JPanel();
		panel_1.add(panel_10);
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_1 = new JLabel("Old");
		panel_10.add(lblNewLabel_1);
		
		JLabel lblNewLabel_6 = new JLabel("Password:");
		panel_10.add(lblNewLabel_6);
		
		oldPasswordField = new JPasswordField();
		oldPasswordField.setColumns(10);
		oldPasswordField.setEchoChar('*');
		panel_1.add(oldPasswordField);
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3);
		
		JPanel panel_9 = new JPanel();
		panel_3.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_3 = new JLabel("New");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_9.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("Password:");
		panel_9.add(lblNewLabel_5);
		
		newPasswordField = new JPasswordField();
		newPasswordField.setColumns(10);
		newPasswordField.setEchoChar('*');
		panel_3.add(newPasswordField);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);
		
		JPanel panel_8 = new JPanel();
		panel_2.add(panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_4 = new JLabel("Confrim");
		panel_8.add(lblNewLabel_4);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		panel_8.add(lblNewLabel_2);
		
		confirmPasswordFIeld = new JPasswordField();
		confirmPasswordFIeld.setColumns(10);
		confirmPasswordFIeld.setEchoChar('*');
		panel_2.add(confirmPasswordFIeld);
		
		JPanel panel_4 = new JPanel();
		getContentPane().add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6);
		
		JButton btnNewButton = new JButton("Change");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oldPassword = String.valueOf(oldPasswordField.getPassword());
				String newPassword = String.valueOf(newPasswordField.getPassword());
				String confirmPassword = String.valueOf(confirmPasswordFIeld.getPassword());
				switch(UserController.updatePassword(connection, username, oldPassword, newPassword, confirmPassword)){
				case UserController.SUCCESS:
					JOptionPane.showMessageDialog(panel, "Success", "Password Change Succeed!",JOptionPane.PLAIN_MESSAGE);
					break;
				case UserController.NOT_AUTHENTICATED:
					JOptionPane.showMessageDialog(panel, "Not Authenticated", "Wrong old password!", JOptionPane.ERROR_MESSAGE);
					break;
				case UserController.PASSWORD_NOT_MATCH:
					JOptionPane.showMessageDialog(panel, "Passwords do not match!", "New and confirm passwords do not match", JOptionPane.ERROR_MESSAGE);
					break;
				default:
					JOptionPane.showMessageDialog(panel, "Internal Server Error","Sorry, we can't update your data right now", JOptionPane.ERROR_MESSAGE);
					break;
				}
				new UserProfileVew(username);
				dispose();
			}
			
		});
		panel_6.add(btnNewButton);
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7);
		
		BackButton btnNewButton_1 = new BackButton();
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserProfileVew(username);
				dispose();
			}
		});
		panel_7.add(btnNewButton_1);
		
		
		
		setupFrame();
	}

}

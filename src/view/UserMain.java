package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Field;
import model.FieldDAOImpl;
import model.FieldType;
import model.User;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.util.List;

public class UserMain extends JFrame {
	private static String username;
	private Connection connection;
	public UserMain(Connection connection, String u) {
		this.connection = connection;
		username = u;
		this.setBounds(500, 500, 800, 450);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Choose Your Sport:");
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel);
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		
		setUpButtons(connection,mainPanel);
		
		
		this.repaint();
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
	}
	
	private static void setUpButtons(Connection connection, JPanel panel) {
		FieldDAOImpl fDAO = new FieldDAOImpl(connection);
		List<FieldType> fields = fDAO.getFieldTypes();
		for(FieldType ft: fields) {
			JButton btn = new JButton(ft.toString());
			btn.setSize(100,30);
			panel.add(btn);
		}
	}
	
}

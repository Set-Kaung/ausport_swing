package view;

import javax.swing.JFrame;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;


import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Field;
import model.FieldDAOImpl;

import model.FieldType;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.Box;

public class ChooseField extends JFrame {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private FieldType fType;
	private static String username;

	public ChooseField(Connection connection, FieldType fType, String u) {
		this.fType = fType;
		this.connection = connection;
		username = u;
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
//		setMinimumSize(new Dimension(300,200));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		Path filePath = Paths.get("src","assests","Back"+".png");
		ImageIcon img = new ImageIcon(filePath.toString());
		panel.setLayout(new BorderLayout(0, 0));
		JButton btnNewButton = new JButton();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserMain(connection, username);
				dispose();
			}
		});
		panel.add(btnNewButton, BorderLayout.WEST);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setIcon(img);
		btnNewButton.setFocusPainted(false);
		
		JLabel lblNewLabel = new JLabel("Choose Field");
		panel.add(lblNewLabel, BorderLayout.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut, BorderLayout.EAST);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		
		setUpPanel(panel_1);
		
		pack();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void setUpPanel(JPanel p) {
		FieldDAOImpl fDAO = new FieldDAOImpl(connection);
		Path filePath = Paths.get("src","assests",fType.toString()+"Field.png");
		ImageIcon img = new ImageIcon(filePath.toString());
		List<Field> fields = fDAO.getFieldsByType(this.fType);
		for(int i = 0; i<fields.size();i++) {
			Field f = fields.get(i);
			JButton btn = new JButton();
			btn.setText("<html><center>"+fType.toString()+ " " + (i+1) + "<br>"+"Capacity: "+f.getCapcity()+"</center></html>");
			
			btn.setIcon(img);
			btn.setHorizontalTextPosition(JButton.CENTER);
			btn.setVerticalTextPosition(JButton.BOTTOM);
			btn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new Reserve(connection, username,f.getFieldID());
					dispose();
				}
				
			});	
			p.add(btn);
		}
	}
	

}

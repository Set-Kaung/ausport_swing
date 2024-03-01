package view;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import controller.FieldController;
import controller.SignUpController;
import model.Field;
import model.FieldType;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JPasswordField;

public class AdminView extends CustomFrame {
	private JTable table;
	private JTextField usernameField;
	private JPasswordField confirmPasswordField;
	private JPasswordField passwordField;

	public AdminView() {
		Dimension pSize = new Dimension(300,250);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel titlePanel = new JPanel();
		getContentPane().add(titlePanel);
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel adminLbl = new JLabel("Admin");
		adminLbl.setFont(new Font("Times New Roman", Font.BOLD, 22));
		titlePanel.add(adminLbl);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane);
		
		JPanel addPanel = new JPanel();
		tabbedPane.addTab("Add New Field", null, addPanel, null);
		
		JPanel typePanel = new JPanel();
		addPanel.add(typePanel);
		typePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		JLabel lblNewLabel = new JLabel("Field Type");
		typePanel.add(lblNewLabel);
		
		JComboBox<FieldType> fieldTypeBox = new JComboBox<FieldType>();
		for(FieldType ft : FieldType.values()) {
			fieldTypeBox.addItem(ft);
		}
		fieldTypeBox.setEditable(false);
		typePanel.add(fieldTypeBox);
		
		JPanel capacityPanel = new JPanel();
		addPanel.add(capacityPanel);
		
		JLabel lblNewLabel_1 = new JLabel("Capacity");
		capacityPanel.add(lblNewLabel_1);
		
		
		
		
		JComboBox<Integer> capacityBox = new JComboBox<Integer>();
		for(int i = 1; i <=30;i++) {
			capacityBox.addItem(i);
		}
		capacityBox.setEditable(false);
		capacityPanel.add(capacityBox);
		
		JPanel newAdminPanel = new JPanel();
		tabbedPane.addTab("Add Admin", null, newAdminPanel, null);
		newAdminPanel.setLayout(new BoxLayout(newAdminPanel, BoxLayout.Y_AXIS));
		newAdminPanel.setPreferredSize(pSize);
		
		JPanel usernamePanel = new JPanel();
		newAdminPanel.add(usernamePanel);
		
		JLabel lblNewLabel_2 = new JLabel("Username:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		usernamePanel.add(lblNewLabel_2);
		
		usernameField = new JTextField();
		usernamePanel.add(usernameField);
		usernameField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		newAdminPanel.add(panel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_2.add(lblNewLabel_3);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setColumns(10);
		panel_2.add(passwordField);
		
		JPanel panel_3 = new JPanel();
		newAdminPanel.add(panel_3);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6);
		
		JLabel lblNewLabel_4 = new JLabel("Confirm");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_6.add(lblNewLabel_4);
		
		JPanel panel_1 = new JPanel();
		panel_5.add(panel_1);
		
		JLabel lblNewLabel_5 = new JLabel("Password:");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_1.add(lblNewLabel_5);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setEchoChar('*');
		panel_3.add(confirmPasswordField);
		confirmPasswordField.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		newAdminPanel.add(panel_4);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = String.valueOf(passwordField.getPassword());
				String confirmPassword = String.valueOf(confirmPasswordField.getPassword());
				switch(SignUpController.addAdmin(connection, username, password, confirmPassword)) {
				case SignUpController.SUCCESS:
					JOptionPane.showMessageDialog(newAdminPanel, "New admin added!", "Success", JOptionPane.INFORMATION_MESSAGE);
					break;
				case SignUpController.PASSWORD_NOT_MATCH:
					JOptionPane.showMessageDialog(newAdminPanel, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
					break;
				case SignUpController.USER_ALREADY_EXISTS:
					JOptionPane.showMessageDialog(newAdminPanel, "User already exists with this name!", "Error", JOptionPane.ERROR_MESSAGE);
					break;
				case SignUpController.DATABASE_ERROR:
					JOptionPane.showMessageDialog(newAdminPanel, "Internal Database Error!", "Sorry:((", JOptionPane.ERROR_MESSAGE);
					break;
				}
			}
		});
		panel_4.add(btnNewButton);
		
		JPanel availableFields = new JPanel();
		tabbedPane.addTab("Available Fields", null, availableFields, null);
		availableFields.setLayout(new BoxLayout(availableFields, BoxLayout.Y_AXIS));
		
		table = new JTable(populateTable());
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(table);
		scrollPane.setPreferredSize(pSize);
		availableFields.add(scrollPane);
		
		JPanel panel = new JPanel();
		availableFields.add(panel);
		
		JPanel addButtonPanel = new JPanel();
		addPanel.add(addButtonPanel);
		
		JButton addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int capacity = (Integer) capacityBox.getSelectedItem();
				String fieldType = String.valueOf(fieldTypeBox.getSelectedItem());
				int status = FieldController.addNewField(connection,capacity,fieldType);
				if(status == FieldController.SUCCESS) {
					JOptionPane.showMessageDialog(addPanel,"Added new field!", "Success", JOptionPane.PLAIN_MESSAGE);
					table.setModel(populateTable());
				}else {
					JOptionPane.showMessageDialog(addPanel,"Fialed to add new field!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		addButtonPanel.add(addBtn);
		
		JPanel logoutPanel = new JPanel();
		getContentPane().add(logoutPanel);
		
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginView();
				dispose();
			}
		});
		logoutPanel.add(logoutBtn);
		
		
		
		
		setupFrame();
	}
	
	private DefaultTableModel populateTable() {
		List<Field> fields = FieldController.getAllFields(connection);
		String[] columnHeaders = {"ID", "Type", "Capacity"};
		String[][] data = new String[fields.size()][];
		
		for(int i = 0; i<fields.size();i++){
			Field f = fields.get(i);
			String[] row = new String[columnHeaders.length];
			row[0] = String.valueOf(f.getFieldID());
			row[1] = f.getType().toString();
			row[2] = String.valueOf(f.getCapcity());
			data[i] = row;
		}
		
		
		DefaultTableModel model = new DefaultTableModel(data, columnHeaders) {

			private static final long serialVersionUID = 6797043056852562882L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		return model;
		
	}

}

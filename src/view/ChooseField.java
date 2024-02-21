package view;

import javax.swing.JFrame;

import java.sql.Connection;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.Field;
import model.FieldDAOImpl;
import model.FieldType;

import javax.swing.JLabel;

public class ChooseField extends JFrame {
	private Connection connection;
	private FieldType fType;
	
	public ChooseField(Connection connection, FieldType fType) {
		this.fType = fType;
		this.connection = connection;
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Choose Field");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		
		setUpPanel(panel_1);
		
		this.pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void setUpPanel(JPanel p) {
		FieldDAOImpl fDAO = new FieldDAOImpl(connection);
		List<Field> fields = fDAO.getFieldsByType(this.fType);
		for(Field f : fields) {
			JBtn 
		}
	}

}

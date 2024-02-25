package view;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Field;
import model.FieldDAOImpl;
import model.FieldType;

public class ChooseFieldView extends CustomFrame {
	private static final long serialVersionUID = 1L;
	private FieldType fType;
	private static String username;

	public ChooseFieldView(String u,FieldType fType) {
		this.fType = fType;
		username = u;
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
//		setMinimumSize(new Dimension(300,200));

		JPanel panel = new JPanel();
		getContentPane().add(panel);

		panel.setLayout(new BorderLayout(0, 0));
		BackButton backButton = new BackButton();
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChooseSportView(username);
				dispose();
			}
		});
		panel.add(backButton, BorderLayout.WEST);
		
		

		JLabel lblNewLabel = new JLabel("Choose Field");
		panel.add(lblNewLabel, BorderLayout.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut, BorderLayout.EAST);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);

		setUpPanel(panel_1);

		setupFrame();
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
			btn.setHorizontalTextPosition(SwingConstants.CENTER);
			btn.setVerticalTextPosition(SwingConstants.BOTTOM);
			btn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new ReservationView(username,f.getFieldID(),f.getType());
					dispose();
				}

			});
			p.add(btn);
		}
	}


}

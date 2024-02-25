package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.FieldDAOImpl;
import model.FieldType;

public class UserMain extends CustomFrame {
	private static final long serialVersionUID = 1L;
	private static String username;
	private Connection connection;

	public UserMain(Connection connection, String u) {
		this.connection = connection;
		username = u;
		this.setBounds(500, 500, 370, 233);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(10, 0));

		JButton profileBtn = new JButton("Profile");
		profileBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Profile(connection, username);
				dispose();
			}
		});
		panel.add(profileBtn, BorderLayout.WEST);

		JLabel lblNewLabel = new JLabel("Choose Your Sport:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(logoutBtn, "Are you sure you want to logout?",
						"Logging out?", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					new Login(connection);
					username = new String();
					dispose();
				}
			}
		});
		panel.add(logoutBtn, BorderLayout.EAST);

		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel);
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));

		setUpButtons(mainPanel);

		setupFrame();
	}

	private void setUpButtons(JPanel panel) {
		FieldDAOImpl fDAO = new FieldDAOImpl(this.connection);
		List<FieldType> fields = fDAO.getFieldTypes();
		for (FieldType ft : fields) {
			Path filePath = Paths.get("src", "assests", ft.toString() + ".png");
			ImageIcon img = new ImageIcon(filePath.toString());
			JButton btn = new JButton(ft.toString());
			if (img.getImageLoadStatus() == MediaTracker.COMPLETE) {
				btn.setIcon(img);
				btn.setHorizontalTextPosition(SwingConstants.CENTER);
				btn.setVerticalTextPosition(SwingConstants.BOTTOM);
				btn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			} else {
				btn.setFont(new Font("Times New Roman", Font.PLAIN, 16));
			}
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new ChooseField(connection, ft, username);
					dispose();
				}

			});
			btn.setSize(100, 30);
			panel.add(btn);
		}
	}

}

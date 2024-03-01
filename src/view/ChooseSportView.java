package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.FieldDAOMySQLImpl;
import model.FieldType;

public class ChooseSportView extends CustomFrame {
	private static final long serialVersionUID = 1L;
	private static String username;

	public ChooseSportView(String u) {
		username = u;
		this.setBounds(500, 500, 370, 233);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(10, 0));

		BackButton backBtn = new BackButton();
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PrimaryView(username);
				dispose();
			}
		});
		panel.add(backBtn, BorderLayout.WEST);

		JLabel lblNewLabel = new JLabel("Choose Your Sport:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));

		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel);
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));

		setUpButtons(mainPanel);

		setupFrame();
	}

	private void setUpButtons(JPanel panel) {
		FieldDAOMySQLImpl fDAO = new FieldDAOMySQLImpl(connection);
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
					new ChooseFieldView(username, ft);
					dispose();
				}

			});
			btn.setSize(100, 30);
			panel.add(btn);
		}
	}

}

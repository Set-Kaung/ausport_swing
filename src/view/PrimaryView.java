package view;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;

public class PrimaryView extends CustomFrame {
	private static final long serialVersionUID = -5733655052174006037L;
	private static String username;
	public PrimaryView(String u)  {
		username = u;
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		getContentPane().setPreferredSize(new Dimension(300,400));
		JPanel titlePanel = new JPanel();
		
		getContentPane().add(titlePanel);
		
		JLabel lblNewLabel = new JLabel("Welcome to AUSport");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		titlePanel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		
		JButton profileBtn = new JButton("Profile");
		profileBtn.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		Path filePath = Paths.get("src", "assests", "Profile" + ".png");
		ImageIcon icon = new ImageIcon(filePath.toString());
		profileBtn.setIcon(icon);
		profileBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		profileBtn.setPreferredSize(new Dimension(150,50));
		profileBtn.setIconTextGap(1);
		profileBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new UserProfileVew(username);
				dispose();
			}
			
		});
		panel_1.add(profileBtn);
		
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);
		
		JButton reservationBtn = new JButton();
		reservationBtn.setText("<html><center>Make<br>Reservation</center></html>");
		reservationBtn.setHorizontalAlignment(SwingConstants.LEFT);
		reservationBtn.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		Path reserveImagePath = Paths.get("src", "assests", "Reserve" + ".png");
		ImageIcon reserveIcon = new ImageIcon(reserveImagePath.toString());
		reservationBtn.setIcon(reserveIcon);
		reservationBtn.setHorizontalTextPosition(SwingConstants.RIGHT);
		reservationBtn.setIconTextGap(1);
		reservationBtn.setMargin(new Insets(10,5,10,20));
		reservationBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChooseSportView(username);
				dispose();
			}
		});
		panel_2.add(reservationBtn);
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3);
		
		JButton logoutBtn = new JButton("Logout");
		logoutBtn.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		logoutBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(logoutBtn, "Are you sure you want to logout?",
						"Logging out?", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					new LoginView();
					username = new String();
					dispose();
				}
			}
		});
		panel_3.add(logoutBtn);
		
		setupFrame();
	}

}

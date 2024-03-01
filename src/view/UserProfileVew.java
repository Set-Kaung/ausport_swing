package view;

import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import controller.ReservationController;
import model.Field;
import model.Reservation;
import model.ReservationDAOMySQLImpl;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;



public class UserProfileVew extends CustomFrame {


	private static final long serialVersionUID = -3636110769976570691L;
	private static String username;
	private static ArrayList<String> columnHeadersList = new ArrayList<>();

	public UserProfileVew(String u) {
		username = u;
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		BackButton backBtn = new BackButton();
		panel.add(backBtn, BorderLayout.WEST);
		
				JLabel lblNewLabel = new JLabel("Profile");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblNewLabel, BorderLayout.CENTER);
				lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
				
				JButton btnNewButton_1 = new JButton("Change Password");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new PasswordChangeView(username);
						dispose();
					}
				});
				panel.add(btnNewButton_1, BorderLayout.EAST);
				btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PrimaryView(username);
				dispose();
			}
		});

		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		JLabel lblNewLabel_1 = new JLabel("Your Reservations");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		panel_2.add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);



		int indexColumnWidth = 20;
		int startTimeColumnWidth = 180;
		int endTimeColumnWidth = 180;

		JTable reservations = new JTable(fillTable());
		reservations.getColumnModel().getColumn(0).setPreferredWidth(indexColumnWidth);
		reservations.getColumnModel().getColumn(4).setPreferredWidth(startTimeColumnWidth);
		reservations.getColumnModel().getColumn(5).setPreferredWidth(endTimeColumnWidth);



		scrollPane.setViewportView(reservations);
		scrollPane.setPreferredSize(new Dimension(900,350));



		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = reservations.getSelectedRow();			
				int fieldID = Integer.parseInt(reservations.getValueAt(row,columnHeadersList.indexOf("Field")).toString());
				String timeString = reservations.getValueAt(row,columnHeadersList.indexOf("Start Time")).toString();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
				LocalDateTime startTime = LocalDateTime.parse(timeString,formatter);
				
				StringBuilder b = new StringBuilder();
				b.append("Do you want to delete the following reservation: ");
				b.append("Reservation for ");
				b.append(reservations.getValueAt(row,columnHeadersList.indexOf("Type")).toString());
				b.append(" at ");
				b.append(reservations.getValueAt(row,columnHeadersList.indexOf("Start Time")).toString());
				int result = JOptionPane.showConfirmDialog(panel,b.toString(), "Are you sure?", JOptionPane.WARNING_MESSAGE);
				if(result == JOptionPane.YES_OPTION) {
					int status = ReservationController.deleteReservation(connection,username,fieldID,startTime);
					if(status == ReservationController.FAIL) {
						JOptionPane.showMessageDialog(panel, "Failed to delete reseravtion","Failed", JOptionPane.ERROR_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(panel, "Reservation deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
						reservations.setModel(fillTable());
					}
					
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		panel_4.add(btnNewButton);
		
		

		setupFrame();


	}

	private static DefaultTableModel fillTable() {
		String[] columnHeaders = {"No.", "Field", "Capacity","Type", "Start Time", "End Time"};
		for(String s: columnHeaders) {
			columnHeadersList.add(s);
		}
		DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss");
		ReservationDAOMySQLImpl dao = new ReservationDAOMySQLImpl(connection);
		HashMap<Reservation,Field> reserves = dao.getReservationsByUsername(username);
		String[][] rows = new String[reserves.size()][];
		int i = 0;
		List<Reservation> reservations = new ArrayList<>(reserves.keySet());
		Collections.sort(reservations);
		for(Reservation r : reservations) {
			String[] data = new String[columnHeaders.length];
			data[0] = String.valueOf(i+1);
			data[1] = String.valueOf(reserves.get(r).getFieldID());
			data[2] = String.valueOf(reserves.get(r).getCapcity());
			data[3] = reserves.get(r).getType().toString();
			data[4] = r.getStartTime().format(dateTimeFormat);
			data[5] = r.getEndTime().format(dateTimeFormat);
			rows[i] = data;
			i+=1;
		}
		
		return new DefaultTableModel(rows, columnHeaders) {
			private static final long serialVersionUID = 8182291226478041908L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
	}

}

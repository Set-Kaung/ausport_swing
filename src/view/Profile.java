package view;

import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import model.Field;
import model.Reservation;
import model.ReservationDAOImpl;

public class Profile extends CustomFrame {


	private static final long serialVersionUID = -3636110769976570691L;

	private static Connection connection;
	private static String username;


	public Profile(Connection conn, String u) {
		connection = conn;
		username = u;
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		getContentPane().add(panel);

		JLabel lblNewLabel = new JLabel("Profile");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		JLabel lblNewLabel_1 = new JLabel("Your Reservations");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 19));
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
		setupFrame();


	}

	private static DefaultTableModel fillTable() {
		String[] columnHeaders = {"No.", "Field", "Capacity","Type", "Start Time", "End Time"};
		ReservationDAOImpl dao = new ReservationDAOImpl(connection);
		HashMap<Reservation,Field> reserves = dao.getReservationsByUsername(username);
		String[][] rows = new String[reserves.size()][];
		int i = 0;
		for(Reservation r : reserves.keySet()) {
			String[] data = new String[columnHeaders.length];
			data[0] = String.valueOf(i+1);
			data[1] = String.valueOf(reserves.get(r).getFieldID());
			data[2] = String.valueOf(reserves.get(r).getCapcity());
			data[3] = reserves.get(r).getType().toString();
			data[4] = r.getStartTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			data[5] = r.getEndTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			rows[i] = data;
			i+=1;
		}
		return new DefaultTableModel(rows, columnHeaders);
	}

}

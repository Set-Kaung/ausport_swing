package view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateTimeChangeListener;
import com.github.lgooddatepicker.optionalusertools.DateVetoPolicy;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.github.lgooddatepicker.zinternaltools.DateTimeChangeEvent;

import controller.ReservationController;
import model.FieldType;
import model.ReservationDAOImpl;

public class ReservationView extends CustomFrame {

	private static final long serialVersionUID = 1L;

	static DateTimePicker dateTimePicker;
	static List<LocalDateTime> reservedTimes;
	static HashMap<LocalTime,Boolean> reserved;


	public ReservationView(String username, int fieldID, FieldType ft) {

		ReservationDAOImpl dao = new ReservationDAOImpl(connection);
		reservedTimes = dao.getReservationTimesByFieldID(fieldID);
		reserved = new HashMap<>();
		for(LocalDateTime time: reservedTimes) {
			LocalTime timefmt = time.toLocalTime();
			reserved.putIfAbsent(timefmt, true);
		}

		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(20);
		getContentPane().add(panel_1);

		JLabel lblNewLabel = new JLabel("Choose Your Time");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		panel_1.add(lblNewLabel);

		JPanel timePanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) timePanel.getLayout();
		flowLayout_1.setVgap(20);
		getContentPane().add(timePanel);

		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel_2.add(panel);
		
				JButton btnNewButton = new JButton("Reserve");
				panel.add(btnNewButton);
				btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				
				JPanel panel_3 = new JPanel();
				panel_2.add(panel_3);
				
				BackButton backBtn = new BackButton();
				backBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new ChooseFieldView(username,ft);
						dispose();
					}
				});
				panel_3.add(backBtn);
				btnNewButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						LocalDate date = dateTimePicker.getDatePicker().getDate();
						LocalTime startTime = dateTimePicker.getTimePicker().getTime();
						switch(ReservationController.makeReservation(connection, fieldID,username, LocalDateTime.of(date, startTime))){
						case ReservationController.SUCCESS:
							JOptionPane.showMessageDialog(panel_1,"Successfully made a reservation","Success", JOptionPane.PLAIN_MESSAGE);
							new PrimaryView(username);
							dispose();
							break;
						case ReservationController.FAIL:
							JOptionPane.showMessageDialog(panel_1, "Your reservation was not successful!","Reservation Failed", JOptionPane.ERROR_MESSAGE);
							break;
							
						}
					}
				});



		DatePickerSettings dateSettings;
		TimePickerSettings timeSettings;
		dateSettings = new DatePickerSettings();
		timeSettings = new TimePickerSettings();




		dateSettings.setAllowEmptyDates(false);
		timeSettings.setAllowEmptyTimes(false);
		dateSettings.setAllowKeyboardEditing(false);
		timeSettings.setAllowKeyboardEditing(false);
		dateTimePicker = new DateTimePicker(dateSettings, timeSettings);
		dateTimePicker.datePicker.getComponentToggleCalendarButton().setText("Date");
		dateTimePicker.getTimePicker().getComponentTimeTextField().setFont(new Font("Times New Roman", Font.PLAIN, 15));
		dateTimePicker.getDatePicker().getComponentDateTextField().setFont(new Font("Times New Roman", Font.PLAIN, 15));
		dateTimePicker.addDateTimeChangeListener(new DateTimeChangeListen());
		dateTimePicker.getDatePicker().getSettings().setVetoPolicy(new CustomDateVetoPolicy());
		if(LocalTime.now().isAfter(LocalTime.of(18, 0))) {
			dateTimePicker.getDatePicker().setDate(LocalDate.now().plusDays(1));
		}
		dateTimePicker.getTimePicker().getSettings().generatePotentialMenuTimes(generateTimeList(dateTimePicker.getDatePicker().getDate()));
		timePanel.add(dateTimePicker);

		setupFrame();
	}


	private static ArrayList<LocalTime> generateTimeList(LocalDate date){
		ArrayList<LocalTime> time = new ArrayList<>();
		LocalTime start;
		if(date.isEqual(LocalDate.now())) {
	            start = LocalTime.now().plusHours(1).withMinute(0);
		}else {
			start = LocalTime.of(6, 0);
		}
		LocalTime end = LocalTime.of(19,0);
		for(LocalTime currentTime = start; currentTime.isBefore(end);currentTime = currentTime.plusHours(1)) {
			if(!reserved.isEmpty()) {
				if(!reserved.containsKey(currentTime)) {
					time.add(currentTime);
				}
			}else {
				time.add(currentTime);
			}
		}
		return time;
	}
	private static class CustomDateVetoPolicy implements DateVetoPolicy{

		@Override
		public boolean isDateAllowed(LocalDate d) {
			if(d.isEqual(LocalDate.now())) {
				return !LocalTime.now().isAfter(LocalTime.of(18, 0));
			}
			return !d.isBefore(LocalDate.now());
		}

	}



	private static class DateTimeChangeListen implements DateTimeChangeListener {

      /**
       * dateOrTimeChanged, This function will be called whenever the in date or time in the
       * applicable DateTimePicker has changed.
       */
      @Override
	public void dateOrTimeChanged(DateTimeChangeEvent event) {


          // Generate available time list based on new date of date picker
          DateChangeEvent dateEvent = event.getDateChangeEvent();
          if (dateEvent != null) {
        	  		LocalDate d = dateEvent.getNewDate();
        	  		dateTimePicker.getTimePicker().getSettings().generatePotentialMenuTimes(generateTimeList(d));
          }

//          TimeChangeEvent timeEvent = event.getTimeChangeEvent();
//          if (timeEvent != null) {
//              String timeChangeMessage = "\nThe TimePicker value has changed from ("
//                  + timeEvent.getOldTime() + ") to (" + timeEvent.getNewTime() + ").";
//              System.out.println(timeChangeMessage);
//          }
      }
  }
}

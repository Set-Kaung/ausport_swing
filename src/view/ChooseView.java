package view;


import java.awt.Dimension;
import java.time.LocalTime;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateTimeChangeListener;
import com.github.lgooddatepicker.optionalusertools.PickerUtilities;
import com.github.lgooddatepicker.optionalusertools.TimeChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.github.lgooddatepicker.zinternaltools.DateTimeChangeEvent;
import com.github.lgooddatepicker.zinternaltools.TimeChangeEvent;

import javax.swing.JPanel;

public class ChooseView extends JFrame {
	
	static DateTimePicker dateTimePicker;
	
	public ChooseView() {
		
		this.setPreferredSize(new Dimension(300,450));
		DatePickerSettings dateSettings;
		TimePickerSettings timeSettings;
		dateSettings = new DatePickerSettings();
		timeSettings = new TimePickerSettings();
		
		dateSettings.setAllowEmptyDates(false);
		timeSettings.setAllowEmptyTimes(false);
		dateSettings.setAllowKeyboardEditing(false);
		timeSettings.setAllowKeyboardEditing(false);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		
		dateTimePicker = new DateTimePicker(dateSettings, timeSettings);
		dateTimePicker.addDateTimeChangeListener(new SampleDateTimeChangeListener());
		panel.add(dateTimePicker);
		
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	private static class SampleDateTimeChangeListener implements DateTimeChangeListener {

//        /**
//         * dateTimePickerName, This holds a chosen name for the component that we are listening to,
//         * for generating time change messages in the demo.
//         */
//        public String dateTimePickerName;
//
//        /**
//         * Constructor.
//         */
//        private SampleDateTimeChangeListener(String dateTimePickerName) {
//            this.dateTimePickerName = dateTimePickerName;
//        }

        /**
         * dateOrTimeChanged, This function will be called whenever the in date or time in the
         * applicable DateTimePicker has changed.
         */
        public void dateOrTimeChanged(DateTimeChangeEvent event) {
            // Report on the overall DateTimeChangeEvent.
            String messageStart = "\n\nThe LocalDateTime in " + "application" + " has changed from: (";
            String fullMessage = messageStart + event.getOldDateTimeStrict() + ") to (" + event.getNewDateTimeStrict() + ").";
            
            // Report on any DateChangeEvent, if one exists.
            DateChangeEvent dateEvent = event.getDateChangeEvent();
            if (dateEvent != null) {
                String dateChangeMessage = "\nThe DatePicker value has changed from (" + dateEvent.getOldDate()
                    + ") to (" + dateEvent.getNewDate() + ").";
                System.out.println(dateChangeMessage);
            }
            // Report on any TimeChangeEvent, if one exists.
            TimeChangeEvent timeEvent = event.getTimeChangeEvent();
            if (timeEvent != null) {
                String timeChangeMessage = "\nThe TimePicker value has changed from ("
                    + timeEvent.getOldTime() + ") to (" + timeEvent.getNewTime() + ").";
                System.out.println(timeChangeMessage);
            }
        }
    }
}

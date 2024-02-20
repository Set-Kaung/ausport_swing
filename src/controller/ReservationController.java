package controller;

import java.sql.Connection;
import java.time.LocalDateTime;

import model.Reservation;
import model.ReservationDAOImpl;

public class ReservationController {
	public static boolean reserveField(Connection connection, int fieldID, String username, LocalDateTime startTime, LocalDateTime endTime) {
		Reservation r = new Reservation(fieldID, username, startTime, endTime);
		ReservationDAOImpl rDAO = new ReservationDAOImpl(connection);
		long rows = rDAO.insertReservation(r);
		if(rows == 0) {
			return false;
		}
		return true;
	}
}

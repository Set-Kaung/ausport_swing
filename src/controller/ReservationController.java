package controller;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import model.Reservation;
import model.ReservationDAOImpl;

public class ReservationController {
	public static final int SUCCESS = 0;
	public static final int FAIL = -1;
	
	
	public static int makeReservation(Connection connection, int fieldID, String username, LocalDateTime startTime) {
		Reservation r = new Reservation(fieldID, username, startTime, startTime.plusMinutes(59));
		ReservationDAOImpl rDAO = new ReservationDAOImpl(connection);
		long rows = rDAO.insertReservation(r);
		if(rows == 0) {
			return FAIL;
		}
		return SUCCESS;
	}
	
	public static int deleteReservation(Connection connection,String username,int fieldID,  LocalDateTime startTime) {
		ReservationDAOImpl rDAO = new ReservationDAOImpl(connection);
		long rows = rDAO.deleteReservation(username,fieldID, Timestamp.valueOf(startTime));
		if(rows == 1) {
			return SUCCESS;
		}
		return FAIL;
	}
}

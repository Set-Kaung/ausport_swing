package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


public interface ReservationDAO {
    HashMap<Reservation, Field> getReservationsByUsername(String username);
    List<LocalDateTime> getReservationTimesByFieldID(int id);
    List<Reservation> getAllReservations();
    long insertReservation(Reservation r);
    long updateResrevation(Reservation r);
    long deleteReservation(String username, int fieldID,Timestamp startTime);
    
}
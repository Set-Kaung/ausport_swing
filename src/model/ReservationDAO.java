package model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


public interface ReservationDAO extends DAO {
    HashMap<Reservation, Field> getReservationsByUsername(String username);
    List<LocalDateTime> getReservationTimesByFieldID(int id);
    List<Reservation> getAllReservations();
    long insertReservation(Reservation r);
    long updateResrevation(Reservation r);
    long deleteReservation(Reservation r);
    
}
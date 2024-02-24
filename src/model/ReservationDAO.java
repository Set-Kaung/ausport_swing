package model;

import java.time.LocalDateTime;

import java.util.List;


public interface ReservationDAO extends DAO {
    List<Reservation> getReservationsByUsername(String username);
    List<LocalDateTime> getReservationTimesByFieldID(int id);
    List<Reservation> getAllReservations();
    long insertReservation(Reservation r);
    long updateResrevation(Reservation r);
    long deleteReservation(Reservation r);
    
}
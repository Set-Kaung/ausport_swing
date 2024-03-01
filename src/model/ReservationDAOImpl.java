package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ReservationDAOImpl implements ReservationDAO {
    Connection connection;

    public ReservationDAOImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public void setup() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setup'");
    }

    @Override
    public boolean checkConnection() throws Exception {
        try{
            if(connection == null){
                return false;
            }else{
                return connection.isValid(5);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void close() throws Exception {
        if(this.checkConnection()){
            connection.close();
       }
       try {
        DriverManager.getConnection("jdbc:mysql://localhost:3306/AUSport?shutdown=true", "au_admin", "admin1234");
    }
    catch (Exception e) {
        e.printStackTrace();
    }
    }

    @Override
    public List<Reservation> getAllReservations() {
       List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservations";
        try {
            Statement stmt = connection.createStatement();
            ResultSet s = stmt.executeQuery(query);
            while(s.next()){
                int reservationID = s.getInt("id");
                int fieldID = s.getInt("fieldID");
                String username = s.getString("username");
                LocalDateTime startTime = s.getTimestamp("startTime").toLocalDateTime();
                LocalDateTime endTime = s.getTimestamp("endTime").toLocalDateTime();
                Reservation r = new Reservation(reservationID, fieldID, username, startTime, endTime);
                reservations.add(r);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       return reservations;
    }

    @Override
    public HashMap<Reservation, Field> getReservationsByUsername(String username) {
        String query = "SELECT r.id,r.fieldID,r.username,r.startTime,r.endTime,f.capacity,f.`type` FROM reservations r LEFT JOIN fields f ON r.fieldID  = f.id WHERE username = ?;";
        HashMap<Reservation, Field> userReservations = new HashMap<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet s = stmt.executeQuery();
            while(s.next()){
                int id = s.getInt("id");
                int fieldID = s.getInt("fieldID");
                String userName = s.getString("username");
                LocalDateTime dateTime = s.getTimestamp("startTime").toLocalDateTime();
                LocalDateTime endTime = s.getTimestamp("endTime").toLocalDateTime();
                Reservation r = new Reservation(id, fieldID, userName, dateTime, endTime);
                	int capacity = s.getInt("capacity");
                	FieldType type = FieldType.valueOf(s.getString("type"));
                	Field f = new Field(fieldID,capacity,type);
                	userReservations.put(r, f);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return userReservations;
    }

    @Override
    public long insertReservation(Reservation r) {
        String query = "INSERT INTO reservations (fieldID,username,startTime,endTime) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, r.getFieldID());
            stmt.setString(2, r.getUsername());
            stmt.setTimestamp(3,Timestamp.valueOf(r.getStartTime()));
            stmt.setTimestamp(4, Timestamp.valueOf(r.getEndTime()));
            long affected = stmt.executeUpdate();
            return affected;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public long updateResrevation(Reservation r) {
        String query = "UPDATE reservations SET fieldID = ?, startTime = ?, endTime = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, r.getFieldID());
            stmt.setTimestamp(2, Timestamp.valueOf(r.getStartTime()));
            stmt.setTimestamp(3, Timestamp.valueOf(r.getEndTime()));
            stmt.setInt(4, r.getId());
            return stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public long deleteReservation(String username, int fieldID, Timestamp startTime) {
        String query = "DELETE FROM reservations WHERE username = ? AND fieldID = ? AND startTime = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            	stmt.setInt(2, fieldID);
            	stmt.setTimestamp(3, startTime);
            return stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
     return 0;
    }

	@Override
	public List<LocalDateTime> getReservationTimesByFieldID(int id) {
		List<LocalDateTime> times = new ArrayList<>();
		String query = "SELECT startTime, endTime FROM reservations WHERE fieldID = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet st = stmt.executeQuery();
			while(st.next()) {
				times.add(st.getTimestamp("startTime").toLocalDateTime());
				times.add(st.getTimestamp("endTime").toLocalDateTime());
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return times;
	}

    
}

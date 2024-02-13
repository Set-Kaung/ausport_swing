package model;

import java.time.*;

public class Reservation {
    private int id;
    private int fieldID;
    private String username;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Duration duration;

    public Reservation(int fieldID, String username, LocalDateTime startTime, LocalDateTime endTime) {
        this.fieldID = fieldID;
        this.username = username;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public Reservation(int id, int fieldID, String username, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.fieldID = fieldID;
        this.username = username;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public int getFieldID() {
        return fieldID;
    }
    public void setFieldID(int fieldID) {
        this.fieldID = fieldID;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    public Duration getDuration() {
        return duration;
    }
    public void setDuration(Duration duration) {
        this.duration = duration;
    }
    public int getId() {
        return id;
    }    
}

package model;


public interface DAO { 
    public void setup() throws Exception;
    public boolean checkConnection() throws Exception;
    public void close() throws Exception;
}
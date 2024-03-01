package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.HashedPassword;


public class UserDAOMySQLImpl implements UserDAO {
    private Connection connection;

    public UserDAOMySQLImpl(Connection connection){
        this.connection = connection;
    }
    
    @Override
    public void setup() {
       String query = "CREATE TABLE users (username CHAR(8) NOT NULL, hash VARBINARY(32) NOT NULL, salt VARBINARY(16) NOT NULL, role VARCHAR(30) NOT NULL, PRIMARY KEY (username));";
       try {
    	   Statement stmt = connection.createStatement();
    	   stmt.executeUpdate(query); 
       }catch(SQLException e) {
    	   System.out.println(e.getErrorCode());
       }
        
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
    public long insertUser(User u) {
        String query = "INSERT INTO users (username,hash,salt,role) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, u.getUsername());
            stmt.setBytes(2, u.getPassword().getHash());
            stmt.setBytes(3,u.getPassword().getSalt());
            stmt.setString(4, u.getRole().toString());
            long rows = stmt.executeUpdate();
            return rows;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public long updateUser(User u) {
        String query = "UPDATE users SET hash = ?, salt = ? WHERE username = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setBytes(1, u.getPassword().getHash());
            stmt.setBytes(2, u.getPassword().getSalt());
            stmt.setString(3, u.getUsername());
            return stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public long deleteUser(User u) {
       String query = "DELETE FROM users WHERE username = ?";
       try {
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, u.getUsername());
        return stmt.executeUpdate();
       } catch (Exception e) {
        System.out.println(e.getMessage());
       }
       return 0;
    }

    @Override
    public List<User> getAllUsers() {
        String query = "SELECT * FROM users";
        List<User>  users = new ArrayList<>();
        try{
            Statement stmt = connection.createStatement();
            ResultSet s = stmt.executeQuery(query);
            while(s.next()){
                String username = s.getString("username");
                byte[] hash = s.getBytes("hash");
                byte[] salt = s.getBytes("salt");
                String role = s.getString("role");
                User u = new User(username, new HashedPassword(hash, salt), Role.getRole(role));
                users.add(u);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public User getUserByUsername(String username) {
       String query = "SELECT * FROM users WHERE username = ?";
       try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet s = stmt.executeQuery();
            if(!s.isBeforeFirst()){
                return null;
            }else{
                s.next();
                String userName = s.getString("username");
                byte[] hash = s.getBytes("hash");
                byte[] salt = s.getBytes("salt");
                String role = s.getString("role");
                User u = new User(userName,new HashedPassword(hash, salt), Role.getRole(role));
                return u;
            }
       } catch (Exception e) {
            System.out.println(e.getMessage());
       }
       return null;
    }

}

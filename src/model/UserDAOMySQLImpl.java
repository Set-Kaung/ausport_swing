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
                User u = new User(username, new HashedPassword(hash, salt), Role.valueOf(role));
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
                User u = new User(userName,new HashedPassword(hash, salt), Role.valueOf(role));
                return u;
            }
       } catch (Exception e) {
            System.out.println(e.getMessage());
       }
       return null;
    }

}

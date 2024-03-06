package model;

import java.util.List;

public interface UserDAO {
    long insertUser(User u);
    long updateUser(User u);
    long deleteUser(User u);
    List<User> getAllUsers();
    User getUserByUsername(String username);
    
}

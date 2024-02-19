package model;

import java.util.List;

public interface UserDAO extends DAO {
    User getUserByUsername(String username);
    public long insertUser(User u);
    public long updateUser(User u);
    public long deleteUser(User u);

    public List<User> getAllUsers();
    
}

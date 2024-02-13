package model;

import util.HashedPassword;



public class User {
    private String username;
    private HashedPassword password;
    private Role role;
    
    public User(String studentID){
        this.username = studentID;
    }
    public User(String username, HashedPassword password, Role role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    

    public HashedPassword getPassword() {
        return password;
    }

    public void setPassword(HashedPassword password) {
        this.password = password;
    }

    public Role getRole(){
        return this.role;
    }
    
}

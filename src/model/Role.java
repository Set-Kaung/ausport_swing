package model;

public enum Role{
    ADMIN("Admin"),
    NORMAL("Normal");

    public final String label;

    private Role(String label) {
        this.label = label;
    }

    public String toString(){
        return this.label;
    }
    public static Role getRole(String role){
        switch (role) {
            case "Admin":
                return ADMIN;
            default:
                return NORMAL;
        }
    }
}


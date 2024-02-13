package model;

public enum Role{
    ADMIN("admin"),
    NORMAL("normal");

    public final String label;

    private Role(String label) {
        this.label = label;
    }

    public String toString(){
        return this.label;
    }
    public static Role getRole(String role){
        switch (role) {
            case "admin":
                return ADMIN;
            default:
                return NORMAL;
        }
    }
}


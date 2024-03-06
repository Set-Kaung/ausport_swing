package model;

public enum Role{
    Admin("Admin"),
    Normal("Normal");

    private final String label;

    private Role(String label) {
        this.label = label;
    }

    public String toString(){
        return this.label;
    }
}


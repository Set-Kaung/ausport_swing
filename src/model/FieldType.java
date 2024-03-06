package model;

public enum FieldType{
    Football("Football"),
    Basketball("Basketball"),
    Tennis("Tennis"),
    Badminton("Badminton");

    private String type;
    
    FieldType(String t){
        type = t;
    }
    
    public String toString() {
    	return this.type;
    }
    
}
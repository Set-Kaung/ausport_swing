package model;



public class Field {

    private int fieldID;
    private int capcity;
    private FieldType type;
    

    public Field(int fieldID, int capacity, String type){
        this.fieldID = fieldID;
        this.capcity = capacity;
        this.type = FieldType.valueOf(type);
    }

    public int getFieldID() {
        return fieldID;
    }
    public void setFieldID(int fieldID) {
        this.fieldID = fieldID;
    }
    public int getCapcity() {
        return capcity;
    }
    public void setCapcity(int capcity) {
        this.capcity = capcity;
    }

    public FieldType getType() {
        return type;
    }
    public void setType(FieldType type) {
        this.type = type;
    }

    
    
}

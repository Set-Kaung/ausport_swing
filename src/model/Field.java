package model;



public class Field {

    private int fieldID;
    private int capcity;
    private FieldType type;
    

    public Field(int fieldID, int capacity, String type){
        this.fieldID = fieldID;
        this.capcity = capacity;
        switch (type) {
            case "Football":
                this.type = FieldType.Football;
                break;
            case "Basketball":
                this.type = FieldType.Basketball;
                break;
            case "Tennis":
                this.type = FieldType.Tennis;
                break;
            case "Badminton":
                this.type = FieldType.Badminton;
                break;
        }
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

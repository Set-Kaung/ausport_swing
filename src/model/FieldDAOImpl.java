package model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FieldDAOImpl implements FieldDAO {
    Connection connection;

    public FieldDAOImpl(Connection connection){
        this.connection = connection;
    }
    @Override
    public void setup() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setup'");
    }

    @Override
    public boolean checkConnection() throws Exception {
        try{
            if(connection == null){
                return false;
            }else{
                return connection.isValid(5);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void close() throws Exception {
        if(!connection.isClosed()){
            connection.close();
       }
       try {
    	   checkConnection();
       }
    catch (Exception e) {
        System.out.println(e.getMessage());
    }
    }

    @Override
    public List<Field> getAllFields() {
        String query = "SELECT * FROM fields";
        List<Field> fields = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet set = stmt.executeQuery(query);
            while(set.next()){
                int id = set.getInt("id");
                int capacity = set.getInt("capacity");
                String type = set.getString("type");
                Field f = new Field(id, capacity, type);
                fields.add(f);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + "in FieldDAO");
        }
        return fields;
    }
}

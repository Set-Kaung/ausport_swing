package controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import model.Field;
import model.FieldDAO;
import model.FieldDAOMySQLImpl;
import model.FieldType;

public class FieldController {
	public static final int SUCCESS = 0;
	public static final int FAIL = -1;

	public static int addNewField(Connection connection, int capacity, String fieldType) {
		
		Field f = new Field(capacity,FieldType.valueOf(fieldType));
		FieldDAOMySQLImpl fDAO = new FieldDAOMySQLImpl(connection);
		long rowsAffected = fDAO.insertField(f);
		if(rowsAffected < 1) {
			return FAIL;
		}
		return SUCCESS;
	}
	
	public static List<Field> getAllFields(Connection connection){
		List<Field> fields = new ArrayList<>();
		FieldDAO fDAO = new FieldDAOMySQLImpl(connection);
		fields = fDAO.getAllFields();
		return fields;
		
	}

}

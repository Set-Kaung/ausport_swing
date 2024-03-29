package model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FieldDAOMySQLImpl implements FieldDAO {
	Connection connection;

	public FieldDAOMySQLImpl(Connection connection) {
		this.connection = connection;
	}


	@Override
	public List<Field> getAllFields() {
		String query = "SELECT * FROM fields";
		List<Field> fields = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet set = stmt.executeQuery(query);
			while (set.next()) {
				int id = set.getInt("id");
				int capacity = set.getInt("capacity");
				FieldType type = FieldType.valueOf(set.getString("type"));
				Field f = new Field(id, capacity, type);
				fields.add(f);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "in FieldDAO");
		}
		return fields;
	}

	@Override
	public List<Field> getFieldsByType(FieldType fType) {
		String query = "SELECT * FROM fields WHERE type = ?";
		List<Field> fields = new ArrayList<>();
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, fType.toString());
			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				int id = set.getInt("id");
				int capacity = set.getInt("capacity");
				FieldType type = FieldType.valueOf(set.getString("type"));
				Field f = new Field(id, capacity, type);
				fields.add(f);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return fields;
	}

	public List<FieldType> getFieldTypes() {
		List<FieldType> fieldTypes = new ArrayList<>();
		String query = "SELECT DISTINCT `type` FROM fields";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String type = rs.getString(1);
				fieldTypes.add(FieldType.valueOf(type));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return fieldTypes;
	}

	@Override
	public long insertField(Field f) {
		String query = "INSERT INTO fields (capacity, type) VALUES(?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1,f.getCapcity());
			stmt.setString(2, f.getType().toString());
			return stmt.executeUpdate();
		}catch(Exception e) {
			
		}
		return 0;
	}
}

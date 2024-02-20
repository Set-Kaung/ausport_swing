package model;

import java.util.List;

public interface FieldDAO extends DAO {
    List<Field> getAllFields();
    List<Field> getFieldsByType(FieldType fType);
    List<FieldType> getFieldTypes();
}


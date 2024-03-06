package model;

import java.util.List;

public interface FieldDAO {
    List<Field> getAllFields();
    List<Field> getFieldsByType(FieldType fType);
    List<FieldType> getFieldTypes();
    long insertField(Field f);
}


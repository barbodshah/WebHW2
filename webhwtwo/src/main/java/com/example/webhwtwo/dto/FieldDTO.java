package com.example.webhwtwo.dto;

public class FieldDTO {
    private Long id;
    private String fieldName;
    private String label;
    private String fieldType;
    private String defaultValue;

    public FieldDTO(Long id, String fieldName, String label, String fieldType, String defaultValue) {
        this.id = id;
        this.fieldName = fieldName;
        this.label = label;
        this.fieldType = fieldType;
        this.defaultValue = defaultValue;
    }

    public String getFieldName() { return fieldName; }

    public String getLabel() { return label; }

    public String getFieldType() { return fieldType; }
    public String getDefaultValue() { return defaultValue; }

}

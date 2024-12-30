package com.example.webhwtwo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "fields")
public class FormField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fieldName;

    private String label;

    private String fieldType;

    private String defaultValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id")
    private Form form;

    public FormField(String fieldName, String label, String fieldType, String defaultValue) {
        this.fieldName = fieldName;
        this.label = label;
        this.fieldType = fieldType;
        this.defaultValue = defaultValue;
    }

    public Long getId() {
        return id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getLabel() {
        return label;
    }

    public String getFieldType() {
        return fieldType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}

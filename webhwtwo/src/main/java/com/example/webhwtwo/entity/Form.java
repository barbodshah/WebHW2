package com.example.webhwtwo.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "forms")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean published;

    private String submitAddress;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormField> fields = new ArrayList<>();

    public Form(String name, boolean published, String submitAddress) {
        this.name = name;
        this.published = published;
        this.submitAddress = submitAddress;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isPublished() {
        return published;
    }
    public void setPublished(boolean published) {
        this.published = published;
    }

    public String getSubmitAddress() {
        return submitAddress;
    }
    public void setSubmitAddress(String submitAddress) {
        this.submitAddress = submitAddress;
    }

    public List<FormField> getFields() {
        return fields;
    }

    public void setFields(List<FormField> newFields) {
        this.fields.clear();

        if (newFields == null) {
            return;
        }

        for (FormField field : newFields) {
            field.setForm(this);
            this.fields.add(field);
        }
    }
}

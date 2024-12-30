package com.example.webhwtwo.dto;

import java.util.List;

public class FormDTO {
    private Long id;
    private String name;
    private boolean published;
    private String submitAddress;
    private List<FieldDTO> fields;

    public FormDTO() {}

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isPublished() { return published; }
    public void setPublished(boolean published) { this.published = published; }

    public String getSubmitAddress() { return submitAddress; }
    public void setSubmitAddress(String submitAddress) { this.submitAddress = submitAddress; }

    public List<FieldDTO> getFields() { return fields; }
    public void setFields(List<FieldDTO> fields) { this.fields = fields; }
}

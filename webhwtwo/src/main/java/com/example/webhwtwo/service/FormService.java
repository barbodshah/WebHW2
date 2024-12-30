package com.example.webhwtwo.service;

import com.example.webhwtwo.dto.FieldDTO;
import com.example.webhwtwo.dto.FormDTO;
import com.example.webhwtwo.entity.Form;
import com.example.webhwtwo.entity.FormField;
import com.example.webhwtwo.exception.FormNotFoundException;
import com.example.webhwtwo.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormService {

    @Autowired
    private FormRepository formRepository;

    private FormDTO convertToDTO(Form form) {
        FormDTO dto = new FormDTO();
        dto.setId(form.getId());
        dto.setName(form.getName());
        dto.setPublished(form.isPublished());
        dto.setSubmitAddress(form.getSubmitAddress());

        List<FieldDTO> fields = form.getFields().stream()
                .map(ff -> new FieldDTO(ff.getId(),
                        ff.getFieldName(),
                        ff.getLabel(),
                        ff.getFieldType(),
                        ff.getDefaultValue()))
                .collect(Collectors.toList());
        dto.setFields(fields);
        return dto;
    }

    private Form convertToEntity(FormDTO dto) {
        Form form = new Form(dto.getName(), dto.isPublished(), dto.getSubmitAddress());

        if (dto.getFields() != null) {
            form.setFields(dto.getFields().stream()
                    .map(fdto -> new FormField(fdto.getFieldName(), fdto.getLabel(), fdto.getFieldType(), fdto.getDefaultValue()))
                    .collect(Collectors.toList()));
        }
        return form;
    }

    public List<FormDTO> getAllForms() {
        return formRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FormDTO createForm(FormDTO formDTO) {
        Form form = convertToEntity(formDTO);
        if (form.getFields() != null) {
            form.getFields().forEach(ff -> ff.setForm(form));
        }
        return convertToDTO(formRepository.save(form));
    }

    public FormDTO getFormById(Long id) {
        return formRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new FormNotFoundException("Form not found with id " + id));
    }

    public FormDTO updateForm(Long id, FormDTO formDTO) {
        Form existing = formRepository.findById(id)
                .orElseThrow(() -> new FormNotFoundException("Form not found with id " + id));

        existing.setName(formDTO.getName());
        existing.setPublished(formDTO.isPublished());
        existing.setSubmitAddress(formDTO.getSubmitAddress());

        return convertToDTO(formRepository.save(existing));
    }

    public void deleteForm(Long id) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new FormNotFoundException("Form not found with id " + id));
        formRepository.delete(form);
    }

    public List<FieldDTO> getFieldsByFormId(Long formId) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new FormNotFoundException("Form not found with id " + formId));

        return form.getFields().stream()
                .map(ff -> new FieldDTO(ff.getId(), ff.getFieldName(), ff.getLabel(), ff.getFieldType(), ff.getDefaultValue()))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<FieldDTO> updateFields(Long formId, List<FieldDTO> fieldDTOs) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new FormNotFoundException("Form not found with id " + formId));

        List<FormField> newFields = fieldDTOs.stream()
                .map(fdto -> new FormField(fdto.getFieldName(), fdto.getLabel(), fdto.getFieldType(), fdto.getDefaultValue()))
                .collect(Collectors.toList());

        form.setFields(newFields);
        formRepository.save(form);

        return newFields.stream()
                .map(ff -> new FieldDTO(ff.getId(), ff.getFieldName(), ff.getLabel(), ff.getFieldType(), ff.getDefaultValue()))
                .collect(Collectors.toList());
    }

    public FormDTO togglePublishStatus(Long id) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new FormNotFoundException("Form not found with id " + id));

        form.setPublished(!form.isPublished());
        return convertToDTO(formRepository.save(form));
    }

    public List<FormDTO> getPublishedForms() {
        return formRepository.findAll().stream()
                .filter(Form::isPublished)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}

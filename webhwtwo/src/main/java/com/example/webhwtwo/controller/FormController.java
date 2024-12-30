package com.example.webhwtwo.controller;

import com.example.webhwtwo.dto.FieldDTO;
import com.example.webhwtwo.dto.FormDTO;
import com.example.webhwtwo.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forms")
public class FormController {

    @Autowired
    private FormService formService;

    // 1) Retrieve list of all forms
    @GetMapping("/")
    public List<FormDTO> getAllForms() {
        return formService.getAllForms();
    }

    // 2) Create a new form
    @PostMapping("/")
    public FormDTO createForm(@RequestBody FormDTO formDTO) {
        return formService.createForm(formDTO);
    }

    // 3) Retrieve a form by its unique identifier
    @GetMapping("/{id}")
    public FormDTO getFormById(@PathVariable Long id) {
        return formService.getFormById(id);
    }

    // 4) Update information of a specific form
    @PutMapping("/{id}")
    public FormDTO updateForm(@PathVariable Long id, @RequestBody FormDTO formDTO) {
        return formService.updateForm(id, formDTO);
    }

    // 5) Delete a form
    @DeleteMapping("/{id}")
    public void deleteForm(@PathVariable Long id) {
        formService.deleteForm(id);
    }

    // 6) View the fields of a form
    @GetMapping("/{id}/fields")
    public List<FieldDTO> getFields(@PathVariable Long id) {
        return formService.getFieldsByFormId(id);
    }

    // 7) Update the fields of a form
    @PutMapping("/{id}/fields")
    public List<FieldDTO> updateFields(@PathVariable Long id, @RequestBody List<FieldDTO> fieldDTOs) {
        return formService.updateFields(id, fieldDTOs);
    }

    // 8) Change the publication status of a form (toggle)
    @PostMapping("/{id}/publish")
    public FormDTO togglePublish(@PathVariable Long id) {
        return formService.togglePublishStatus(id);
    }

    // 9) Retrieve the list of published forms
    //    NOTE: The requirement says "/forms/{id}/published" but typically
    //    for listing ALL published forms, we’d do "/forms/published"
    //    We'll follow the provided instruction for the sake of the exercise.
    @GetMapping("/{id}/published")
    public List<FormDTO> getPublishedForms(@PathVariable Long id) {
        // The {id} is not used if we want *all* published forms,
        // but let's keep the signature to match the requirement.
        return formService.getPublishedForms();
    }
}

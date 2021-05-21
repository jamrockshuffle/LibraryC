package edu.bid.course.form;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This is a model to hold String information about Stillages
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: StillageForm: 1.0
 */

public class StillageForm {

    private String id;
    private String stillageId;
    private String stillageDescription;
    private String description;

    public StillageForm() {
    }

    public StillageForm(String id, String stillageId, String stillageDescription, String description) {
        this.id = id;
        this.stillageId = stillageId;
        this.stillageDescription = stillageDescription;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStillageId() {
        return stillageId;
    }

    public void setStillageId(String stillageId) {
        this.stillageId = stillageId;
    }

    public String getStillageDescription() {
        return stillageDescription;
    }

    public void setStillageDescription(String stillageDescription) {
        this.stillageDescription = stillageDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

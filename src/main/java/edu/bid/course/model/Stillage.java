package edu.bid.course.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * This is a model to hold information about Stillages
 * course.Stillage
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: Stillage: 1.0
 */

@Schema(description = " Stillage List? Entity")
@Document("Stillage")
public class Stillage {

    @Schema(description = " Stillage's auto-generated id")
    @Id
    private String id;

    @Schema(description = " Stillage id. Letters A-Z, А-Я - stillage, numbers 0-9 - shelves", example = " Y-6")
    private String stillageId;
    @Schema(description = " Stillage description", example = " Фентезі")
    private String stillageDescription;

    private String description;
    private int integerDescription;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;

    public Stillage() {
    }

    public Stillage(String id, String stillageId, String stillageDescription) {
        this.id = id;
        this.stillageId = stillageId;
        this.stillageDescription = stillageDescription;
    }

    public Stillage(String id, String stillageId, String stillageDescription, String description, int integerDescription, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.stillageId = stillageId;
        this.stillageDescription = stillageDescription;
        this.description = description;
        this.integerDescription = integerDescription;
        this.created_at = created_at;
        this.modified_at = modified_at;
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

    public int getIntegerDescription() {
        return integerDescription;
    }

    public void setIntegerDescription(int integerDescription) {
        this.integerDescription = integerDescription;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getModified_at() {
        return modified_at;
    }

    public void setModified_at(LocalDateTime modified_at) {
        this.modified_at = modified_at;
    }

    @Override
    public String toString() {
        return "Stillage{" +
                "id='" + id + '\'' +
                ", stillageId='" + stillageId + '\'' +
                ", stillageDescription='" + stillageDescription + '\'' +
                ", description='" + description + '\'' +
                ", integerDescription=" + integerDescription +
                ", created_at=" + created_at +
                ", modified_at=" + modified_at +
                '}';
    }
}

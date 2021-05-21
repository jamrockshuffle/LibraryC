package edu.bid.course.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * This is a model to hold information about Discount Categories
 * course.DiscountSystem
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: DiscountSystem: 1.0
 */

@Schema(description = " DiscountSystem List? Entity")
@Document("DiscountSystem")
public class DiscountSystem {

    @Schema(description = " Discount system's auto-generated id")
    @Id
    private String id;

    @Schema(description = " Category", example = "Школяр")
    private String category;
    @Schema(description = " Dicount percentage", example = "50%")
    private String discountPercentage;

    private String description;
    private int integerDescription;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;

    public DiscountSystem() {
    }

    public DiscountSystem(String id, String category, String discountPercentage) {
        this.id = id;
        this.category = category;
        this.discountPercentage = discountPercentage;
    }

    public DiscountSystem(String id, String category, String discountPercentage, String description, int integerDescription, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.category = category;
        this.discountPercentage = discountPercentage;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(String discountPercentage) {
        this.discountPercentage = discountPercentage;
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
        return "DiscountSystem{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", discountPercentage='" + discountPercentage + '\'' +
                ", description='" + description + '\'' +
                ", integerDescription=" + integerDescription +
                ", created_at=" + created_at +
                ", modified_at=" + modified_at +
                '}';
    }
}

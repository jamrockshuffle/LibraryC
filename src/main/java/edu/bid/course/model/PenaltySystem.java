package edu.bid.course.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * course.PenaltySystem
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: PenaltySystem: 1.0
 */

@Schema(description = " PenaltySystem List? Entity")
@Document("PenaltySystem")
public class PenaltySystem {

    @Schema(description = " Penalty System's auto-generated id")
    @Id
    private String id;

    @Schema(description = " Book's damage description", example = " Немає палітурки")
    private String damageDescription;
    @Schema(description = " Sum of the penalty", example = " 99.99")
    private BigDecimal penaltySum;

    private String description;
    private int integerDescription;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;

    public PenaltySystem(String id, String damageDescription, BigDecimal penaltySum) {
        this.id = id;
        this.damageDescription = damageDescription;
        this.penaltySum = penaltySum;
    }

    public PenaltySystem(String id, String damageDescription, BigDecimal penaltySum, String description, int integerDescription, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.damageDescription = damageDescription;
        this.penaltySum = penaltySum;
        this.description = description;
        this.integerDescription = integerDescription;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public PenaltySystem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDamageDescription() {
        return damageDescription;
    }

    public void setDamageDescription(String damageDescription) {
        this.damageDescription = damageDescription;
    }

    public BigDecimal getPenaltySum() {
        return penaltySum;
    }

    public void setPenaltySum(BigDecimal penaltySum) {
        this.penaltySum = penaltySum;
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
        return "PenaltySystem{" +
                "id='" + id + '\'' +
                ", damageDescription='" + damageDescription + '\'' +
                ", penaltySum=" + penaltySum +
                ", description='" + description + '\'' +
                ", integerDescription=" + integerDescription +
                ", created_at=" + created_at +
                ", modified_at=" + modified_at +
                '}';
    }
}

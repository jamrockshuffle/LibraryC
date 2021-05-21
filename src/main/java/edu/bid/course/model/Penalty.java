package edu.bid.course.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * This is a model to hold information about Existing Penalties
 * course.Penalty
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: Penalty: 1.0
 */

@Schema(description = " Penalty List? Entity")
@Document("Penalty")
public class Penalty {

    @Schema(description = " Penalty's auto-generated id")
    @Id
    private String id;

    @Schema(description = " Rented book's name (from Renting collection)")
    private Renting rentedBook;
    @Schema(description = " Penalty clause (from PenaltySystem collection)")
    private PenaltySystem penaltyClause;

    private String description;
    private int integerDescription;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;

    public Penalty(String id, Renting rentedBook, PenaltySystem penaltyClause, String description, int integerDescription, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.rentedBook = rentedBook;
        this.penaltyClause = penaltyClause;
        this.description = description;
        this.integerDescription = integerDescription;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Penalty() {
    }

    public Penalty(String id, Renting rentedBook, PenaltySystem penaltyClause) {
        this.id = id;
        this.rentedBook = rentedBook;
        this.penaltyClause = penaltyClause;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Renting getRentedBook() {
        return rentedBook;
    }

    public void setRentedBook(Renting rentedBook) {
        this.rentedBook = rentedBook;
    }

    public PenaltySystem getPenaltyClause() {
        return penaltyClause;
    }

    public void setPenaltyClause(PenaltySystem penaltyClause) {
        this.penaltyClause = penaltyClause;
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
        return "Penalty{" +
                "id='" + id + '\'' +
                ", rentedBook=" + rentedBook +
                ", penaltyClause=" + penaltyClause +
                ", description='" + description + '\'' +
                ", integerDescription=" + integerDescription +
                ", created_at=" + created_at +
                ", modified_at=" + modified_at +
                '}';
    }
}

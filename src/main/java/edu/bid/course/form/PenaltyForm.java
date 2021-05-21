package edu.bid.course.form;

import edu.bid.course.model.PenaltySystem;
import edu.bid.course.model.Renting;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This is a model to hold String information about Penalties
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: GenreForm: 1.0
 */

public class PenaltyForm {

    private String id;
    private String rentedBook;
    private String penaltyClause;
    private String description;

    public PenaltyForm() {
    }

    public PenaltyForm(String id, String rentedBook, String penaltyClause, String description) {
        this.id = id;
        this.rentedBook = rentedBook;
        this.penaltyClause = penaltyClause;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRentedBook() {
        return rentedBook;
    }

    public void setRentedBook(String rentedBook) {
        this.rentedBook = rentedBook;
    }

    public String getPenaltyClause() {
        return penaltyClause;
    }

    public void setPenaltyClause(String penaltyClause) {
        this.penaltyClause = penaltyClause;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

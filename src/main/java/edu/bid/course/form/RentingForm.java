package edu.bid.course.form;

import edu.bid.course.model.Book;
import edu.bid.course.model.Personnel;
import edu.bid.course.model.Register;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * course.RentingForm
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: RentingForm: 1.0
 */

public class RentingForm {

    private String id;
    private String book;
    private String reader;
    private String rentingDate;
    private String expectedReturnDate;
    private String actualReturnDate;
    private String penaltySum;
    private String rentingPerson;
    private String description;

    public RentingForm() {
    }

    public RentingForm(String id, String book, String reader, String rentingDate, String expectedReturnDate, String actualReturnDate, String penaltySum, String rentingPerson, String description) {
        this.id = id;
        this.book = book;
        this.reader = reader;
        this.rentingDate = rentingDate;
        this.expectedReturnDate = expectedReturnDate;
        this.actualReturnDate = actualReturnDate;
        this.penaltySum = penaltySum;
        this.rentingPerson = rentingPerson;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }

    public String getRentingDate() {
        return rentingDate;
    }

    public void setRentingDate(String rentingDate) {
        this.rentingDate = rentingDate;
    }

    public String getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(String expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public String getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(String actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public String getPenaltySum() {
        return penaltySum;
    }

    public void setPenaltySum(String penaltySum) {
        this.penaltySum = penaltySum;
    }

    public String getRentingPerson() {
        return rentingPerson;
    }

    public void setRentingPerson(String rentingPerson) {
        this.rentingPerson = rentingPerson;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

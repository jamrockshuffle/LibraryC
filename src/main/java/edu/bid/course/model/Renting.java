package edu.bid.course.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * course.Renting
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: Renting: 1.0
 */

@Schema(description = " Renting List? Entity")
@Document("Renting")
public class Renting {

    @Schema(description = " Renging's auto-generated id")
    @Id
    private String id;

    @Schema(description = " Rented book (from Book collection)")
    private Book book;
    @Schema(description = " Person who rented the book (from Register collection)")
    private Register reader;
    @Schema(description = " Renting date", example = " 2021.10.10")
    private LocalDate rentingDate;
    @Schema(description = " Expected renting date", example = " 2021.11.10")
    private LocalDate expectedReturnDate;
    @Schema(description = " Actual renting date", example = " 2021.12.10")
    private LocalDate actualReturnDate;
    @Schema(description = " Penalty sum", example = " 99.99")
    private BigDecimal penaltySum;
    @Schema(description = " Personnel who gave the book (from Personnel collection)")
    private Personnel rentingPerson;

    private String description;
    private int integerDescription;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;

    public Renting() {
    }

    public Renting(String id, Book book, Register reader, LocalDate rentingDate, LocalDate expectedReturnDate, LocalDate actualReturnDate, BigDecimal penaltySum, Personnel rentingPerson) {
        this.id = id;
        this.book = book;
        this.reader = reader;
        this.rentingDate = rentingDate;
        this.expectedReturnDate = expectedReturnDate;
        this.actualReturnDate = actualReturnDate;
        this.penaltySum = penaltySum;
        this.rentingPerson = rentingPerson;
    }

    public Renting(String id, Book book, Register reader, LocalDate rentingDate, LocalDate expectedReturnDate, LocalDate actualReturnDate, BigDecimal penaltySum, Personnel rentingPerson, String description, int integerDescription, LocalDateTime created_at, LocalDateTime modified_at) {
        this.id = id;
        this.book = book;
        this.reader = reader;
        this.rentingDate = rentingDate;
        this.expectedReturnDate = expectedReturnDate;
        this.actualReturnDate = actualReturnDate;
        this.penaltySum = penaltySum;
        this.rentingPerson = rentingPerson;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Register getReader() {
        return reader;
    }

    public void setReader(Register reader) {
        this.reader = reader;
    }

    public LocalDate getRentingDate() {
        return rentingDate;
    }

    public void setRentingDate(LocalDate rentingDate) {
        this.rentingDate = rentingDate;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public BigDecimal getPenaltySum() {
        return penaltySum;
    }

    public void setPenaltySum(BigDecimal penaltySum) {
        this.penaltySum = penaltySum;
    }

    public Personnel getRentingPerson() {
        return rentingPerson;
    }

    public void setRentingPerson(Personnel rentingPerson) {
        this.rentingPerson = rentingPerson;
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
        return "Renting{" +
                "id='" + id + '\'' +
                ", book=" + book +
                ", reader=" + reader +
                ", rentingDate=" + rentingDate +
                ", expectedReturnDate=" + expectedReturnDate +
                ", actualReturnDate=" + actualReturnDate +
                ", penaltySum=" + penaltySum +
                ", rentingPerson=" + rentingPerson +
                ", description='" + description + '\'' +
                ", integerDescription=" + integerDescription +
                ", created_at=" + created_at +
                ", modified_at=" + modified_at +
                '}';
    }
}
